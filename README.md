# AirMentorPro2_SmartThings

<img src="https://github.com/philippeportesppo/AirMentorPro2_SmartThings/blob/master/overallsetup.png" alt="Overall Setup Icon" style="width:50%;height:50%;">

What you need:

Raspberry PI 3 with Apache2 and PHP5 installed properly<p></p>
  Raspberry Pi PHP and Apache installation instructions (https://www.raspberrypi.org/documentation/remote-access/web-server/apache.md)
Assign a static IP address to your raspberry on your local network. This project works only if your Hub and raspberry are on same network(otherwise the HubAction won't work and you need to implement external HTTPrequest instead)<p></p>
Optional: 1 USB dongle BT-LE (Plugable Dual-Mode BT-LE/BT model USB-BT4LE)  I didn't make it with the internal BT-LE of the Pi using the oringinal image on it, I used this external one then. Then recently, the Raspberry Pi internal BTLE works fine using Linux raspberrypi 4.4.50-v7+ #970 SMP Mon Feb 20 19:18:29 GMT 2017 armv7l GNU/Linux< image from the Raspberry website <p></p>
Additional installation on Raspberry:<p></p>
  <li>Bluez (http://www.elinux.org/RPi_Bluetooth_LE)<p></p></li>
  <li>BluePy (https://github.com/IanHarvey/bluepy)<p></p></li>
  <li>requests (http://raspberrypi-aa.github.io/session4/requests.html)<p></p></li>
Put in Raspberry /var/www/html folder the file : airmentorpro2.php<p></p>
Put in /home/pi/Documents the python script airmentorpro2.py<p></p>
You will launch this first python script by: <b>sudo /usr/bin/python /var/www/html/airmentorpro2.py [your AirMentor MAC] [your hci#] & </b><p></p>Example: sudo /usr/bin/python /var/www/html/airmentorpro2.py fe:ed:fa:ce:be:ef 0 & <p></p>As this script runs an infinit loop, better to fork it with &<p></p>
Put in /home/pi/Documents the python script undergroundweather.py<p></p>
This requires you to get a Weather UnderGround API key from https://www.wunderground.com/weather/api/<p></p>The information is used to provide more data about outside conditions. If you don't want to use this, check the previous versions of the DTH and html page on this GitHub.<p></p>
<p></p> You will launche this script by: <b>sudo /usr/bin/python /var/www/html/undergroundweather.py [yourAPI key] [state] [city] &</b>
<p>As this script runs an infinit loop, better to fork it with & too</p>
In Smartthings IDE: Create a Device Handler (then save and publish for yourself) from air-mentor-pro-2.groovy <p></p>
In Smartthings IDE: Create a SmartApp (then save and publish for yourself) from SmartApp.groovy. The Smartapp is here to allow the alerting on pollution level and polluant. This is an optional app, if you don't want to be notified, you can ignore it.<p>
In Smartthings IDE: Create a SmartApp (then save and publish for yourself) from iaq-vent.groovy. The Smartapp is here to allow piloting vents/swtiches based on selected levels of IAQ</p>
Create a device in Smartthings web page based on this device handler. Put anything as Device Network Id as the Device Handler will overwrite it at first run. Don't ever change it after if your raspberry doesn't change its static IP address otherwise, the parse method is sent for some reason to the former device despite the HubAction is sent by the new instance...<p></p>
Configure the Smarthing device with the IP, port of the Raspberry and URL of the webpage and self-refreshing regularly.You can also access the web page directly by a http://[yourraspberry IP]/airmentorpro2.php?Action=get<p></p>
<b>IMPORTANT:</b> use pollster smartapp to cadence the polling (every 5min) otherwise, Smartthing known issue will let the DTH stoping the polling after 24h or so.<p></p>
Hope you like it.
