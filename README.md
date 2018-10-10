<img src="https://github.com/philippeportesppo/AirMentorPro2_SmartThings/blob/master/overallsetup.png" alt="Overall Setup Icon" style="width:50%;height:50%;">

<h2>Raspberry Pi 3 side:</h2>

<h3>1. Apache2 and PHP5 properly installed <a href="https://www.raspberrypi.org/documentation/remote-access/web-server/apache.md"> (see here) </a></h3> <p>
Assign a static IP address to your raspberry on your local network. This project works only if your Hub and raspberry are on same network (otherwise the HubAction won't work and you need to implement external HTTPrequest instead)<p></p>
Optional: 1 USB dongle BT-LE (Plugable Dual-Mode BT-LE/BT model USB-BT4LE)  I didn't make it with the internal BT-LE of the Pi using the oringinal image on it, I used this external one then. Then recently, the Raspberry Pi internal BTLE works fine using Linux raspberrypi 4.4.50-v7+ #970 SMP Mon Feb 20 19:18:29 GMT 2017 armv7l GNU/Linux< image from the Raspberry website (or above)<p></p>
<h3>2. Additional installation on Raspberry:<p></h3>
  <h4>Bluez (http://www.elinux.org/RPi_Bluetooth_LE)<p></p>
  BluePy (https://github.com/IanHarvey/bluepy)<p></p>
  requests (http://raspberrypi-aa.github.io/session4/requests.html)<p></p>
  netifaces (python -m pip install netifaces)  <p></p></h4>
<h3>3. This project files installation:<p>    </h3>
<li>Put in Raspberry /var/www/html folder the file : airmentorpro2.php</li>
<li>Put in /home/pi the python script airmentorpro2.py</li>
<li>Put in /home/pi the python script ssdp_server.py (this file uses wlan0 as interface. You can change the code to use eth0 or other)</li>
<li>Create a folder "lib" in /home/pi</li>
<li>Put in /home/pi/lib the 2 files ssdp.py and upnp_http_server.py.</li>
This is mandatory the 2 files are in a lib folder and the lib folder at the same location as ssdp_server.py
<li>In <b>/etc/rc.local</b>, just before the exit 0 (last line):</li> 
<li>add: <b>sudo /usr/bin/python /home/pi/airmentorpro2.py [your AirMentor MAC] [your hci#] & </b><p></p>Example: sudo /usr/bin/python /home/pi/airmentorpro2.py fe:ed:fa:ce:be:ef 0 & <p></p></li>

  <li>add: <b>/usr/bin/python /home/pi/ssdp_server.py &</b></li>

<h2>Smarthings IDE side:<p></h2>

<li> with a github enabled SmartThings IDE (see <a href=http://docs.smartthings.com/en/latest/tools-and-ide/github-integration.html > here</a>), import the namespace philippeportesppo and repository AirMentorPro2_SmartThings on master branch</li> and the following device handlers and smartapps:
<h3>SmartApps:</h3>
<li>AirMentor Pro UPnP Service Manager : the smartapp managing the ssdp discover.</li>
<li>IAQ_vent : the smartapp managing vents and AC fans upon air quality notification</li>
<li>Notify Me When for AirMentor Pro : the smartapp managing events and notify you about the air quality.</li>
<h3>Device Handler:</h3>
<li>Air Mentor Pro 2 : the device handler to access the AirMentor Pro 2</li>
<p>
If you cannot access github integration, you might have to create the devicehandler and smartapps manually from the code.

<h2>SmartThing Mobile app:<p></h2>
  <li>Go to Smartapps section and add the <b>AirMentor Pro UPnP Service Manager</b> smartapp from "My Apps"</li>
  <li>Start the research, few seconds later the pi will be discovered, select it, press next and save.</li>
  <li>AirMentor Pro 2 will be added
    

<h3><b>IMPORTANT:</b></h3> use pollster smartapp to cadence the polling (every 5min) otherwise, Smartthing known issue will let the DTH stoping the polling after 24h or so.<p></p>
Hope you like it.

[![Support via PayPal](https://cdn.rawgit.com/twolfson/paypal-github-button/1.0.0/dist/button.svg)](https://www.paypal.me/philippeportesppo)
