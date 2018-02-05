import os
os.system('arecord -D plughw:1,0 -f cd -d 3 sound/recording.mp3')