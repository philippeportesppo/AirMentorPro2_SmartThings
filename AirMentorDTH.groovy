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
 
preferences {
    
	section("Internal Access"){
		input "internal_ip", "text", title: "Internal IP", required: true
		input "internal_port", "text", title: "Internal Port (80)", required: true
		input "internal_query_path", "text", title: "Internal query Path (/airmentorpro2.php?Action=get)", required: true
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
     standardTile("iaqlevel", "device.iaqlevel", width: 6, height: 4, canChangeIcon: false) {
    state"default", label:'IAQ: '+'${currentValue}', wordWrap: false, backgroundColors:[
        [value: 0, color: "#153591"],
        [value: 1, color: "#1d9114"],
        [value: 51, color: "#f7f709"],
        [value: 100, color: "#f7a709"],
        [value: 150, color: "#f70909"],
        [value: 200, color: "#5100a3"]]
	}        
	standardTile("temperaturelevel", "device.temperaturelevel", width: 2, height: 2, canChangeIcon: false) {
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
	standardTile("humiditylevel", "device.humiditylevel", width: 2, height: 2, canChangeIcon: false) {
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
		standardTile("co2level", "device.co2level", width: 2, height: 2, canChangeIcon: false) {
            state "default", label: 'CO2: '+'${currentValue}', 
                  icon: "st.alarm.smoke.smoke", backgroundColors:[
            [value: -1, color: "#1e9cbb"],
            [value: 0, color: "#1d9114"],
            [value: 800, color: "#f7f709"],
            [value: 1000, color: "#f7a709"],
            [value: 2500, color: "#f70909"]]
        }
        standardTile("pm2_5level", "device.pm2_5level", width: 2, height: 2, canChangeIcon: false) {
            state "default", label: 'PM2.5: '+'${currentValue}', 
                  icon: "st.particulate.particulate.particulate", backgroundColors:[
            [value: -1, color: "#1e9cbb"],
            [value: 0, color: "#1d9114"],
            [value: 10, color: "#f7f709"],
            [value: 40, color: "#f7a709"],
            [value: 66, color: "#f70909"],
            [value: 150, color: "#5100a3"]]
        }
        standardTile("pm10level", "device.pm10level", width: 2, height: 2, canChangeIcon: false) {
            state "default", label: 'PM10: '+'${currentValue}', 
                  icon: "st.particulate.particulate.particulate", backgroundColors:[
            [value: -1, color: "#1e9cbb"],
            [value: 0, color: "#1d9114"],
            [value: 55, color: "#f7f709"],
            [value: 155, color: "#f7a709"],
            [value: 255, color: "#f70909"],
            [value: 355, color: "#5100a3"]]
        }
        standardTile("tvoclevel", "device.tvoclevel", width: 2, height: 2, canChangeIcon: false) {
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
    standardTile("iaq_main", "device.iaq_main") 
    	{state "default", label:'${currentValue}', icon:"http://www.air-mentor.com/static/www/en/img/app-icon.png", backgroundColors:[
            [value: 0, color: "#153591"],
            [value: 1, color: "#1d9114"],
            [value: 51, color: "#f7f709"],
            [value: 100, color: "#f7a709"],
            [value: 150, color: "#f70909"],
            [value: 200, color: "#5100a3"]]
   		}
	main("iaq_main")
	details(["iaqlevel","co2level","pm2_5level","pm10level","tvoclevel","temperaturelevel","humiditylevel","battery","refresh" ])
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

def parse(description) {
	log.debug "Executing 'parse'"
    
    if (state.requestCounter==0)
    	return null

	// Prevent parse to be executed twice (ST bug?)
	state.requestCounter=0

    def msg = parseLanMessage(description)
     
	if (msg.status == 200)
    {
        def xmlParser = new XmlParser()
        def html = xmlParser.parseText(msg.body)

		// log.debug html
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

        def co2_event = createEvent(name: "co2level",     		value: co2_int.toString())
        def pm2_5_event = createEvent(name: "pm2_5level",       value: pm2_5_int.toString())
        def pm10_event = createEvent(name: "pm10level",    		value: pm10_int.toString())
        def tvoc_event = createEvent(name: "tvoclevel",    		value: tvoc_int.toString())
        def IAQ_event = createEvent(name: "iaqlevel",     		value: iaq_int.toString())
        def IAQ_main_event = createEvent(name: "iaq_main",       value: iaq_int.toString())
		def temp_event = createEvent(name: "temperaturelevel", 	value: temp_float.toString().format(java.util.Locale.US,"%.1f", temp_float.toFloat()))
        def hum_event = createEvent(name: "humiditylevel", 	    value: humid_float.toString().format(java.util.Locale.US,"%.1f", humid_float.toFloat()))

        //log.debug "Generating alerts if not good"
        def iaq_event_alert = generate_app_event( "IAQ",iaq_int.toInteger(), 50, 100,150, 200)
        def co2_event_alert = generate_app_event( "CO2",co2_int.toInteger(), 800, 1200,2000, 5000)
        def pm2_5_event_alert = generate_app_event( "PM2_5",pm2_5_int.toInteger(), 15, 40, 65, 105)
        def pm10_event_alert = generate_app_event( "PM10",pm10_int.toInteger(), 55, 155, 255, 355)
        def tvoc_event_alert = generate_app_event( "TVOC",tvoc_int.toInteger(), 312, 560, 1000, 3000)
        
      return [co2_event,pm2_5_event,pm10_event, tvoc_event, IAQ_event, IAQ_main_event, temp_event, hum_event]  

	}
 
}

private Map generate_app_event( name_, int value_, int thres_moderate, int thres_unhealthy_sensitive, int thres_unhealthy, int thres_very_unhealty)
{

  def bcst_value_=""
  def desc_=""
  def fire_event = false
  log.debug("generate_app_event: ${name_} ${value_} ${thres_moderate} ${thres_unhealthy_sensitive} ${thres_unhealthy} ${thres_very_unhealty}")
  if (value_>thres_very_unhealty)
  	{   
        bcst_value_="very unhealthy"
        desc_="${name_} alert: ${bcst_value_}"

        fire_event=true
    }
  else if (value_>thres_unhealthy)
  	{
  		bcst_value_="unhealthy"
        desc_="${name_} alert: ${bcst_value_}"

        fire_event=true
    }
  else if (value_>thres_unhealthy_sensitive)
  	{
  		bcst_value_="unhealthy sensitive persons"
        desc_="${name_} alert: ${bcst_value_}"

        fire_event=true
    }
  else if (value_>thres_moderate)
  	{
  		bcst_value_="moderate"
        desc_="${name_} alert: ${bcst_value_}"

        fire_event=true
    }
  else
  	{
  		bcst_value_="good"
        desc_="${name_} alert: ${bcst_value_}"
		// change to true if want to be notified of good measures
        fire_event=false
    }   
  if (fire_event)
  	{
    	log.debug("Generated alert for : ${name_} ${value_}")
  		return (sendEvent(name: name_, value: bcst_value_, descriptionText: desc_))
    }
  else
  	{
     	log.debug("No alert generated for ${name_}")
  	 	return null
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
  		state.requestCounter=1
        return hubAction
    
    }
    catch (Exception e) {
    	log.debug "Hit Exception $e on $hubAction"
    }
    
}
