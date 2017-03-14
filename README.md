# AirMentorPro2_SmartThings
Update 3/14/2017: Added new environmental information: Dew point, Equilibrium Moisture Content, Indoor real feel. Added UnderGround Weather  outdoor information (temperature, humidity, real feel, dew point) 
Update 3/12/2017: fixed bug and code-cleaning in airmentorpro2.py
Update 3/11/2017: fixed temperature and humidity extraction and display. Now fully aligned with the AirMentor app results. Updated accordingly DTH groovy script, php and python scripts. 
Update 3/9/2017: added new alert event and smartapp to receive SmartThings notification in case of pollution above normal.
Update 3/5/2017: no longer need pexpect, directly use blue-py to parse broadcast notifications. Modified the python script.
Initial Commit:
What you need:
  - Raspberry PI 3 with Apach2 and PHP installed properly
  - Assign a static IP address to your raspberry on your local network. This project works only if your Hub and raspberry are on same network(otherwise the HubAction won't work and you need to implement external HTTPrequest instead)
  - USB dongle BT-LE (Plugable Dual-Mode BT-LE/BT model USB-BT4LE)
  - Installation on Raspberry of Gatttool/Bluez/Blue-pi and all dependencies
  - Installation on Raspberry of python libs: requests, pexpect (only for first version/commit of the python script
  - Put in Raspberry /var/www/html folder the airmentorpro2.php
  - Create a Device Handler in SmartThings based on code in the .groovy file then save and publish for yourself
  - Create a device in Smartthings web page based on this device handler. Put anything as Device Network Id as the Device Handler will overwrite it at first run. Don't ever change it after if your raspberry doesn't change its static IP address otherwise, the parse method is sent for some reason to the former device despite the HubAction is sent by the new instance...
  - Configure the Smarthing device with the IP, port of the Raspberry and URL of the webpage and self-refreshing regularly.You can also access the web page directly by a http://[yourraspberry IP]/airmentorpro2.php?Action=get
  
Remark 1: I am not sure I parsed correctly the temperature and humidity. Other parameters were straight forward but these 2 didn't come directly, didn't match the Sensirion sensor raw data so I had to find a logic and decided they used an offset for some reasons. If someone can find a better formula, I would be happy.
Remark 2: I didn't find any capability related to particule while some ST icons exist so if someone can share with me the right capabilities, or tell me how to request new ones, that would be great, same for VOC so that we could trigger actions based on these values. 

