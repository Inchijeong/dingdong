import RPi.GPIO as GPIO
from picamera import PiCamera
from time import sleep
import subprocess

GPIO.setwarnings(False)
GPIO.setmode(GPIO.BOARD)
GPIO.setup(13, GPIO.IN)         #Read output from PIR motion sensor
GPIO.setup(12, GPIO.OUT)         #LED output pin

count = 0

while count < 5:

	i=GPIO.input(13)
	if i==0:                 #When output from motion sensor is LOW
		print('No intruders : ', i)
		GPIO.output(12, 0)  #Turn OFF LED
		sleep(1)
	elif i==1:               #When output from motion sensor is HIGH
		count = count + 1
		print('Intruder detected : ',i)
		GPIO.output(12, True)  #Turn ON LED
		sleep(1)

	if count == 5:
		with PiCamera() as camera:
			camera.start_preview()
			camera.start_recording('/home/pi/Desktop/dingdong/video/video.h264')
			camera.wait_recording(3)

			camera.stop_recording()
			camera.stop_preview()
			print("success")
		from subprocess import CalledProcessError
		try:
			output = subprocess.check_output('MP4Box -add video/video.h264 -new video/video.mp4', stderr=subprocess.STDOUT, shell=True)
		except CalledProcessError as e:
			print('FAIL:\ncmd:{}\noutput:{}'.format(e.cmd, e.output))
		#subprocess.call('MP4Box -add video/video.h264 video/video.mp4', shell=True)
		GPIO.output(12, 0)  #Turn OFF LED