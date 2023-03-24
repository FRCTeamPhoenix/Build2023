import time
import random
import board
import adafruit_dotstar as dotstar
from networktables import NetworkTables
import logging


# Using a DotStar Digital LED Strip with 60*4 (240 leds) LEDs connected to hardware SPI
dots = dotstar.DotStar(board.SCK, board.MOSI, 240, brightness=0.1)

# Start Loggign for pubsub
logging.basicConfig(level=logging.DEBUG)

# Setup Network Tables
NetworkTables.initialize(server="10.23.42.2")
ColorTable = NetworkTables.getTable("SmartDashboard")
ColorTable.putString("color","team")
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
# HELPERS
# a random color 0 -> 192
def random_color():
	return random.randrange(0, 7) * 32

def team_color():
	n_dots = len(dots)
	dots[0]=Colors["red"];
	dots[1]=Colors["off"];
	dots[2]=Colors["off"];
	dots[3]=Colors["red_orange"];
	dots[4]=Colors["red_orange"];
	dots[5]=Colors["red_orange"];
	dots[6]=Colors["red_orange"];
	dots[7]=Colors["red_orange"];
	dots[8]=Colors["red_orange"];
	dots[9]=Colors["off"];
	dots[10]=Colors["off"];
	for led in range(6,len(dots)):
		dots[led-10]=Colors["red"];
		dots[led-9]=Colors["off"];
		dots[led-8]=Colors["off"];
		dots[led-7]=Colors["red_orange"];
		dots[led-6]=Colors["red_orange"];
		dots[led-5]=Colors["red_orange"];
		dots[led-4]=Colors["red_orange"];
		dots[led-3]=Colors["red_orange"];
		dots[led-2]=Colors["red_orange"];
		dots[led-1]=Colors["off"];
		dots[led]=Colors["off"];
		#Bail out if the color changed.
		nt_color = ColorTable.getString("color","team")
		if (nt_color!="team"):
			return
	dots[229]=Colors["red"];
	dots[230]=Colors["red"];
	dots[231]=Colors["red"];
	dots[232]=Colors["red"];
	dots[233]=Colors["red"];
	dots[234]=Colors["red"];
	dots[235]=Colors["red"];
	dots[236]=Colors["red"];
	dots[237]=Colors["red"];
	dots[238]=Colors["red"];
	dots[239]=Colors["red"];
	# dots[240]=Colors["red"];
#	dots.fill(Colors["red_orange"])

# MAIN LOOP
while True:
	try:
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

