/**
 *  Generic UPnP Service Manager
 *
 *  Copyright 2018 Philippe Portes based on SmartThings original UPnP Service Manager SmartApp
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
definition(
		name: "AirMentor Pro UPnP Service Manager",
		namespace: "philippeportesppo",
		author: "Philippe Portes",
		description: "Discover and configures your Raspberry Pi bridge to AirMentor Pro (see https://github.com/philippeportesppo/AirMentorPro2_SmartThings).",
		category: "SmartThings Labs",
		iconUrl: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/app-icon_bw.png",
		iconX2Url: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/app-icon_bw.png",
		iconX3Url: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/app-icon_bw.png")


preferences {
	page(name: "searchTargetSelection", title: "UPnP AirMentor via Pi Search Target", nextPage: "deviceDiscovery") {
		section("Search Target") {
			input "searchTarget", "string", title: "Search Target", defaultValue: "urn:schemas-upnp-org:device:AirMentorPro2:1", required: true
		}
	}
	page(name: "deviceDiscovery", title: "UPnP Device Setup", content: "deviceDiscovery")
}

def deviceDiscovery() {
	def options = [:]
	def devices = getVerifiedDevices()
	devices.each {
		def value = it.value.name ?: "AirMentorPro ${it.value.ssdpUSN.split(':')[1][-3..-1]}"
		def key = it.value.mac
		options["${key}"] = value
	}

	ssdpSubscribe()

	ssdpDiscover()
	verifyDevices()

	return dynamicPage(name: "deviceDiscovery", title: "Discovery Started!", nextPage: "", refreshInterval: 5, install: true, uninstall: true) {
		section("Please wait while we discover your AirMentorPro Device. Discovery can take five minutes or more, so sit back and relax! Select your device below once discovered.") {
			input "selectedDevices", "enum", required: false, title: "Select Devices (${options.size() ?: 0} found)", multiple: true, options: options
		}
	}
}

def installed() {
	log.debug "Installed with settings: ${settings}"

	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"

	unsubscribe()
	initialize()
}

def initialize() {
	unsubscribe()
	unschedule()

	ssdpSubscribe()

	if (selectedDevices) {
    	log.debug "Selected device"
		addDevices()
	}

	runEvery5Minutes("ssdpDiscover")
}

void ssdpDiscover() {
	sendHubCommand(new physicalgraph.device.HubAction("lan discovery ${searchTarget}", physicalgraph.device.Protocol.LAN))
}

void ssdpSubscribe() {
	subscribe(location, "ssdpTerm.${searchTarget}", ssdpHandler)
}

Map verifiedDevices() {
	def devices = getVerifiedDevices()
	def map = [:]
	devices.each {
		def value = it.value.name ?: "AirMentorPro ${it.value.ssdpUSN.split(':')[1][-3..-1]}"
		def key = it.value.mac
		map["${key}"] = value
        log.debug map
	}
	map
}

void verifyDevices() {
	def devices = getDevices().findAll { it?.value?.verified != true }
	devices.each {
		int port = convertHexToInt(it.value.deviceAddress)
		String ip = convertHexToIP(it.value.networkAddress)
		String host = "${ip}:${port}"
		sendHubCommand(new physicalgraph.device.HubAction("""GET ${it.value.ssdpPath} HTTP/1.1\r\nHOST: $host\r\n\r\n""", physicalgraph.device.Protocol.LAN, host, [callback: deviceDescriptionHandler]))
	}
}

def getVerifiedDevices() {
	getDevices().findAll{ it.value.verified == true }
}

def getDevices() {
	if (!state.devices) {
		state.devices = [:]
	}
	state.devices
}

def addDevices() {
	def devices = getDevices()
    
    log.debug devices

	selectedDevices.each { dni ->
		def selectedDevice = devices.find { it.value.mac == dni }
        log.debug selectedDevice
		def d
		if (selectedDevice) {
			d = getChildDevices()?.find {
				it.deviceNetworkId == selectedDevice.value.mac
			}
		}

		if (!d) {
			addChildDevice("philippeportesppo", "Air Mentor Pro 2", selectedDevice.value.mac, selectedDevice?.value.hub, [
				"label": selectedDevice?.value?.name ?: "AirMentorPro",
				"data": [
					"mac": selectedDevice.value.mac,
					"ip": selectedDevice.value.networkAddress,
					"port": "0050",
                    "query_path":"/airmentorpro2.php?Action=get"
				]
			])
		}
	}
}

def ssdpHandler(evt) {
	def description = evt.description
	def hub = evt?.hubId

	def parsedEvent = parseLanMessage(description)
	parsedEvent << ["hub":hub]

	def devices = getDevices()
	String ssdpUSN = parsedEvent.ssdpUSN.toString()

	if (devices."${ssdpUSN}") {
		def d = devices."${ssdpUSN}"
        //log.debug d
        //log.debug parsedEvent
		if (d.networkAddress != parsedEvent.networkAddress || d.deviceAddress != parsedEvent.deviceAddress) {
			d.networkAddress = parsedEvent.networkAddress
			d.deviceAddress = parsedEvent.deviceAddress
			def child = getChildDevice(parsedEvent.mac)
            log.debug "Child: "
			if (child) {
            	log.debug child
				child.sync(parsedEvent.networkAddress, parsedEvent.deviceAddress)
			}
		}
	} else {
		devices << ["${ssdpUSN}": parsedEvent]
	}
}

void deviceDescriptionHandler(physicalgraph.device.HubResponse hubResponse) {
	def body = hubResponse.xml
	def devices = getDevices()
	def device = devices.find { it?.key?.contains(body?.device?.UDN?.text()) }
	if (device) {
		device.value << [name: body?.device?.roomName?.text(), model:body?.device?.modelName?.text(), serialNumber:body?.device?.serialNum?.text(), verified: true]
	}
}

private Integer convertHexToInt(hex) {
	Integer.parseInt(hex,16)
}

private String convertHexToIP(hex) {
	[convertHexToInt(hex[0..1]),convertHexToInt(hex[2..3]),convertHexToInt(hex[4..5]),convertHexToInt(hex[6..7])].join(".")
}