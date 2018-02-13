#The MIT License (MIT)
#Copyright (c) 2016 Erwan Martin <public@fzwte.net>
#Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
#The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
#THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

# adapter by Philippe Portes 2018 from orignal ssdp_server.py written by ZeWarren on https://github.com/ZeWaren/python-upnp-ssdp-example/blob/master/__main__.py

import sys
sys.path.append("/home/pi/lib")

from ssdp import SSDPServer

from upnp_http_server import UPNPHTTPServer

import uuid

import netifaces as ni

from time import sleep

#import logging



NETWORK_INTERFACE = 'wlan0'



#logger = logging.getLogger()

#logger.setLevel(logging.DEBUG)





#def setup_debugging():
#
#    """
#
#    Load PyCharm's egg and start a remote debug session.
#
#    :return: None
#
#
#    """

#    import sys

#    sys.path.append('/root/pycharm-debug-py3k.egg')

#    import pydevd

#    pydevd.settrace('192.168.1.49', port=5422, stdoutToServer=True, stderrToServer=True, suspend=False)





#setup_debugging()





def get_network_interface_ip_address(interface='wlan0'):

    """

    Get the first IP address of a network interface.

    :param interface: The name of the interface.

    :return: The IP address.

    """


    while interface not in ni.interfaces():

        print('Could not find interface %s.' % (interface,))

        sleep(10)



    while True:
        interfacestring = ni.ifaddresses(interface)

        if (2 not in interfacestring) or (len(interfacestring[2]) == 0):

            print('Could not find IP of interface %s. Sleeping.' % (interface,))

            sleep(10)

            continue
        else:
            break
    return interfacestring[2][0]['addr']


print "Start..."


device_uuid = uuid.uuid4()

print "Device_uuid:", device_uuid

local_ip_address = get_network_interface_ip_address(NETWORK_INTERFACE)

print local_ip_address

http_server = UPNPHTTPServer(8090,

                             friendly_name="Air Mentor Pro 2",

                             manufacturer="Air Mentor",

                             manufacturer_url='https://www.air-mentor.com/web2017/?lang=en',

                             model_description='Air Mentor Pro 2',

                             model_name="Air Mentor",

                             model_number="Air Mentor Pro 2",

                             model_url="https://www.air-mentor.com/web2017/product/air_mentor_8096ap",

                             serial_number="N/A",

                             uuid=device_uuid,

                             presentation_url="http://{}:5000/".format(local_ip_address))

http_server.start()

print "Server started"

ssdp = SSDPServer()

print "SSDP started"

ssdp.register('local',

              'uuid:{}::urn:schemas-upnp-org:device:AirMentorPro2:1'.format(device_uuid),

              'urn:schemas-upnp-org:device:AirMentorPro2:1',

              'http://{}:8090/airmentorpro2.xml'.format(local_ip_address))

print "SSDP registered"

ssdp.run()
