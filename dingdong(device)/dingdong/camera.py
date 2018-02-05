from picamera import PiCamera
from time import sleep
import sys

with PiCamera() as camera:
    camera.start_preview()
    sleep(1)
    camera.capture('capture/image.jpg')
    camera.stop_preview()
    print("success")