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
 
definition (name: "Air Mentor Pro 2", namespace: "philippeportesppo", author: "Philippe PORTES", oauth: true) {
    	capability "refresh"
        capability "polling"
        capability "sensor"
		capability "Carbon Dioxide Measurement"
        capability "capability.temperatureMeasurement"
        capability "capability.relativeHumidityMeasurement"
		
}

metadata {

preferences {
        section {
            //input "internal_ip", "text", title: "Internal IP", required: true
            //input "internal_port", "text", title: "Internal Port (80)", required: true
            //input "internal_query_path", "text", title: "Internal query Path", defaultValue: "/airmentorpro2.php?Action=get", required: true
            }		
        }
    
tiles(scale: 2) {

    standardTile("iaqlevel", "device.iaqlevel", width: 6, height: 3) {
        state"default", label:'${currentValue}', wordWrap: false, icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/app-icon_bw_iaq.png", backgroundColors:[
            [value: 0, color: "#153591"],
            [value: 1, color: "#1d9114"],
            [value: 51, color: "#f7f709"],
            [value: 100, color: "#f7a709"],
            [value: 150, color: "#f70909"],
            [value: 200, color: "#5100a3"]]
   		}
               
               
               
               
	standardTile("temperaturecallevel", "device.temperaturecallevel", width: 2, height: 2, decoration: "flat",canChangeIcon: false) {
            state "default", label: '${currentValue}º',unit:'${currentValue}', 
                  icon: "st.Weather.weather2", backgroundColors:[
            [value: 10, color: "#153591"], 
            [value: 15, color: "#1e9cbb"],
            [value: 18, color: "#90d2a7"],
            [value: 20, color: "#44b621"],
            [value: 22, color: "#f1d801"],
            [value: 25, color: "#d04e00"],
            [value: 28, color: "#bc2323"]]
        }        
        
	standardTile("humiditylevel", "device.humiditylevel", width: 2, height: 2) {
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
		standardTile("co2level", "device.co2level", width: 2, height: 2,decoration: "flat") {
            state "default", label: '${currentValue}', 

                  icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/CO2-Icon.png", backgroundColors:[

            [value: -1, color: "#1e9cbb"],
            [value: 0, color: "#1d9114"],
            [value: 800, color: "#f7f709"],
            [value: 1000, color: "#f7a709"],
            [value: 2500, color: "#f70909"]]
        }
        standardTile("pm2_5level", "device.pm2_5level", width: 2, height: 2, decoration: "flat",canChangeIcon: false) {
            state "default", label: '${currentValue}', 

                  icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/particulate_pm25.png", backgroundColors:[

            [value: -1, color: "#1e9cbb"],
            [value: 0, color: "#1d9114"],
            [value: 10, color: "#f7f709"],
            [value: 40, color: "#f7a709"],
            [value: 66, color: "#f70909"],
            [value: 150, color: "#5100a3"]]
        }
        standardTile("pm10level", "device.pm10level", width: 2, height: 2, decoration: "flat",canChangeIcon: false) {
            state "default", label: '${currentValue}', 

                  icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/particulate_pm10.png", backgroundColors:[

            [value: -1, color: "#1e9cbb"],
            [value: 0, color: "#1d9114"],
            [value: 55, color: "#f7f709"],
            [value: 155, color: "#f7a709"],
            [value: 255, color: "#f70909"],
            [value: 355, color: "#5100a3"]]
        }
        standardTile("tvoclevel", "device.tvoclevel", width: 2, height: 2, decoration: "flat",canChangeIcon: false) {
            state "default", label: '${currentValue}',unit:'ppm', 
                  icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/TVOC-Icon.png", backgroundColors:[
            [value: -1, color: "#1e9cbb"],
            [value: 0, color: "#1d9114"],
            [value: 340, color: "#f7f709"],
            [value: 530, color: "#f7a709"],
            [value: 800, color: "#f70909"],
            [value: 1600, color: "#5100a3"]]
        }

 	standardTile("dewpointlevel", "device.dewpointlevel",  width: 2, height: 2, decoration: "flat",canChangeIcon: false) {
            state "default", label: '${currentValue}º',unit:'${currentValue}',icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/dewpoint.png", backgroundColor:"#e5e9ea"}
            
  	standardTile("EMClevel", "device.EMClevel",  width: 2, height: 2, decoration: "flat",canChangeIcon: false) {
            state "default",  label: '${currentValue}%',icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/EMC.png", backgroundColors:[
            [value: 0, color: "#1d9114"],
            [value: 4.5, color: "#1d9114"],
            [value: 12.5, color: "#f7f709"],
            [value: 16, color: "#f70909"]]
            }

    standardTile("RealFeellevel", "device.RealFeellevel",   width: 2, height: 2, decoration: "flat", canChangeIcon: false) {
            state "default",  label: '${currentValue}º',unit:'${currentValue}',icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/realfeel.png",backgroundColor:"#e5e9ea"}
            
    standardTile("UGW_web", "device.UGW_web",  width: 6, height: 3,  canChangeIcon: false ) {
            state "default", icon: "http://icons.wxug.com/graphics/wu2/logo_130x80.png"      }   
             
    standardTile("UGWtemperaturecallevel", "device.UGWtemperaturecallevel", width: 2, height: 2, decoration: "flat", canChangeIcon: false) {
            state "default", label: '${currentValue}º',unit:'${currentValue}', icon: "st.Weather.weather2", backgroundColor:"#e5e9ea"}  
        
	standardTile("UGWhumiditylevel", "device.UGWhumiditylevel", width: 2, height: 2, decoration: "flat", canChangeIcon: false) {
            state "default", label: '${currentValue}', icon: "st.Weather.weather12", backgroundColor:"#e5e9ea"      }
            
	standardTile("UGWFeelsLikelevel", "device.UGWFeelsLikelevel",  width: 2, height: 2, decoration: "flat", canChangeIcon: false) {
            state "default",  label: '${currentValue}º',unit:'${currentValue}',icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/realfeel.png", backgroundColor:"#e5e9ea"}

	standardTile("UGWdewpointlevel", "device.UGWdewpointlevel",  width: 2, height: 2, decoration: "flat", canChangeIcon: false) {
            state "default", label: '${currentValue}º',unit:'${currentValue}',icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/dewpoint.png", backgroundColor:"#e5e9ea"}
            
    standardTile("UGW_Icon_UrlIcon", "device.UGW_Icon_UrlIcon", decoration: "flat",   width: 2, height: 2) {
                state "chancerain",		icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/chancerain.png"
                state "chancesleet",	icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/chancesleet.png"
                state "chancesnow",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/chancesnow.png"
                state "chancetstorms",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/chancetstorms.png"
                state "clear",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/clear.png"
                state "cloudy",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/cloudy.png"
                state "flurries",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/flurries.png"
                state "fog",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/fog.png"
                state "hazy",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/hazy.png"
                state "mostlycloudy",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/mostlycloudy.png"
                state "mostlysunny",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/mostlysunny.png"
                state "partlycloudy",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/partlycloudy.png"
                state "partlysunny",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/partlysunny.png"
                state "sleet",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/sleet.png"
                state "rain",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/rain.png"
                state "snow",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/snow.png"
                state "sunny",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/sunny.png"
                state "tstorms",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/tstorms.png"
                state "unknown",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/unknown.png"
                state "nt_chanceflurries",	icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/nt_chanceflurries.png"	
                state "nt_chancerain",		icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/nt_chancerain.png"
                state "nt_chancesleet",	icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/nt_chancesleet.png"
                state "nt_chancesnow",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/nt_chancesnow.png"
                state "nt_chancetstorms",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/nt_chancetstorms.png"
                state "nt_clear",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/nt_clear.png"
                state "nt_cloudy",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/nt_cloudy.png"
                state "nt_flurries",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/nt_flurries.png"
                state "nt_fog",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/nt_fog.png"
                state "nt_hazy",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/nt_hazy.png"
                state "nt_mostlycloudy",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/nt_mostlycloudy.png"
                state "nt_mostlysunny",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/nt_mostlysunny.png"
                state "nt_partlycloudy",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/nt_partlycloudy.png"
                state "nt_sleet",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/nt_sleet.png"
                state "nt_rain",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/nt_rain.png"
                state "nt_sleet",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/nt_sleet.png"
                state "nt_snow",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/nt_snow.png"
                state "nt_sunny",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/nt_sunny.png"
                state "nt_tstorms",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/nt_tstorms.png"
	}
            
    standardTile("refresh", "device.refresh", decoration: "flat", width: 2, height: 2) {
 		state "default", action:"refresh", icon:"st.secondary.refresh"
 		} 
	
   standardTile("weather", "device.weather", width: 6, height: 2) {
 		state "default", label:'${currentValue}'
 		} 
    // Only used for things view in order to display a nice icon (I didn't ask permission to use it, so you can replace by what you like).
    standardTile("iaq_main", "device.iaq_main", decoration: "flat", width: 6, height: 4) 

    	{state "default", label:'${currentValue}', icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/app-icon_bw.png", backgroundColors:[

            [value: 0, color: "#153591"],
            [value: 1, color: "#1d9114"],
            [value: 51, color: "#f7f709"],
            [value: 100, color: "#f7a709"],
            [value: 150, color: "#f70909"],
            [value: 200, color: "#5100a3"]]
   		}
	main("iaq_main")
	details(["iaqlevel","co2level","pm2_5level","pm10level","tvoclevel","temperaturecallevel","humiditylevel","EMClevel","RealFeellevel","dewpointlevel","UGW_web","UGWtemperaturecallevel","UGWhumiditylevel","UGWFeelsLikelevel","UGWdewpointlevel","UGW_Icon_UrlIcon","weather","refresh" ])
 	}
    
}    


def installed() {
	log.debug "Executing 'installed'"
    log.debug getDataValue("ip")
    log.debug getDataValue("port")

	state.IAQ_event = ""
    state.CO2_event = ""
    state.PM25_event= ""
    state.PM10_event= ""
    state.TVOC_event= ""
    state.requestCounter = 0

    log.debug "state events initialized..."

 	refresh()

    
}

def updated() {

	log.debug "Executing 'updated'"

 	refresh()
}

def initialize() {


}


def poll(){
log.debug "Executing 'poll'"
    refresh()
}

String convertTemperature( float temperatureCelcius, unit)
{
	float value = temperatureCelcius
    
    if (unit =="F")
    {
       value = temperatureCelcius * 1.8 + 32.0
    }
    return value.toString().format(java.util.Locale.US,"%.1f", value)
}

def parse(description) {
	log.debug "Executing 'parse'"
        
    if (state.requestCounter==0)
    	return null

	// Prevent parse to be executed twice (ST bug?)
	state.requestCounter=0
	def events = []
    //log.debug "The device id receiving is: $device.deviceNetworkId"
    //log.debug description
    def msg = parseLanMessage(description)
    //log.debug msg.body
	if (msg.status == 200)
    {
        def xmlParser = new XmlParser()
        def html = xmlParser.parseText(msg.body)

		// log.debug html
        log.debug "CO2: ${html.body.table.tr[1].td[0].text()}"
        log.debug "PM2.5: ${html.body.table.tr[1].td[1].text()}"
        log.debug "PM10: ${html.body.table.tr[1].td[2].text()}"
        log.debug "Temperature: ${html.body.table.tr[1].td[3].text()}"
        log.debug "Temperature_Cal: ${html.body.table.tr[1].td[4].text()}"
        log.debug "humidity: ${html.body.table.tr[1].td[5].text()}"
        log.debug "TVOC: ${html.body.table.tr[1].td[6].text()}"        
        log.debug "IAQ: ${html.body.table.tr[1].td[7].text()}"
        log.debug "Battery: ${html.body.table.tr[1].td[8].text()}"
        log.debug "UGW_feelslike:${html.body.table.tr[1].td[9].text()}"
        log.debug "UGW_DewPoint:${html.body.table.tr[1].td[10].text()}"
        log.debug "UGW_Humidity:${html.body.table.tr[1].td[11].text()}"
        log.debug "UGW_Temp:${html.body.table.tr[1].td[12].text()}"
        log.debug "UGW_Icon_Url:${html.body.table.tr[1].td[13].text()}"
        log.debug "UGW_Icon_Night:${html.body.table.tr[1].td[14].text()}"
        log.debug "UGW_Weather:${html.body.table.tr[1].td[15].text()}"
        
        def co2_int     = html.body.table.tr[1].td[0].text()
        def pm2_5_int   = html.body.table.tr[1].td[1].text()
        def pm10_int    = html.body.table.tr[1].td[2].text()
        def temp_float  = html.body.table.tr[1].td[3].text()
        def temp_cal_float  = html.body.table.tr[1].td[4].text()
        def humid_float = html.body.table.tr[1].td[5].text()  
        def tvoc_int    = html.body.table.tr[1].td[6].text()
        def iaq_int     = html.body.table.tr[1].td[7].text()  
        def battery_int = html.body.table.tr[1].td[8].text()  
        def UGW_feelslike_float  = html.body.table.tr[1].td[9].text()
        def UGW_DewPoint_float  = html.body.table.tr[1].td[10].text()
        def UGW_Humidity_float = html.body.table.tr[1].td[11].text()
        def UGW_Temp_float = html.body.table.tr[1].td[12].text() 
        def UGW_Icon_Url = html.body.table.tr[1].td[13].text()
        def UGW_Icon_Nt =  html.body.table.tr[1].td[14].text()

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
        
        if (temp_cal_float == 'na')
            temp_cal_float = -1
        
        if (humid_float == 'na')
            humid_float = -1
            
        if (tvoc_int == 'na')
            tvoc_int = -1
            
        if (iaq_int == 'na')
            iaq_int = -1    

		//log.debug "Generating events for UX refresh"
        def temperatureScale = getTemperatureScale()

        events <<  createEvent(name: "co2level",     		value: co2_int.toString())
        events <<  createEvent(name: "pm2_5level",       value: pm2_5_int.toString())
        events <<  createEvent(name: "pm10level",    		value: pm10_int.toString())
        events <<  createEvent(name: "tvoclevel",    		value: tvoc_int.toString())
        events <<  createEvent(name: "iaqlevel",     		value: iaq_int.toString())
        events <<  createEvent(name: "iaq_main",       value: iaq_int.toString())
        
        events <<  createEvent(name:"temperaturecallevel",  value: convertTemperature(temp_cal_float.toFloat(),temperatureScale), unit: temperatureScale)
        events <<  createEvent(name: "humiditylevel", 	    value: humid_float.toString().format(java.util.Locale.US,"%.1f", humid_float.toFloat()))

		// Environmental indicators
        
        // Dew Point computation:
        // TD: =243.04*(LN(RH/100)+((17.625*T)/(243.04+T)))/(17.625-LN(RH/100)-((17.625*T)/(243.04+T)))
        // From http://andrew.rsmas.miami.edu/bmcnoldy/Humidity.html
        float dew_point_value = 243.04*(Math.log(humid_float.toFloat()/100.0)+((17.625*temp_cal_float.toFloat())/(243.04+temp_cal_float.toFloat())))/(17.625-Math.log(humid_float.toFloat()/100.0)-((17.625*temp_cal_float.toFloat())/(243.04+temp_cal_float.toFloat()))) 
		events <<  createEvent(name: "dewpointlevel", 	  value: convertTemperature(dew_point_value,temperatureScale), unit: temperatureScale)

 	    // Equilibrium Moisture Content Calculator
        def emc_W  =(349+(1.29*temp_cal_float.toFloat())+(0.0135*temp_cal_float.toFloat()*temp_cal_float.toFloat()))	
        def emc_k  = 0.805+(0.000736*temp_cal_float.toFloat())-(0.00000273*temp_cal_float.toFloat()*temp_cal_float.toFloat())	
        def emc_k1 = 6.27-(0.00938*temp_cal_float.toFloat())-(0.000303*temp_cal_float.toFloat()*temp_cal_float.toFloat())	
        def emc_k2 = 1.91+(0.0407*temp_cal_float.toFloat())-(0.000293*temp_cal_float.toFloat()*temp_cal_float.toFloat())
		def emc_humid=humid_float.toFloat()/100
		def EMC = (1800/emc_W)*(((emc_k*emc_humid)/(1-emc_k*emc_humid))+(((emc_k1*emc_k*emc_humid)+(2*emc_k1*emc_k2*emc_k*emc_k*emc_humid*emc_humid))/(1+(emc_k1*emc_k*emc_humid)+(emc_k1*emc_k2*emc_k*emc_k*emc_humid*emc_humid))))
        events <<  createEvent(name: "EMClevel", value: EMC.toString().format(java.util.Locale.US,"%.1f", EMC.toFloat()))

		// Real feel indoor temperature
        float realfeel= temp_cal_float.toFloat()+0.348*emc_humid*6.105*Math.exp((17.27*temp_cal_float.toFloat())/(237.7+temp_cal_float.toFloat()))-4.25
        events <<  createEvent(name: "RealFeellevel", value: convertTemperature(realfeel,temperatureScale), unit: temperatureScale)
 
 		// UnderGround Weather references
       	events <<   createEvent(name: "UGWFeelsLikelevel", value: convertTemperature(UGW_feelslike_float.toFloat(),temperatureScale), unit: temperatureScale)
        events <<   createEvent(name: "UGWdewpointlevel", value: convertTemperature(UGW_DewPoint_float.toFloat(),temperatureScale), unit: temperatureScale)
        events <<   createEvent(name: "UGWhumiditylevel", value: UGW_Humidity_float.toString())
       	events <<   createEvent(name: "UGWtemperaturecallevel", value: convertTemperature(UGW_Temp_float.toFloat(),temperatureScale), unit: temperatureScale)
       	events <<   createEvent(name: "UGW_Icon_UrlIcon", value: UGW_Icon_Nt.toString()+UGW_Icon_Url.toString())
       
 		log.debug "Generating alerts if not good"
        
        def map = generate_app_event( "IAQ",iaq_int.toInteger(), state.IAQ_event, 50, 100,150, 200)

        if (map) {
			events << createEvent(map)
            state.IAQ_event = map.descriptionText
		}

	      
        map = generate_app_event( "CO2",co2_int.toInteger(), state.CO2_event, 800, 1200,2000, 5000)
        if (map) {
			events << createEvent(map)
            state.CO2_event = map.descriptionText
		}
        
        map = generate_app_event( "PM2_5",pm2_5_int.toInteger(), state.PM25_event, 15, 40, 65, 105)
        if (map) {
			events <<  createEvent(map)
            state.PM25_event = map.descriptionText
		}

        map = generate_app_event( "PM10",pm10_int.toInteger(), state.PM10_event, 55, 155, 255, 355)
        if (map) {
			events <<  createEvent(map)
            state.PM10_event=map.descriptionText
		}

        map = generate_app_event( "TVOC",tvoc_int.toInteger(), state.TVOC_event, 312, 560, 1000, 3000)
        if (map) {
			events <<  createEvent(map)
            state.TVOC_event=map.descriptionText
		}
		
        events << createEvent( name:"weather", value: html.body.table.tr[1].td[15].text(), display:true, isStateChange: true)

	}

    return events
}

private generate_app_event( name_, int value_, previous, int thres_moderate, int thres_unhealthy_sensitive, int thres_unhealthy, int thres_very_unhealty)
{
  def map = [:] 
  def fire_event = false
  map.name = name_
  log.debug("generate_app_event: ${name_} ${value_} ${thres_moderate} ${thres_unhealthy_sensitive} ${thres_unhealthy} ${thres_very_unhealty}")
  if (value_>thres_very_unhealty)
  	{   
        map.value="very unhealthy"
        map.descriptionText="${name_} alert: ${map.value}"

        fire_event=true
    }
  else if (value_>thres_unhealthy)
  	{
  		map.value="unhealthy"
        map.desciptionText="${name_} alert: ${map.value}"

        fire_event=true
    }
  else if (value_>thres_unhealthy_sensitive)
  	{
  		map.value="unhealthy sensitive persons"
        map.descriptionText="${name_} alert: ${map.value}"

        fire_event=true
    }
  else if (value_>thres_moderate)
  	{
  		map.value="moderate"
        map.descriptionText="${name_} alert: ${map.value}"

        fire_event=true
    }
  else
  	{
  		map.value="good"
        map.descriptionText="${name_} alert: ${map.value}"
		// change to true if want to be notified of good measures
        fire_event=true
    }
    
  if (previous == map.descriptionText )
  {
  	fire_event=false
  }
    
  if (fire_event)
  	{
    	log.debug("Generated alert for : ${name_} ${map.value}")
  		map.isStateChange = true 
    	map.displayed = true    
    	return map
    }
  else
  	{
     	log.debug("No alert generated for ${name_}")
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
	
    def host = getDataValue("ip")//internal_ip 
    	log.debug "Executing refresh 2"

    def port = getDataValue("port")//internal_port
	log.debug "Executing refresh 3"

    //def hosthex = convertIPtoHex(host)
	log.debug "Executing refresh 4"

    //def porthex = convertPortToHex(port)
    log.debug "The device id before update is: $device.deviceNetworkId"
    device.deviceNetworkId = "$host:$port" 
    
    log.debug "The device id configured is: $device.deviceNetworkId"
    
    def path = getDataValue("query_path")
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



            
