package com.dd.util;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Firebase {
	private static FirebaseDatabase firebaseDatabase;
	private void init() {
		try {
			FileInputStream serviceAccount = new FileInputStream("D:\\DevInfo\\dingdong-34347-firebase-adminsdk-ve0y2-6dee882463.json");
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
					.setDatabaseUrl("https://dingdong-34347.firebaseio.com/")
					.build();
			
			FirebaseApp.initializeApp(options);
			firebaseDatabase = FirebaseDatabase.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void putBellId(String bellToken) {
		if(firebaseDatabase == null) {
			new Firebase().init();
		}
		
		try {
			
			DatabaseReference refb = firebaseDatabase.getReference();
			DatabaseReference bellRef = refb.child(bellToken);
			Map<String, String> bell = new HashMap<String, String>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			bell.put("bellID", bellToken);
			bell.put("timestamp",sdf.format(new Date()));
			bellRef.setValue(bell);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void putResVoiceInfo(String bellId, String resVoicePath) {
		if(firebaseDatabase == null) {
			new Firebase().init();
		}
		
		try {
			DatabaseReference refb = firebaseDatabase.getReference();
			DatabaseReference bellRef = refb.child(bellId);
			Map<String, String> bell = new HashMap<String, String>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			bell.put("resVoicePath", resVoicePath);
			bell.put("timestamp",sdf.format(new Date()));
			bellRef.setValue(bell);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
