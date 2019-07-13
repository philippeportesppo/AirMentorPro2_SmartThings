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
metadata {
definition (name: "Air Mentor Pro 2", namespace: "philippeportesppo", author: "Philippe PORTES", oauth: true, ocfDeviceType: "x.com.st.airqualitylevel", mnmn: "SmartThings", vid:"generic-carbon-monoxide") {
    	capability "refresh"
        capability "polling"
        capability "sensor"
        capability "capability.airQualitySensor"
		capability "capability.carbonDioxideMeasurement"
        capability "capability.temperatureMeasurement"
        capability "capability.relativeHumidityMeasurement"
		
}

/*

preferences {
        section {
            //input "internal_ip", "text", title: "Internal IP", required: true
            //input "internal_port", "text", title: "Internal Port (80)", required: true
            //input "internal_query_path", "text", title: "Internal query Path", defaultValue: "/airmentorpro2.php?Action=get", required: true
            }		
        }
  */  
tiles(scale: 2) {

    standardTile("airQuality", "device.airQuality", width: 6, height: 3) {
        state"default", label:'${currentValue}', wordWrap: false, icon:"https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/app-icon_bw_iaq.png", backgroundColors:[
            [value: 0, color: "#153591"],
            [value: 1, color: "#1d9114"],
            [value: 51, color: "#f7f709"],
            [value: 100, color: "#f7a709"],
            [value: 150, color: "#f70909"],
            [value: 200, color: "#5100a3"]]
   		}
             
               
               
               
	standardTile("temperature", "device.temperature", width: 2, height: 2 , decoration: "flat") {
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
        
	standardTile("humidity", "device.humidity", width: 2, height: 2) {
            state "default", label: '${currentValue}%', 
                  icon: "st.Weather.weather12" , backgroundColors:[
            [value: 40, color: "#153591"],
            [value: 50, color: "#1e9cbb"],
            [value: 60, color: "#90d2a7"],
            [value: 70, color: "#44b621"],
            [value: 80, color: "#f1d801"],
            [value: 90, color: "#d04e00"],
            [value: 95, color: "#bc2323"]]
        }
        
		standardTile("carbonDioxide", "device.carbonDioxide", width: 2, height: 2,decoration: "flat") {
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
            state "default", label: '${currentValue}º',unit:'${currentValue}',icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/dewpoint.png", backgroundColor:"#888888"}
            
  	standardTile("EMClevel", "device.EMClevel",  width: 2, height: 2, decoration: "flat",canChangeIcon: false) {
            state "default",  label: '${currentValue}%',icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/EMC.png", backgroundColors:[
            [value: 0, color: "#1d9114"],
            [value: 4.5, color: "#1d9114"],
            [value: 12.5, color: "#f7f709"],
            [value: 16, color: "#f70909"]]
            }

    standardTile("RealFeellevel", "device.RealFeellevel",   width: 2, height: 2, decoration: "flat", canChangeIcon: false) {
            state "default",  label: '${currentValue}º',unit:'${currentValue}',icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/realfeel.png",backgroundColor:"#999999"}
    standardTile("Status", "device.status", width: 6, height: 2) {
 		state "default", label:'${currentValue}'
 		}         
    standardTile("TWC_web", "device.TWC_web",  width: 6, height: 3,  canChangeIcon: false ) {
            state "default", icon: "https://business.weather.com/img/the-weather-company-logo.png"  , backgroundColor: "#999999"    }   
             
    standardTile("TWCtemperaturecallevel", "device.TWCtemperaturecallevel", width: 2, height: 2, decoration: "flat", canChangeIcon: false) {
            state "default", label: '${currentValue}º',unit:'${currentValue}', icon: "st.Weather.weather2", backgroundColor:"#999999"}  
        
	standardTile("TWChumiditylevel", "device.TWChumiditylevel", width: 2, height: 2, decoration: "flat", canChangeIcon: false) {
            state "default", label: '${currentValue}%', icon: "st.Weather.weather12", backgroundColor:"#999999"      }
            
	standardTile("TWCFeelsLikelevel", "device.TWCFeelsLikelevel",  width: 2, height: 2, decoration: "flat", canChangeIcon: false) {
            state "default",  label: '${currentValue}º',unit:'${currentValue}',icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/realfeel.png", backgroundColor:"#999999"}

	standardTile("TWCdewpointlevel", "device.TWCdewpointlevel",  width: 2, height: 2, decoration: "flat", canChangeIcon: false) {
            	state "default", label: '${currentValue}º',unit:'${currentValue}',icon: "https://raw.githubusercontent.com/philippeportesppo/AirMentorPro2_SmartThings/master/images/dewpoint.png", backgroundColor:"#999999"}
    standardTile("TWC_Icon_UrlIcon", "device.TWC_Icon_UrlIcon", decoration: "flat",   width: 2, height: 2) {
    			state "na",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/na.png"
                state "0", icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/0.png"
                state "1", icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/1.png"
                state "2", icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/2.png"
                state "3", icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/3.png"
                state "4", icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/4.png"
                state "5", icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/5.png"
                state "6", icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/6.png"
                state "7", icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/7.png"
                state "8", icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/8.png"
                state "9", icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/9.png"
                state "10",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/10.png"
                state "11",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/11.png"
                state "12",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/12.png"
                state "13",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/13.png"
                state "14",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/14.png"
                state "15",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/15.png"
                state "16",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/16.png"
                state "17",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/17.png"
                state "18",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/18.png"
                state "19",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/19.png"
                state "20",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/20.png"	
                state "21",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/21.png"
                state "22",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/22.png"
                state "23",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/23.png"
                state "24",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/24.png"
                state "25",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/25.png"
                state "26",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/26.png"
                state "27",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/27.png"
                state "28",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/28.png"
                state "29",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/29.png"
                state "30",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/30.png"
                state "31",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/31.png"
                state "32",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/32.png"
                state "33",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/33.png"
                state "34",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/34.png"
                state "35",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/35.png"
                state "36",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/36.png"
                state "37",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/37.png"
                state "38",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/38.png"
                state "39",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/39.png"
                state "40",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/40.png"
                state "41",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/41.png"
                state "42",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/42.png"
                state "43",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/43.png"
                state "44",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/44.png"
                state "45",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/45.png"
                state "46",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/46.png"
                state "47",icon:"https://raw.githubusercontent.com/philippeportesppo/TheWeatherCompany_SmartThings/master/icons/47.png"
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
	main("airQuality")
	details(["airQuality","carbonDioxide","pm2_5level","pm10level","tvoclevel","temperature","humidity","EMClevel","RealFeellevel","dewpointlevel","Status","TWC_web","TWCtemperaturecallevel","TWChumiditylevel","TWCFeelsLikelevel","TWCdewpointlevel","TWC_Icon_UrlIcon","weather","refresh" ])
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
	state.requestCounter=0
 	refresh()
}

def initialize() {


}


def poll(){
	log.debug "Executing 'poll'"
    log.debug "poll state.requestCounter = ${state.requestCounter}"
    if (state.requestCounter == 1)
    {
    	sendEvent(name:"status", value:"SmartThings is not receiving any data from Airmentor. Please check the Pi is running properly or restart it", display:true)
        log.debug "SmartThings is not receiving any data from Airmentor. Please check the Pi is running properly or restart it"
    }
    else
    {
    	sendEvent(name:"status", value:"Connected to Pi and AirMentor", display:true)
    }
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

	log.debug ("parse state.requestCounter = ${state.requestCounter}")

	// Prevent parse to be executed twice (ST bug?)
	state.requestCounter = 0
	def events = []
    //log.debug "The device id receiving is: $device.deviceNetworkId"
    //log.debug description
    def msg = parseLanMessage(description)
    //log.debug msg.body
    log.debug ("parse msg.status = ${msg.status}")

    
	if (msg.status == 200)
    {
        def xmlParser = new XmlParser()
        // log.debug msg
        def html = xmlParser.parseText(msg.body)
		// log.debug msg.body
		// log.debug html
        
        if (html.body.table.tr[1].td[0].text() == "")
        	{
        		
                sendEvent(name:"status", value:"AirMentor is not sending any data. Please check connection between Pi and AirMentor", display:true)
                log.debug "AirMentor is not sending any data. Please check connection between Pi and AirMentor"
        	}
        else
            {   
                sendEvent(name:"status", value:"Connected to Pi and AirMentor")

                log.debug "CO2: ${html.body.table.tr[1].td[0].text()}"
                log.debug "PM2.5: ${html.body.table.tr[1].td[1].text()}"
                log.debug "PM10: ${html.body.table.tr[1].td[2].text()}"
                log.debug "Temperature: ${html.body.table.tr[1].td[3].text()}"
                log.debug "Temperature_Cal: ${html.body.table.tr[1].td[4].text()}"
                log.debug "humidity: ${html.body.table.tr[1].td[5].text()}"
                log.debug "TVOC: ${html.body.table.tr[1].td[6].text()}"        
                log.debug "IAQ: ${html.body.table.tr[1].td[7].text()}"
                log.debug "Battery: ${html.body.table.tr[1].td[8].text()}"
			}        
        // Now use ST 
        def mymap = getTwcConditions()
        
        def co2_int     = html.body.table.tr[1].td[0].text()
        def pm2_5_int   = html.body.table.tr[1].td[1].text()
        def pm10_int    = html.body.table.tr[1].td[2].text()
        def temp_float  = html.body.table.tr[1].td[3].text()
        def temp_cal_float  = html.body.table.tr[1].td[4].text()
        def humid_float = html.body.table.tr[1].td[5].text()  
        def tvoc_int    = html.body.table.tr[1].td[6].text()
        def iaq_int     = html.body.table.tr[1].td[7].text()  
        def battery_int = html.body.table.tr[1].td[8].text()  
        def TWC_feelslike_float  = mymap['temperatureFeelsLike']
        def TWC_DewPoint_float  = mymap['temperatureDewPoint']
        def TWC_Humidity_float = mymap['relativeHumidity'] 
        def TWC_Temp_float = mymap['temperature'] 
        def TWV_Icon_Url = mymap['iconCode']
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

        events <<  createEvent(name: "carbonDioxide",     		value: co2_int.toString())
        events <<  createEvent(name: "pm2_5level",       value: pm2_5_int.toString())
        events <<  createEvent(name: "pm10level",    		value: pm10_int.toString())
        events <<  createEvent(name: "tvoclevel",    		value: tvoc_int.toString())
        events <<  createEvent(name: "airQuality",     		value: iaq_int.toString())
        events <<  createEvent(name: "iaq_main",       value: iaq_int.toString())
        
        events <<  createEvent(name:"temperature",  value: convertTemperature(temp_cal_float.toFloat(),temperatureScale), unit: temperatureScale)
        events <<  createEvent(name: "humidity", 	    value: humid_float.toString().format(java.util.Locale.US,"%.1f", humid_float.toFloat()))

		// Environmental indicators
        
        // Dew Point computation:
        // TD: =243.04*(LN(RH/100)+((17.625*T)/(243.04+T)))/(17.625-LN(RH/100)-((17.625*T)/(243.04+T)))
        // From http://andrew.rsmas.miami.edu/bmcnoldy/Humidity.html
        float dew_point_value = 243.04*(Math.log(humid_float.toFloat()/100.0)+((17.625*temp_cal_float.toFloat())/(243.04+temp_cal_float.toFloat())))/(17.625-Math.log(humid_float.toFloat()/100.0)-((17.625*temp_cal_float.toFloat())/(243.04+temp_cal_float.toFloat()))) 
		events <<  createEvent(name: "dewpointlevel", 	 displayed:false, value: convertTemperature(dew_point_value,temperatureScale), unit: temperatureScale)

 	    // Equilibrium Moisture Content Calculator
        def emc_W  =(349+(1.29*temp_cal_float.toFloat())+(0.0135*temp_cal_float.toFloat()*temp_cal_float.toFloat()))	
        def emc_k  = 0.805+(0.000736*temp_cal_float.toFloat())-(0.00000273*temp_cal_float.toFloat()*temp_cal_float.toFloat())	
        def emc_k1 = 6.27-(0.00938*temp_cal_float.toFloat())-(0.000303*temp_cal_float.toFloat()*temp_cal_float.toFloat())	
        def emc_k2 = 1.91+(0.0407*temp_cal_float.toFloat())-(0.000293*temp_cal_float.toFloat()*temp_cal_float.toFloat())
		def emc_humid=humid_float.toFloat()/100
		def EMC = (1800/emc_W)*(((emc_k*emc_humid)/(1-emc_k*emc_humid))+(((emc_k1*emc_k*emc_humid)+(2*emc_k1*emc_k2*emc_k*emc_k*emc_humid*emc_humid))/(1+(emc_k1*emc_k*emc_humid)+(emc_k1*emc_k2*emc_k*emc_k*emc_humid*emc_humid))))
        events <<  createEvent(name: "EMClevel",  displayed:false, value: EMC.toString().format(java.util.Locale.US,"%.1f", EMC.toFloat()))

		// Real feel indoor temperature
        float realfeel= temp_cal_float.toFloat()+0.348*emc_humid*6.105*Math.exp((17.27*temp_cal_float.toFloat())/(237.7+temp_cal_float.toFloat()))-4.25
        events <<  createEvent(name: "RealFeellevel",  displayed:false, value: convertTemperature(realfeel,temperatureScale), unit: temperatureScale)
 
 		// UnderGround Weather references
       	events <<   createEvent(name: "TWCFeelsLikelevel", displayed:false, value:mymap['temperatureFeelsLike'], unit: temperatureScale)
        events <<   createEvent(name: "TWCdewpointlevel", displayed:false, value: mymap['temperatureDewPoint'], unit: temperatureScale)
        events <<   createEvent(name: "TWChumiditylevel", value: mymap['relativeHumidity'])
       	events <<   createEvent(name: "TWCtemperaturecallevel", value: mymap['temperature'], unit: temperatureScale)
       	events <<   createEvent(name: "TWC_Icon_UrlIcon", displayed:false, value: mymap['iconCode'])
         
        // Alert management    
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
		
        events << createEvent( name:"weather", value: mymap['wxPhraseMedium'], display:true, isStateChange: true)

	}

	log.debug ("end parse state.requestCounter = ${state.requestCounter}")

    return events
}

private generate_app_event( name_, int value_, previous, int thres_moderate, int thres_unhealthy_sensitive, int thres_unhealthy, int thres_very_unhealty)
{
  def map = [:] 
  def fire_event = false
  map.name = name_
  //log.debug("generate_app_event: ${name_} ${value_} ${thres_moderate} ${thres_unhealthy_sensitive} ${thres_unhealthy} ${thres_very_unhealty}")
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
    	//log.debug("Generated alert for : ${name_} ${map.value}")
  		map.isStateChange = true 
    	map.displayed = true    
    	return map
    }
  else
  	{
     	// log.debug("No alert generated for ${name_}")
    }
}

def refresh() {
	log.debug "Executing refresh"
	log.debug location.hubs[0].status
    def host = getDataValue("ip")

    def port = getDataValue("port")

    device.deviceNetworkId = "$host:$port" 
       
    def path = getDataValue("query_path")
 
    def headers = [:] 
    headers.put("HOST", "$host:$port")
 	log.debug ("refresh state.requestCounter = ${state.requestCounter}")
  try {
    def hubAction = new physicalgraph.device.HubAction(
    	method: "GET",
    	path: path,
    	headers: headers
        )
  		state.requestCounter = 1
        return hubAction
    
    }
    catch (Exception e) {
    	log.debug "Hit Exception $e on $hubAction"
        sendEvent(name:"status", value:"HubAction to Pi failed!", display:true)
        state.requestCounter = 0
    }
    
}



            
