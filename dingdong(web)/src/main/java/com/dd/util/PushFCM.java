package com.dd.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.dd.domain.VisitorFiles;
import com.dd.util.KmeansUtil.Point;

import lombok.extern.java.Log;

@Log
public class PushFCM {
	public final static String AUTH_KEY_FCM = "AAAAkhVzq4s:APA91bHPsxnoIM4jKldyBfKjObweKH0ZnltRXV64lmK9JMgtmnpi3GaJwi2d6JEbdq-QBwl8MJcshkYfFIgi_jhhkgHqZaqLDSbC68tUpHZizK1fgDLmK1yB382hu5wf4g-W54mWCMm5"; //server key
	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
	
	public static String sendPushNotification(List<String> tokenList, VisitorFiles vf, List<Float> hours) throws IOException {
		System.out.println("sendPushNotification start!!!");
	    String result = "";
	    URL url = new URL(API_URL_FCM);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	
	    conn.setUseCaches(false);
	    conn.setDoInput(true);
	    conn.setDoOutput(true);
	
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);
	    conn.setRequestProperty("Content-Type", "application/json");

	    // 패턴 분석
		int k = 4; // 생성할 개수 지정
		double timeRange = 1; // 시간 범위 지정
		
		// 현재 시간
		String hour = new java.text.SimpleDateFormat("HH").format(new java.util.Date());
        String minute = new java.text.SimpleDateFormat("mm").format(new java.util.Date());
        float fh = Float.parseFloat(hour);
        float fm = Math.round(Float.parseFloat(minute) / 60f * 10f) / 10f;
        float curTime = fh + fm;
		
		Point newHour = new Point(curTime); // 새로 들어온 사람의 방문 시간
		
		List<Point> dataset= new ArrayList<>();
		
		try {
			dataset = KmeansUtil.changePoint(hours);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Point> centers = KmeansUtil.initializeRandomCenters(k, 0, 24);		
		List<Point> kmeansCenterList = KmeansUtil.kmeans(centers, dataset, k);


		boolean warning = KmeansUtil.checkWarning(kmeansCenterList, newHour, timeRange);
		
		String titleText = "새 방문자 알림";
		String bodyText = "손님이 오셨어요!";
		
		if (warning) {
			System.out.println("위험한 방문자");
			titleText = "수상한 방문자 알림";
			bodyText = "꼭 확인해 주세요!";
		}    
	    // 패턴 분석 끝
		
	    try {
	    	
	    	JSONObject json = new JSONObject();
	    	InetAddress ip = InetAddress.getLocalHost(); 
	    	json.put("registration_ids", tokenList);	//다중 FCM 발송 시 registration_ids 사용. 단일 발송 시 to 사용
	    	JSONObject info = new JSONObject();
	    	info.put("title", titleText);
	    	info.put("body", bodyText);
	    	info.put("click_action",  "https://"+ ip.getHostAddress() +":8443/visitorFiles?fn="+vf.getVisitFileNo());
	    	json.put("notification", info);
	    	
	        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
	        wr.write(json.toString());
	        wr.flush();
	
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	
	        String output;
	        System.out.println("Output from Server .... \n");
	        while ((output = br.readLine()) != null) {
	            System.out.println(output);
	        }
	        result = "success";
	    } catch (Exception e) {
	        e.printStackTrace();
	        result = "fail";
	    }
	    System.out.println("GCM Notification is sent successfully");
	
	    return result;
	}
}