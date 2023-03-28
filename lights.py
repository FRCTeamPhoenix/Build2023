import time
import random
import board
import adafruit_dotstar as dotstar
from networktables import NetworkTables
import logging

#Constants
#Colors
Colors = {
"red" : (255,0,0),
"yellow" : (255,255,0), #yellowish green
"purple" : (153,0,153),
"orange" : (255,128,0),
"red_orange" : (255,69,0),
"red_bright" : (255,51,51),
"dark_orange" : (255,140,0), #yellow
"blue" : (51,51,255),
"crimson" : (220,20,60),
"gold" : (255,215,0),
"green" : (0,255,0),
"off" : (0,0,0)
}

thingAmaBobPattern = ["red","off","off","red_orange","red_orange",,"red_orange",,"red_orange",,"red_orange","off","off"]

#Server
Server = "10.23.42.2"


# HELPERS
# a random color 0 -> 192
def random_color():
	return random.randrange(0, 7) * 32

def flicker():
	return random.randint(200, 255)

def fire_effect():
	for i in range(len(dots)):
		dots[i] = FIRE_COLORS[random.randint(0, len(FIRE_COLORS)-1)]
	for i in range(len(dots)):
		r = flicker()
		g = flicker() // 2
		dots[i] = (r, g, 0)

def thingAmaBob(led):
	for x in range(10):
		if((led+x) in range(0,240)):
			dots[led+x]=Colors[thingAmaBobPattern[x]]

def team_color():
	n_dots = len(dots) 
	for current in range(10):
		thingAmaBob(current-10)
		for led in range(24):
			thingAmaBob((led*10)+current)
			#Bail out if the color changed.
			nt_color = ColorTable.getString("color","team")
			if (nt_color!="team"):
				return
	dots.fill(Colors["red"]);


# Using a DotStar Digital LED Strip with 60*4 (240 leds) LEDs connected to hardware SPI
dots = dotstar.DotStar(board.SCK, board.MOSI, 240, brightness=0.1)
FIRE_COLORS = [(255, 10, 0), (255, 60, 0), (255, 100, 0), (255, 150, 0), (255, 200, 0), (255, 255, 0)]

# Start Loggign for pubsub
logging.basicConfig(level=logging.DEBUG)

# Setup Network Tables
while (not NetworkTables.isConnected()) :
	NetworkTables.initialize(server=Server)
	fire_effect()

ColorTable = NetworkTables.getTable("SmartDashboard")
ColorTable.putString("color","team")

# MAIN LOOP
while True:
	try:
		while (not NetworkTables.isConnected()) :
			NetworkTables.initialize(server=Server)
			fire_effect()

		nt_color = ColorTable.getString("color","team")
		if (nt_color == "team"):
			team_color()
		else:
			dots.fill(Colors[nt_color])

		time.sleep(.1)
	except KeyboardInterrupt:
		exit()
	except Exception as e:
		print(f"Error: {e}")

