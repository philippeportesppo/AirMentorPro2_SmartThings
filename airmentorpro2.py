#!/usr/bin/env python
# 
# Philippe Portes February 2017 based on Michael Saunby. April 2013
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
import sys
import requests
import math
from bluepy.btle import Scanner, DefaultDelegate

 
class AirMentorProDelegate(DefaultDelegate):
    def __init__(self, bluetooth_adr):
        self.adr = bluetooth_adr
	self.s=requests.Session()
	self.AMP_CO2=0
	self.AMP_PM25=0
	self.AMP_PM10=0
	self.AMP_TVOC=0
	self.AMP_HUM=0.0
	self.AMP_TEM=0.0
	self.AMP_IAQ=0
	self.AMP_BATT=0
	self.AMP_CHARG=0
        self.AMP_TEMP_CAL=0.0
        DefaultDelegate.__init__(self)

    def handleDiscovery(self, dev, isNewDev, isNewdata):
        #print "Notification received #2"
	if dev.addr == self.adr:
	    for scan in dev.getScanData():
		
            	if scan[0] == 0xff: # Proprietary
	            payload = {}
			      
                    if int(scan[2][1:3],16)==0x22:			      
                        print scan,
			hex_data = scan[2]     
		        #      [TVOC][Temp][TempDelta][Humi][IAQ ]
			#[2221][00b9][1963][33       ][  2d][0145]			      
                   
			self.AMP_TVOC=int(hex_data[4:8],16)
                   	print "AMP_TVOC: ",self.AMP_TVOC,

	                self.AMP_TEM=(float(int(hex_data[8:12],16))-4000)*0.01
		   	print "AMP_TEM: ",self.AMP_TEM,

                        self.AMP_TEMP_CAL=self.AMP_TEM-(float(int(hex_data[12:14],16)))*0.1
			print "AMP_TEMP_CAL: ",self.AMP_TEMP_CAL,

		   	self.AMP_HUM=float(int(hex_data[14:16],16))*math.exp(self.AMP_TEM*17.62/(self.AMP_TEM+243.12))/math.exp(self.AMP_TEMP_CAL*17.62/(self.AMP_TEMP_CAL+243.12))     
                   	print "AMP_HUM: ",self.AMP_HUM,
                   	
 			self.AMP_IAQ=int(hex_data[16:],16)
                        print "AMP_IAQ: ",self.AMP_IAQ

		    	try:
	                   requests.get("http://localhost/airmentorpro2.php?Action=set&CO2="+str(self.AMP_CO2)+"&PM25="+str(self.AMP_PM25)+"&PM10="+str(self.AMP_PM10)+"&TEM_ACT="+str(self.AMP_TEM)+"&TEM_CAL="+str(self.AMP_TEMP_CAL)+"&HUM="+str(self.AMP_HUM)+"&TVOC="+str(self.AMP_TVOC)+"&IAQ="+str(self.AMP_IAQ)+"&BATT="+str(self.AMP_BATT), data=payload) 
			except:
                       	   print "Couldn't send request..."
                                                   
		    else:
			if int(scan[2][1:3],16)==0x12:
			    hex_data = scan[2]      
			    #       [CO2 ][PM25][PM10][CO O3]
			    # [2121][2710][0003][0003][00 00]
                            print scan,
			    self.AMP_CO2=int(hex_data[4:8],16)
		            print "AMP_CO2",self.AMP_CO2,

	                    self.AMP_PM25=int(hex_data[8:12],16)
	                    print "AMP_PM25",self.AMP_PM25,

	                    self.AMP_PM10=int(hex_data[12:16],16)
	                    print "AMP_PM10",self.AMP_PM10
            
                            #AMP_BATT=int(hex_data[16],16)
                            #print "self.AMP_BATT",self.AMP_BATT,
                            try:
	                        requests.get("http://localhost/airmentorpro2.php?Action=set&CO2="+str(self.AMP_CO2)+"&PM25="+str(self.AMP_PM25)+"&PM10="+str(self.AMP_PM10)+"&TEM_ACT="+str(self.AMP_TEM)+"&TEM_CAL"+str(self.AMP_TEMP_CAL)+"&HUM="+str(self.AMP_HUM)+"&TVOC="+str(self.AMP_TVOC)+"&IAQ="+str(self.AMP_IAQ)+"&BATT="+str(self.AMP_BATT), data=payload) 
			    except:
                       	        print "Couldn't send request..."
                #else:
                #    print "[",scan[0],":", scan[2], "]"			 


def main():
    
    bluetooth_adr = sys.argv[1].lower()

    print  bluetooth_adr

    while True:
        try:   
            # BTLE UUSB is on hci1, so pass 1 to Scanner
            scanner = Scanner(1).withDelegate(AirMentorProDelegate(bluetooth_adr))

            

   	    while(1):
	        scanner.start()
	        scanner.process(1)
	        scanner.stop()
                

        except:
            pass

if __name__ == "__main__":
    main()
