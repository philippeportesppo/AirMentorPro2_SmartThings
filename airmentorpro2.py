#!/usr/bin/env python
# 
# Philippe Portes February 2017 based on Michael Saunby. April 2013
#
# Notes.
# pexpect uses regular expression so characters that have special meaning
# in regular expressions, e.g. [ and ] must be escaped with a backslash.
#
#   Copyright 2013 Michael Saunby
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

import pexpect
import sys
import time
import json
import select
import re
import requests

AMP_CO2=0
AMP_PM25=0
AMP_PM10=0
AMP_TVOC=0
AMP_HUM=0
AMP_TEM=0
AMP_IAQ=0
AMP_BATT=0
AMP_CHARG=0

class SensorTag:

    def __init__( self, bluetooth_adr ):
        self.con = pexpect.spawn('gatttool -b ' + bluetooth_adr + ' --interactive')
        self.con.expect('\[LE\]>', timeout=600)
        print "Preparing to connect. You might need to press the side button..."
        self.con.sendline('Connect')
        # test for success of connect
        self.con.expect('Connection successful.*\[LE\]>', timeout = 10)
        print "Connected"
        # Earlier versions of gatttool returned a different message.  Use this pattern -
        #self.con.expect('\[CON\].*>')
        self.cb = {}
        return


    # Notification handle = 0x002C value (in bytes): [CO2:2 PM2.2:2 PM10:2 Temp:2 Humid:2 TVOC: 2 IAQ:2]
    def notification_loop( self ):
        while True:
	    try:
            pnum = self.con.expect('Notification handle = .*', timeout = 60000)
            handle_byte=re.findall('Notification handle = (\S*)', self.con.after)
            print "handle_byte: ", handle_byte 
            if int(handle_byte[0],16)==0x002c:
                hex_data=re.findall('value: (.*)\r', self.con.after)
                print "hex_data: ", hex_data  
                hex_data=hex_data[0].split() 
                
                AMP_CO2=int(hex_data[0],16)*16*16+int(hex_data[1],16)
                print " AMP_CO2", AMP_CO2,
                
                AMP_PM25=int(hex_data[2],16)*16*16+int(hex_data[3],16)
                print " AMP_PM25", AMP_PM25,
                
                AMP_PM10=int(hex_data[4],16)*16*16+int(hex_data[5],16)
                print " AMP_PM10", AMP_PM10,
                
                AMP_TVOC=int(hex_data[10],16)*16*16+int(hex_data[11],16)
                print " AMP_TVOC", AMP_TVOC,
                
                if hex_data[17]!='ff':
                   AMP_IAQ=int(hex_data[12],16)*16*16+int(hex_data[13],16)
                   print " AMP_IAQ", AMP_IAQ,
                else:
                   print "No valid IAQ in paquet"
                   
                AMP_BATT=int(hex_data[16],16)
                print " AMP_BATT", AMP_BATT,

                AMP_TEM=((int(hex_data[6],16)*16*16+int(hex_data[7],16))-int('11DE',16))/100.0
                print "AMP_TEM: ", AMP_TEM,
                
                AMP_HUM=((int(hex_data[8],16)*16*16+int(hex_data[9],16))-int('23AB',16))/100.0
                print "AMP_HUM: ", AMP_HUM
                   
                payload = {}
                try:
                           s=requests.Session()
                           requests.get("http://localhost/airmentorpro2.php?Action=set&CO2="+str(AMP_CO2)+"&PM25="+str(AMP_PM25)+"&PM10="+str(AMP_PM10)+"&TEM="+str(AMP_TEM)+"&HUM="+str(AMP_HUM)+"&TVOC="+str(AMP_TVOC)+"&IAQ="+str(AMP_IAQ)+"&BATT="+str(AMP_BATT), data=payload) 
                except:
                    print "Couldn't send request..."
            except pexpect.TIMEOUT:
               print "TIMEOUT exception!"
               break
        pass

    def register_cb( self, handle, fn ):
        self.cb[handle]=fn;
        return


def main():    
    bluetooth_adr = sys.argv[1]
    print  bluetooth_adr

    while True:
     try:   
      print "[re]starting.."

      tag = SensorTag(bluetooth_adr)
      print "Ready to parse notifications"
      tag.notification_loop()
      print "Have to restart..."
     except:
      pass

if __name__ == "__main__":
    main()
