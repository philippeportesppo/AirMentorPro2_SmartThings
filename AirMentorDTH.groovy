/**
 *  Air Mentor Pro 2
 *
 *  Copyright 2017 Philippe PORTES
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
    > preferences {

    	section("Internal Access"){
		input "internal_ip", "text", title: "Internal IP", required: true
		input "internal_port", "text", title: "Internal Port (80)", required: true
		input "internal_query_path", "text", title: "Internal query Path (/airmentorpro2.php)", required: true
	}
}
metadata {
	definition (name: "Air Mentor Pro 2", namespace: "philippeportesppo", author: "Philippe PORTES", oauth: true) {
		capability "Carbon Dioxide Measurement"
		capability "Relative Humidity Measurement"
		capability "Temperature Measurement"
		capability "refresh"
        capability "polling"  
}
tiles(scale: 2) {
    multiAttributeTile(name:"IAQ", type:"generic", width:6, height:4) {
         // value tile (read only)
    tileAttribute("device.IAQ", key: "PRIMARY_CONTROL") {
        attributeState("default", label:'${currentValue}', backgroundColors:[
            [value: 0, color: "#153591"],
            [value: 1, color: "#1d9114"],
            [value: 51, color: "#f7f709"],
            [value: 100, color: "#f7a709"],
            [value: 150, color: "#f70909"],
            [value: 200, color: "#5100a3"]])
   		}

}
	standardTile("temperature", "device.temperature", width: 2, height: 2, canChangeIcon: false) {
            state "default", label: '${currentValue}º', unit:"dC", 
                  icon: "st.Weather.weather2", backgroundColors:[
            [value: 10, color: "#153591"],
            [value: 15, color: "#1e9cbb"],
            [value: 18, color: "#90d2a7"],
            [value: 20, color: "#44b621"],
            [value: 22, color: "#f1d801"],
            [value: 25, color: "#d04e00"],
            [value: 28, color: "#bc2323"]]
        }
	standardTile("humidity", "device.humidity", width: 2, height: 2, canChangeIcon: false) {
            state "default", label: '${currentValue}%', 
                  icon: "st.Weather.weather12", backgroundColors:[
            [value: 40, color: "#153591"],
            [value: 50, color: "#1e9cbb"],
            [value: 60, color: "#90d2a7"],
            [value: 70, color: "#44b621"],
            [value: 80, color: "#f1d801"],
            [value: 90, color: "#d04e00"],
            [value: 95, color: "#bc2323"]]
        }
		standardTile("co2", "device.co2", width: 2, height: 2, canChangeIcon: false) {
            state "default", label: 'CO2: '+'${currentValue}', 
                  icon: "st.alarm.smoke.smoke", backgroundColors:[
            [value: -1, color: "#1e9cbb"],
            [value: 0, color: "#1d9114"],
            [value: 800, color: "#f7f709"],
            [value: 1000, color: "#f7a709"],
            [value: 2500, color: "#f70909"]]
        }
        standardTile("pm2_5", "device.pm2_5", width: 2, height: 2, canChangeIcon: false) {
            state "default", label: 'PM2.5: '+'${currentValue}', 
                  icon: "st.particulate.particulate.particulate", backgroundColors:[
            [value: -1, color: "#1e9cbb"],
            [value: 0, color: "#1d9114"],
            [value: 10, color: "#f7f709"],
            [value: 40, color: "#f7a709"],
            [value: 66, color: "#f70909"],
            [value: 150, color: "#5100a3"]]
        }
        standardTile("pm10", "device.pm10", width: 2, height: 2, canChangeIcon: false) {
            state "default", label: 'PM10: '+'${currentValue}', 
                  icon: "st.particulate.particulate.particulate", backgroundColors:[
            [value: -1, color: "#1e9cbb"],
            [value: 0, color: "#1d9114"],
            [value: 55, color: "#f7f709"],
            [value: 155, color: "#f7a709"],
            [value: 255, color: "#f70909"],
            [value: 355, color: "#5100a3"]]
        }
        standardTile("tvoc", "device.tvoc", width: 2, height: 2, canChangeIcon: false) {
            state "default", label: 'TVOC: '+'${currentValue}', 
                  icon: "st.particulate.particulate.particulate", backgroundColors:[
            [value: -1, color: "#1e9cbb"],
            [value: 0, color: "#1d9114"],
            [value: 340, color: "#f7f709"],
            [value: 530, color: "#f7a709"],
            [value: 800, color: "#f70909"],
            [value: 1600, color: "#5100a3"]]
        }
         standardTile ("battery", "device.battery", decoration:"flat", width: 2, height: 2, canChangeIcon: false) {
            state "default", label:'${currentValue}'  
            }
 	standardTile("refresh", "device.thermostatMode", decoration: "ring", width: 2, height: 2) {
 		state "default", action:"refresh", icon:"st.secondary.refresh"
 		} 

    // Only used for things view in order to display a nice icon (I didn't ask permission to use it, so you can replace by what you like).
    standardTile("IAQ_main", "device.IAQ_main") 
    	{state "default", label:'${currentValue}', icon:"http://www.air-mentor.com/static/www/en/img/app-icon.png", backgroundColors:[
            [value: 0, color: "#153591"],
            [value: 1, color: "#1d9114"],
            [value: 51, color: "#f7f709"],
            [value: 100, color: "#f7a709"],
            [value: 150, color: "#f70909"],
            [value: 200, color: "#5100a3"]]
   		}
	main("IAQ_main")
	details(["IAQ","co2","pm2_5","pm10","tvoc","temperature","humidity","battery","refresh" ])
 	}
}
def installed() {
	log.debug "Executing 'installed'"
    log.debug "Scheduling refresh every 3 minutes"
    schedule("0 0/3 * * * ?", refresh)

}
def updated() {
	log.debug "Executing 'updated'"
    refresh()
}
def initialize() {
    log.debug "initialize"
}
def poll(){
log.debug "Executing 'poll'"
    refresh()
}
private Integer computeIAQI(array_IAQI, array_C, Ccur, IQA) {
	def min
    def max
    def index_max
    def IAQI
    def i
	for (i = 1; i <6; i++) {
    	if (Ccur.toInteger()<=array_C[i])
        {
        	index_max=i
        	max=array_C[i]
            min=array_C[i-1]
    	}
    }
    IAQI= (array_IAQI[index_max]-array_IAQI[index_max-1])/(max-min)*(Ccur.toInteger()-min)+array_IAQI[index_max-1]
    // compare to last computed IAQI: final IAQ will be the max of all.
    if (IAQ>IAQI)
    	return IAQ
    else
    	return IAQI
}
def parse(description) {
	log.debug "Executing 'parse'"

    log.debug "The device id receiving is: $device.deviceNetworkId"
    def msg = parseLanMessage(description)
    //log.debug msg.body
	if (msg.status == 200)
    {
        def xmlParser = new XmlParser()
        def html = xmlParser.parseText(msg.body)
        assert html instanceof groovy.util.Node 
        log.debug html
        log.debug "CO2: ${html.body.table.tr[1].td[0].text()}"
        log.debug "PM2.5: ${html.body.table.tr[1].td[1].text()}"
        log.debug "PM10: ${html.body.table.tr[1].td[2].text()}"
        log.debug "Temperature: ${html.body.table.tr[1].td[3].text()}"
        log.debug "humidity: ${html.body.table.tr[1].td[4].text()}"
        log.debug "TVOC: ${html.body.table.tr[1].td[5].text()}"        
        log.debug "IAQ: ${html.body.table.tr[1].td[6].text()}"
        log.debug "Battery: ${html.body.table.tr[1].td[7].text()}"

        def co2_int     = html.body.table.tr[1].td[0].text()
        def pm2_5_int   = html.body.table.tr[1].td[1].text()
        def pm10_int    = html.body.table.tr[1].td[2].text()
        def temp_float  = html.body.table.tr[1].td[3].text()
        def humid_float = html.body.table.tr[1].td[4].text()  
        def tvoc_int    = html.body.table.tr[1].td[5].text()
        def iaq_int     = html.body.table.tr[1].td[6].text()  
        def battery_int = html.body.table.tr[1].td[7].text()  

		// You can compute your own country IAQ based on local regulations.

		// Compute IAQ (not exactly the one computed by Air Mentor as I don't know how they ponderated
        // and it is directly available in the handle 0x002c BT LE message.
		// Below is PRC values as example.
//        							Polluant types (C)
//        						 IAQI   CO2	 PM2.5	PM10  VOC Hum Max	Hum Min	temp Max	Temp min
//									  0	  0	     0	   0    0	40		  60	   	21			26
//    Good						 	 50	801	    15	  55  312	60	      40	    26	        21
//Moderate							101	1201    40	 156  561	80	      30	    30	        15
//Unhealthy for sensitive people	151	2001    65	 256 1001	90	      20	    35	        10
//Unhealthy	                        201	3001   105 	 356 3001  100	       0	    40	         0
// Very unhealthy					300
		// Formula is IAQI = (IAQImax-IAQImin)/(Cmax-Cmin)*(Ccurr-Cmin)+IAQImin
        // In short for a C polluant, use the min max of the IAQI in front of the C range around the CCurr value.
        // Then pick-up the max of all IAQIc computed.
/*		def Integer[] IAQI=      [0,  50,  100,  150,  200,  300]
        def Integer[] CO2_IAQI=  [0, 800, 1200, 2000, 5000, 6000]
        def Integer[] PM2_5_IAQI=[0,  15,   40,   65,  105,  500]
        def Integer[] PM10_IAQI= [0,  55,  155,  255,  355, 1000]
        def Integer[] TVOC_IAQI= [0, 312,  560, 1000, 3000, 5000]
        def Integer IAQ=0
        def Hum_high_IAQI=[40,60,80,90,100]
        def Hum_low_IQAI=[60,40,30,20,0]
        def Temp_high_IAQI=[21,26,30,35,40]

        // if no measurment available, put -1 to get a blue colored icon at tiles
        if (co2_int == 'na')
            co2_int = -1
        else
        	IAQ = computeIAQI(IAQI,CO2_IAQI, co2_int, IAQ) 

        if (pm2_5_int == 'na')
            pm2_5_int = -1
        else
            IAQ = computeIAQI(IAQI,PM2_5_IAQI, pm2_5_int, IAQ) 
        if (pm10_int == 'na')
            pm10_int = -1
        else
            IAQ = computeIAQI(IAQI,PM10_IAQI, pm10_int, IAQ) 

        if (tvoc_int == 'na')
            tvoc_int = -1
        else
            IAQ = computeIAQI(IAQI,TVOC_IAQI, tvoc_int, IAQ) 
        # continue same for temp and humidity
*/
		// Or use the Air Mentor Pro 2 IAQ
        // if no measurment available, put -1 to get a blue colored icon at tiles
        if (co2_int == 'na')
            co2_int = -1

        if (pm2_5_int == 'na')
            pm2_5_int = -1
        if (pm10_int == 'na')
            pm10_int = -1

        if (temp_float == 'na')
            temp_float = -1

        if (humid_float == 'na')
            humid_float = -1

        if (tvoc_int == 'na')
            tvoc_int = -1

        if (iaq_int == 'na')
            iaq_int = -1    

 		if (battery_int != 'na')
        	// No -1 for battery as the prompt would display it
            if (battery_int.toInteger()>100)
            	sendEvent(name: "battery", value: 'External power in')
            else
        		sendEvent(name: "battery", value:  battery_int.toString()+"% battery")
        sendEvent(name: "co2",     		value:  co2_int.toString())
        sendEvent(name: "pm2_5",   		value:  pm2_5_int.toString())
        sendEvent(name: "pm10",    		value:  pm10_int.toString())
        sendEvent(name: "tvoc",    		value:  tvoc_int.toString())
        sendEvent(name: "IAQ",     		value:  iaq_int.toString())
        sendEvent(name: "IAQ_main",     value:  iaq_int.toString())
		sendEvent(name: "temperature", 	value: temp_float.toString().format(java.util.Locale.US,"%.1f", temp_float.toFloat()))
        sendEvent(name: "humidity", 	value: humid_float.toString().format(java.util.Locale.US,"%.1f", humid_float.toFloat()))
    }

}
private String convertIPtoHex(ipAddress) { 
    String hex = ipAddress.tokenize( '.' ).collect {  String.format( '%02x', it.toInteger() ) }.join()
    log.debug "IP address entered is $ipAddress and the converted hex code is $hex"
    return hex
}
private String convertPortToHex(port) {
	String hexport = port.toString().format( '%04x', port.toInteger() )
    log.debug hexport
    return hexport
}
def refresh() {
	log.debug "Executing refresh"

    def host = internal_ip 
    def port = internal_port
    def hosthex = convertIPtoHex(host)
    def porthex = convertPortToHex(port)
    log.debug "The device id before update is: $device.deviceNetworkId"
    device.deviceNetworkId = "$hosthex:$porthex" 

    log.debug "The device id configured is: $device.deviceNetworkId"

    def path = internal_query_path
    log.debug "path is: $path"

    def headers = [:] 
    headers.put("HOST", "$host:$port")

  try {
    def hubAction = new physicalgraph.device.HubAction(
    	method: "GET",
    	path: path,
    	headers: headers
        )

    return hubAction

    }
    catch (Exception e) {
    	log.debug "Hit Exception $e on $hubAction"
    }

}
