var GPIO = require('onoff').Gpio;
var	button = new GPIO(17, 'in','both');
var	motion = new GPIO(27, 'in','both');
var PythonShell = require('python-shell');
var fetch = require('node-fetch');
var fs = require('fs');
var FormData = require('form-data');
var sleep = require('system-sleep');
var express = require('express');
var app = express();
var firebase = require('firebase');
var admin = require('firebase-admin');
var player = require('play-sound')({player:'omxplayer'});

var form = new FormData();
var ddkey = null;

var sendFile = function (file){
    fetch('http://192.168.0.62:8000/notify', {
        method: 'POST',
        body: form
    }).then(function(res){
        return res.json();
    }).then(function(json){
        console.log(json);
		form = new FormData();
    }).catch(function(err){
        console.log(err);
    });
}; 

app.get('/check', function (req, res) {
    var result = null;
    fetch('http://192.168.0.25:8070/ddkey', {
        headers: {'Content-type': 'application/text'},
        method: 'POST',
    }).then(function(res){
        return res.json();
    }).then(function(json){
		console.log(json.key);
		ddkey = json.key;
		fs.writeFile('ddkey/key.txt', ddkey, function(err) {
			if(err) throw err;
			console.log('DDkey saving completed!!');
		});
        res.header("Access-Control-Allow-Origin", "*");
        res.header("Access-Control-Allow-Headers", "X-Requested-With");
        res.send(json);
    }).catch(function(err){
        console.log("err : " + err);
    });
});

app.listen(3000, function () {
	
	var exceptFirst = 0;

	if(fs.existsSync('ddkey/key.txt')){
		fs.readFile('ddkey/key.txt', function (err, data) {
			if(err) throw err;
			ddkey = data.toString();
		});
	}

	setTimeout(function(){
		// Firebase
	    var serviceAccount = require("/home/pi/Desktop/dingdong/dingdong-34347-firebase-adminsdk-ve0y2-6dee882463.json");
	
	    admin.initializeApp({
	        credential: admin.credential.cert(serviceAccount),
	        databaseURL: "https://dingdong-34347.firebaseio.com/"
		});
	
		if (ddkey){
		    var db = admin.database();
		    var ref = db.ref(ddkey);
		
		    ref.on('value', function(snapshot) {
				if(exceptFirst > 0){
					var resVoice = snapshot.val().resVoicePath;
					player.play('http://192.168.0.62:8000/resources/' + resVoice, function (err) {
						if (err) throw err;
					});
				}
				exceptFirst++;
			});
	
		}
		// End Firebase
	}, 100);

   	function motion(){
		console.log("Running Motion Function")
		PythonShell.run('motion_camera.py', function(err, results){
		if(err) throw err;
		console.log('motion results: %s' , results);	

        var video = null;
		video = fs.ReadStream('/home/pi/Desktop/dingdong/video/video.mp4');
        if (video != null){
            sendFile(video);
			console.log("Send Video");
	       }
		return motion();
		});
	}

	function light(err, state){
		if(state == 1 ){
			console.log("Button Off");
		}else if(state ==0){
			console.log("Button On");
			form.append('deviceID', ddkey);
			PythonShell.run('dingdongsound.py', function(err, results) {
				if(err) throw err;
				console.log('results: %s' , results);
			});

			sleep(2000);
	
			PythonShell.run('camera.py', function(err, results){
				if(err) throw err;
				console.log('results: %s' , results);	
				
				var picture = null;
	        	picture = fs.ReadStream('/home/pi/Desktop/dingdong/capture/image.jpg');
	        
	        	if (picture != null){
	            	form.append('files', picture);
					console.log("Send Picture");
	      			}
			});

			sleep(2000);

			PythonShell.run('whoru.py', function(err, results) {
				if(err) throw err;
				console.log('results: %s' , results);
			});

			PythonShell.run('audio_recording.py', function(err, results) {
				console.log('results: %s' , results);
				var audio = null;
		        audio = fs.ReadStream('/home/pi/Desktop/dingdong/sound/recording.mp3');
		        
	        	if (audio != null){
					form.append('files', audio);
					console.log("Send Audio");
					sendFile(form);
	      			}
			});
		}
	}

	button.watch(light);
	motion();

});        