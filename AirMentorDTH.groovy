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
		capability "Carbon Dioxide Measurement"
        capability "capability.temperatureMeasurement"
        capability "capability.relativeHumidityMeasurement"

}

metadata {

preferences {
        section {
            input "internal_ip", "text", title: "Internal IP", required: true
            input "internal_port", "text", title: "Internal Port (80)", required: true
            input "internal_query_path", "text", title: "Internal query Path (/airmentorpro2.php?Action=get)", required: true
            }		
        }
    
tiles(scale: 2) {

    standardTile("iaqlevel", "device.iaqlevel", width: 6, height: 4, decoration: "flat",canChangeIcon: false) {
        state"default", label:'IAQ: '+'${currentValue}', wordWrap: false, backgroundColors:[
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
		standardTile("co2level", "device.co2level", width: 2, height: 2) {
            state "default", label: '${currentValue}', 
                  icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/new_icons/images/CO2-Icon.png", backgroundColors:[
            [value: -1, color: "#1e9cbb"],
            [value: 0, color: "#1d9114"],
            [value: 800, color: "#f7f709"],
            [value: 1000, color: "#f7a709"],
            [value: 2500, color: "#f70909"]]
        }
        standardTile("pm2_5level", "device.pm2_5level", width: 2, height: 2, decoration: "flat",canChangeIcon: false) {
            state "default", label: '${currentValue}', 
                  icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/new_icons/images/particulate_pm25.png", backgroundColors:[
            [value: -1, color: "#1e9cbb"],
            [value: 0, color: "#1d9114"],
            [value: 10, color: "#f7f709"],
            [value: 40, color: "#f7a709"],
            [value: 66, color: "#f70909"],
            [value: 150, color: "#5100a3"]]
        }
        standardTile("pm10level", "device.pm10level", width: 2, height: 2, decoration: "flat",canChangeIcon: false) {
            state "default", label: '${currentValue}', 
                  icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/new_icons/images/particulate_pm10.png", backgroundColors:[
            [value: -1, color: "#1e9cbb"],
            [value: 0, color: "#1d9114"],
            [value: 55, color: "#f7f709"],
            [value: 155, color: "#f7a709"],
            [value: 255, color: "#f70909"],
            [value: 355, color: "#5100a3"]]
        }
        standardTile("tvoclevel", "device.tvoclevel", width: 2, height: 2, decoration: "flat",canChangeIcon: false) {
            state "default", label: '${currentValue}',unit:'ppm', 
                  icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/new_icons/images/TVOC-Icon.png", backgroundColors:[
            [value: -1, color: "#1e9cbb"],
            [value: 0, color: "#1d9114"],
            [value: 340, color: "#f7f709"],
            [value: 530, color: "#f7a709"],
            [value: 800, color: "#f70909"],
            [value: 1600, color: "#5100a3"]]
        }

 	standardTile("dewpointlevel", "device.dewpointlevel",  width: 2, height: 2, decoration: "flat",canChangeIcon: false) {
            state "default", label: '${currentValue}º',unit:'${currentValue}',icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/new_icons/images/dewpoint.png", backgroundColor:"#e5e9ea"}
            
  	standardTile("EMClevel", "device.EMClevel",  width: 2, height: 2, decoration: "flat",canChangeIcon: false) {
            state "default",  label: '${currentValue}%',icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/new_icons/images/EMC.png", backgroundColors:[
            [value: 0, color: "#1d9114"],
            [value: 4.5, color: "#1d9114"],
            [value: 12.5, color: "#f7f709"],
            [value: 16, color: "#f70909"]]
            }

    standardTile("RealFeellevel", "device.RealFeellevel",   width: 2, height: 2, decoration: "flat", canChangeIcon: false) {
            state "default",  label: '${currentValue}º',unit:'${currentValue}',icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/new_icons/images/realfeel.png",backgroundColor:"#e5e9ea"}
            
    standardTile("UGW_web", "device.UGW_web",  width: 6, height: 3,  canChangeIcon: false ) {
            state "default", icon: "http://icons.wxug.com/graphics/wu2/logo_130x80.png"      }   
             
    standardTile("UGWtemperaturecallevel", "device.UGWtemperaturecallevel", width: 2, height: 2, decoration: "flat", canChangeIcon: false) {
            state "default", label: '${currentValue}º',unit:'${currentValue}', icon: "st.Weather.weather2", backgroundColor:"#e5e9ea"}  
        
	standardTile("UGWhumiditylevel", "device.UGWhumiditylevel", width: 2, height: 2, decoration: "flat", canChangeIcon: false) {
            state "default", label: '${currentValue}', icon: "st.Weather.weather12", backgroundColor:"#e5e9ea"      }
            
	standardTile("UGWFeelsLikelevel", "device.UGWFeelsLikelevel",  width: 2, height: 2, decoration: "flat", canChangeIcon: false) {
            state "default",  label: '${currentValue}º',unit:'${currentValue}',icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/new_icons/images/realfeel.png", backgroundColor:"#e5e9ea"}

	standardTile("UGWdewpointlevel", "device.UGWdewpointlevel",  width: 2, height: 2, decoration: "flat", canChangeIcon: false) {
            state "default", label: '${currentValue}º',unit:'${currentValue}',icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/new_icons/images/dewpoint.png", backgroundColor:"#e5e9ea"}
            
    standardTile("UGW_Icon_UrlIcon", "device.UGW_Icon_UrlIcon", decoration: "flat",   width: 2, height: 2) {
                state "Chance of Flurries",	icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/chanceflurries.png", label: "Chance of Flurries"	
                state "Chance of Rain",		icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/chancerain.png",		label: "Chance of Rain"
                state "Chance Rain",		icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/chancerain.png",		label: "Chance Rain"
                state "Chance of Freezing Rain",	icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/chancesleet.png",label: "Chance of Freezing Rain"
                state "Chance of Sleet",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/chancesleet.png",label: "Chance of Sleet"
                state "Chance of Snow",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/chancesnow.png",label: "Chance of Snow"
                state "Chance of a Thunderstorm",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/chancetstorms.png",label: "Chance of a Thunderstorm"
                state "Chance of Thunderstorm",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/chancetstorms.png",label: "Chance of Thunderstorm"
                state "Clear",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/clear.png",label: "Clear"
                state "Cloudy",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/cloudy.png",label: "Cloudy"
                state "Flurries",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/flurries.png",label: "Flurries"
                state "Fog",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/fog.png",label: "Fog"
                state "Haze",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/hazy.png",label: "Haze"
                state "Mostly Cloudy",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/mostlycloudy.png",label: "Mostly Cloudy"
                state "Mostly Sunny",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/mostlysunny.png",label: "Mostly Sunny"
                state "Partly Cloudy",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/partlycloudy.png",label: "Partly Cloudy"
                state "Partly Sunny",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/partlysunny.png",label: "Partly Sunny"
                state "Freezing Rain",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/sleet.png",label: "Freezing Rain"
                state "Rain",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/rain.png",label: "Rain"
                state "Sleet",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/sleet.png",label: "Sleet"
                state "Snow",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/snow.png",label: "Snow"
                state "Sunny",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/sunny.png",label: "Sunny"
                state "Thunderstorms",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/tstorms.png",label: "Thunderstorms"
                state "Thunderstorm",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/tstorms.png",label: "Thunderstorm"
                state "Unknown",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/unknown.png",label: "Unknown"
                state "Overcast",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/cloudy.png",label: "Overcast"
                state "Scattered Clouds",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/partlycloudy.png",label: "Scattered Clouds"
                state "NTChance of Flurries",	icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/nt_chanceflurries.png", label: "Chance of Flurries"	
                state "NTChance of Rain",		icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/nt_chancerain.png",		label: "Chance of Rain"
                state "NTChance Rain",		icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/nt_chancerain.png",		label: "Chance Rain"
                state "NTChance of Freezing Rain",	icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/nt_chancesleet.png",label: "Chance of Freezing Rain"
                state "NTChance of Sleet",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/nt_chancesleet.png",label: "Chance of Sleet"
                state "NTChance of Snow",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/nt_chancesnow.png",label: "Chance of Snow"
                state "NTChance of a Thunderstorm",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/nt_chancetstorms.png",label: "Chance of a Thunderstorm"
                state "NTChance of Thunderstorm",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/nt_chancetstorms.png",label: "Chance of Thunderstorm"
                state "NTClear",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/nt_clear.png",label: "Clear"
                state "NTCloudy",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/nt_cloudy.png",label: "Cloudy"
                state "NTFlurries",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/nt_flurries.png",label: "Flurries"
                state "NTFog",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/nt_fog.png",label: "Fog"
                state "NTHaze",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/nt_images/hazy.png",label: "Haze"
                state "NTMostly Cloudy",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/nt_mostlycloudy.png",label: "Mostly Cloudy"
                state "NTMostly Sunny",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/nt_mostlysunny.png",label: "Mostly Sunny"
                state "NTPartly Cloudy",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/nt_partlycloudy.png",label: "Partly Cloudy"
                state "NTPartly Sunny",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/nt_partlysunny.png",label: "Partly Sunny"
                state "NTFreezing Rain",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/nt_sleet.png",label: "Freezing Rain"
                state "NTRain",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/nt_rain.png",label: "Rain"
                state "NTSleet",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/nt_sleet.png",label: "Sleet"
                state "NTSnow",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/nt_snow.png",label: "Snow"
                state "NTSunny",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/nt_sunny.png",label: "Sunny"
                state "NTThunderstorms",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/nt_tstorms.png",label: "Thunderstorms"
                state "NTThunderstorm",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/nt_tstorms.png",label: "Thunderstorm"
                state "NTUnknown",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/unknown.png",label: "Unknown"
                state "NTOvercast",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/nt_cloudy.png",label: "Overcast"
                state "NTScattered Clouds",icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/workaround_icons_png/images/nt_partlycloudy.png",label: "Scattered Clouds"}

            
            
            
            
    standardTile("refresh", "device.refresh", decoration: "flat", width: 2, height: 2) {
 		state "default", action:"refresh", icon:"st.secondary.refresh"
 		} 
	
    
    // Only used for things view in order to display a nice icon (I didn't ask permission to use it, so you can replace by what you like).
    standardTile("iaq_main", "device.iaq_main") 
    	{state "default", label:'${currentValue}', icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/new_icons/images/app-icon_bw.png" /*icon:"http://www.air-mentor.com/static/www/en/img/app-icon.png"*/, backgroundColors:[
            [value: 0, color: "#153591"],
            [value: 1, color: "#1d9114"],
            [value: 51, color: "#f7f709"],
            [value: 100, color: "#f7a709"],
            [value: 150, color: "#f70909"],
            [value: 200, color: "#5100a3"]]
   		}
	main("iaq_main")
	details(["iaqlevel","co2level","pm2_5level","pm10level","tvoclevel","temperaturecallevel","humiditylevel","EMClevel","RealFeellevel","dewpointlevel","UGW_web","UGWtemperaturecallevel","UGWhumiditylevel","UGWFeelsLikelevel","UGWdewpointlevel","UGW_Icon_UrlIcon","refresh" ])
 	}
    
}    


def installed() {
	log.debug "Executing 'installed'"
    
}

def updated() {

	log.debug "Executing 'updated'"
 	refresh()
}

def initialize() {
	state.refreshCounter = 0
    state.requestCounter = 0
    state.daylight=0
    log.debug "state.requestCounter = 0"

    log.debug "state.refreshCounter = 0"
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

    //log.debug "The device id receiving is: $device.deviceNetworkId"

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

        def co2_event = createEvent(name: "co2level",     		value: co2_int.toString())
        def pm2_5_event = createEvent(name: "pm2_5level",       value: pm2_5_int.toString())
        def pm10_event = createEvent(name: "pm10level",    		value: pm10_int.toString())
        def tvoc_event = createEvent(name: "tvoclevel",    		value: tvoc_int.toString())
        def IAQ_event = createEvent(name: "iaqlevel",     		value: iaq_int.toString())
        def IAQ_main_event = createEvent(name: "iaq_main",       value: iaq_int.toString())
        
        def temp_cal_event = createEvent(name:"temperaturecallevel",  value: convertTemperature(temp_cal_float.toFloat(),temperatureScale), unit: temperatureScale)
        def hum_event = createEvent(name: "humiditylevel", 	    value: humid_float.toString().format(java.util.Locale.US,"%.1f", humid_float.toFloat()))

		// Environmental indicators
        
        // Dew Point computation:
        // TD: =243.04*(LN(RH/100)+((17.625*T)/(243.04+T)))/(17.625-LN(RH/100)-((17.625*T)/(243.04+T)))
        // From http://andrew.rsmas.miami.edu/bmcnoldy/Humidity.html
        float dew_point_value = 243.04*(Math.log(humid_float.toFloat()/100.0)+((17.625*temp_cal_float.toFloat())/(243.04+temp_cal_float.toFloat())))/(17.625-Math.log(humid_float.toFloat()/100.0)-((17.625*temp_cal_float.toFloat())/(243.04+temp_cal_float.toFloat()))) 
		def dew_point_event =createEvent(name: "dewpointlevel", 	  value: convertTemperature(dew_point_value,temperatureScale), unit: temperatureScale)

 	    // Equilibrium Moisture Content Calculator
        def emc_W  =(349+(1.29*temp_cal_float.toFloat())+(0.0135*temp_cal_float.toFloat()*temp_cal_float.toFloat()))	
        def emc_k  = 0.805+(0.000736*temp_cal_float.toFloat())-(0.00000273*temp_cal_float.toFloat()*temp_cal_float.toFloat())	
        def emc_k1 = 6.27-(0.00938*temp_cal_float.toFloat())-(0.000303*temp_cal_float.toFloat()*temp_cal_float.toFloat())	
        def emc_k2 = 1.91+(0.0407*temp_cal_float.toFloat())-(0.000293*temp_cal_float.toFloat()*temp_cal_float.toFloat())
		def emc_humid=humid_float.toFloat()/100
		def EMC = (1800/emc_W)*(((emc_k*emc_humid)/(1-emc_k*emc_humid))+(((emc_k1*emc_k*emc_humid)+(2*emc_k1*emc_k2*emc_k*emc_k*emc_humid*emc_humid))/(1+(emc_k1*emc_k*emc_humid)+(emc_k1*emc_k2*emc_k*emc_k*emc_humid*emc_humid))))
        def EMC_event = createEvent(name: "EMClevel", value: EMC.toString().format(java.util.Locale.US,"%.1f", EMC.toFloat()))

		// Real feel indoor temperature
        float realfeel= temp_cal_float.toFloat()+0.348*emc_humid*6.105*Math.exp((17.27*temp_cal_float.toFloat())/(237.7+temp_cal_float.toFloat()))-4.25
        def Indoor_Temp_event = createEvent(name: "RealFeellevel", value: convertTemperature(realfeel,temperatureScale), unit: temperatureScale)
 
 		// UnderGround Weather references
       	def UGW_feelslike_event =  createEvent(name: "UGWFeelsLikelevel", value: convertTemperature(UGW_feelslike_float.toFloat(),temperatureScale), unit: temperatureScale)
        def UGW_DewPoint_event  =  createEvent(name: "UGWdewpointlevel", value: convertTemperature(UGW_DewPoint_float.toFloat(),temperatureScale), unit: temperatureScale)
        def UGW_Humidity_event  =  createEvent(name: "UGWhumiditylevel", value: UGW_Humidity_float.toString())
       	def UGW_Temp_event      =  createEvent(name: "UGWtemperaturecallevel", value: convertTemperature(UGW_Temp_float.toFloat(),temperatureScale), unit: temperatureScale)
        
       	def UGW_Icon_UrlIcon_event      =  createEvent(name: "UGW_Icon_UrlIcon", value: UGW_Icon_Nt.toString()+UGW_Icon_Url.toString() )
        
        
        state.refreshCounter = state.refreshCounter + 1
        log.debug state.refreshCounter

        def alert_event=[]
 		log.debug "Generating alerts if not good"
        
        def map = generate_app_event( "IAQ",iaq_int.toInteger(), 50, 100,150, 200)
        if (map) {
			alert_event = alert_event+createEvent(map)
		}
	 
        map = generate_app_event( "CO2",co2_int.toInteger(), 800, 1200,2000, 5000)
        if (map) {
			alert_event = alert_event+createEvent(map)
		}
        map = generate_app_event( "PM2_5",pm2_5_int.toInteger(), 15, 40, 65, 105)
        if (map) {
			alert_event = alert_event+createEvent(map)
		}
        map = generate_app_event( "PM10",pm10_int.toInteger(), 55, 155, 255, 355)
        if (map) {
			alert_event = alert_event+createEvent(map)
		}
        map = generate_app_event( "TVOC",tvoc_int.toInteger(), 312, 560, 1000, 3000)
        if (map) {
			alert_event = alert_event+createEvent(map)
		}
        return alert_event+[co2_event,pm2_5_event,pm10_event, tvoc_event, IAQ_event, IAQ_main_event, dew_point_event, temp_cal_event, hum_event, EMC_event, Indoor_Temp_event, UGW_feelslike_event, UGW_DewPoint_event, UGW_Humidity_event, UGW_Temp_event, UGW_Icon_UrlIcon_event, UGW_web_event] //,UGW_web_event2]     
		
        
	}
 
}

private generate_app_event( name_, int value_, int thres_moderate, int thres_unhealthy_sensitive, int thres_unhealthy, int thres_very_unhealty)
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

        fire_event=false
    }
  else
  	{
  		map.value="good"
        map.descriptionText="${name_} alert: ${map.value}"
		// change to true if want to be notified of good measures
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


            
