#!/usr/bin/env python
#
# Philippe Portes February 2017 based
#
#   Licensed under the Apache License, Version 2.0 (the "License");
#   you may not use this file except in compliance with the License.
#   You may obtain a copy of the License at
#
#       http://www.apache.org/licenses/LICENSE-2.0
#
#   Unless required by applicable law or agreed to in writing, software
#   distributed under the License is distributed on an "AS IS" BASIS,
#   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#   See the License for the specific language governing permissions and
#   limitations under the License.
import requests
import urllib
import json
import time
import sys

class UnderGroundWeather:
    def __init__(self, key, state, city):
        print key, state, city
        self.city = city
        self.state = state
        self.key = key
        self.s=requests.Session()
        self.feelslike_c=0
        self.dewpoint_c=0
        self.relative_humidity=0
        self.temp_c=0
        self.icon_url=0
        self.icon_nt=0
        self.weather=0
    def CheckWeather(self):

        serviceUrl = 'http://api.wunderground.com/api/'+self.key+'/conditions/q/'+self.state+'/'+self.city+'.json'
        #print 'Retrieving', serviceUrl
        uh = urllib.urlopen(serviceUrl)
        try :
            data = uh.read()
            #print "============"
            #print "data: ",data
            if self.Parse(data)==0:

                try:
                    #print "Sending to php..."
                    self.Send2Web()
                except:
                    print "Error sending"
                    return -1
        except:
            print "Error parsing"
            return -1
    def Parse(self,data):
        #print "Parsing:", data
        try:
            info = json.loads(str(data))
            print(info)
            self.feelslike_c      =info[u'current_observation'][u'feelslike_c']
            self.dewpoint_c       =info[u'current_observation'][u'dewpoint_c']
            self.relative_humidity=info[u'current_observation'][u'relative_humidity']
            self.temp_c           =info[u'current_observation'][u'temp_c']
            self.icon_url         =info[u'current_observation'][u'icon']
            self.weather          =info[u'current_observation'][u'weather']
            self.icon_nt          ="nt_" if info[u'current_observation'][u'icon_url'][28:31]=="nt_" else ""
            print self.feelslike_c
            print self.dewpoint_c
            print self.relative_humidity
            print self.temp_c
            print self.icon_url
            print self.icon_nt
            print self.weather
            return 0
        except:
            info=None
        if info==None:
            print "failure to retrieve"
            return -1

    def Send2Web(self):
        payload = {}
        #print "Sending request..."
        try:
            requests.get("http://localhost/airmentorpro2.php?Action=set&UGW_FL="+str(self.feelslike_c)+"&UGW_DP="+str(self.dewpoint_c)+"&UGW_HUM="+str(self.relative_humidity)+"&UGW_TEM="+str(self.temp_c)+"&UGW_ICON="+str(self.icon_url)+"&UGW_NT="+str(self.icon_nt)+"&UGW_WEATHER="+str(self.weather), data=payload)
        except:
            print "Couldn't send request..."

def main():
    underGroundWeather_key = sys.argv[1]
    underGroundWeather_state = sys.argv[2]
    underGroundWeather_city = sys.argv[3]

    while True:
        try:
            UGW=UnderGroundWeather(underGroundWeather_key,underGroundWeather_state,underGroundWeather_city )
            UGW.CheckWeather()

            time.sleep(5*60) # delays for 5 minutes to stay in free plans provided by underGroundWeather
        except:
            pass

if __name__ == "__main__":
    main()
