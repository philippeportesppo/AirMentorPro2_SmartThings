# AirMentorPro2_SmartThings
What you need:

Raspberry PI 3 with Apach2 and PHP installed properly<p></p>
  Raspberry Pi PHP and Apache installation instructions (https://www.raspberrypi.org/documentation/remote-access/web-server/apache.md)
Assign a static IP address to your raspberry on your local network. This project works only if your Hub and raspberry are on same network(otherwise the HubAction won't work and you need to implement external HTTPrequest instead)<p></p>
1 USB dongle BT-LE (Plugable Dual-Mode BT-LE/BT model USB-BT4LE)  I didn't make it with the internal BT-LE of the Pi, I used this external one.<p></p>
Additional installation on Raspberry:<p></p>
  <li>Bluez (http://www.elinux.org/RPi_Bluetooth_LE)<p></p></li>
  <li>BluePy (https://github.com/IanHarvey/bluepy)<p></p></li>
  <li>requests (http://raspberrypi-aa.github.io/session4/requests.html)<p></p></li>
Put in Raspberry /var/www/html folder the file : airmentorpro2.php airmentorpro2.php<p></p>
Put in /home/pi/Documents the python script airmentorpro2.pyYou will launch this first python script by: python airmentorpro2.py [your AirMentor MAC] &As this script runs an infinit loop, better to fork it with &<p></p>
Put in /home/pi/Documents the python script undergroundweather.py   This requires you to get a Weather UnderGround API key from https://www.wunderground.com/weather/api/  The information is used to provide more data about outside conditions. If you don't want to use this, check the previous versions of the DTH and html page on my GitHub.<p></p>
In Smartthing IDE: Create a Device Handler (then save and publish for yourself) from AirMentorDTH.groovy <p></p>
In Smartthing IDE: Create a SmartApp (then save and publish for yourself) from SmartApp.groovy. The Smartapp is here to allow the alerting on high and very high pollution. You can tweak the smartappto also get alerts on medium pollution.<p></p>
Create a device in Smartthings web page based on this device handler. Put anything as Device Network Id as the Device Handler will overwrite it at first run. Don't ever change it after if your raspberry doesn't change its static IP address otherwise, the parse method is sent for some reason to the former device despite the HubAction is sent by the new instance...<p></p>
Configure the Smarthing device with the IP, port of the Raspberry and URL of the webpage and self-refreshing regularly.You can also access the web page directly by a http://[yourraspberry IP]/airmentorpro2.php?Action=get<p></p>
<b>IMPORTANT:</b> use pollster smartapp to candence the polling (every 5min) otherwise, Smartthing known issue will let the DTH stoping the polling after 24h or so.<p></p>
Hope you like it.
