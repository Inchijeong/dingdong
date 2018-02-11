/**
 * @author Inchijeong
 */
package com.dd.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URI;

import javax.imageio.ImageIO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.dd.domain.Visitor;

//This sample uses the Apache HTTP client library(org.apache.httpcomponents:httpclient:4.2.4)
//and the org.json library (org.json:json:20170516).


public class CognitiveServiceUtil {
	// **********************************************
	// *** Update or verify the following values. ***
	// **********************************************
	
	// Replace the subscriptionKey string value with your valid subscription key.
	public static final String subscriptionKey = "ded527466d99423a8aebcb89f9989f5a";
	
	// Replace or verify the region.
	//
	// You must use the same region in your REST API call as you used to obtain your subscription keys.
	// For example, if you obtained your subscription keys from the westus region, replace
	// "westcentralus" in the URI below with "westus".
	//
	// NOTE: Free trial subscription keys are generated in the westcentralus region, so if you are using
	// a free trial subscription key, you should not need to change this region.
	public static final String uriBase = "https://westcentralus.api.cognitive.microsoft.com/face/v1.0/detect";

	public Visitor cognitiveService(String filePath, Visitor visitor){
		
	    HttpClient httpclient = new DefaultHttpClient();
	    
	    try {
	        URIBuilder builder = new URIBuilder(uriBase);
	
	        builder.setParameter("returnFaceId", "false");
	        builder.setParameter("returnFaceLandmarks", "false");
	        builder.setParameter("returnFaceAttributes", "age,gender");
	
	        URI uri = builder.build();
	        HttpPost request = new HttpPost(uri);
	
	        request.setHeader("Content-Type", "application/octet-stream");
	        request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);
	
	        ByteArrayEntity bae = null;
	        try {
		        BufferedImage img = ImageIO.read(new File(filePath));             
		        ByteArrayOutputStream baos = new ByteArrayOutputStream();
		        ImageIO.write(img, "jpg", baos);
		        baos.flush();
		        baos.close();
		        
		        bae = new ByteArrayEntity(baos.toByteArray());
	        } catch(Exception e){
	        	e.printStackTrace();
	        }
	        
	        request.setEntity(bae);
	
	        HttpResponse response = httpclient.execute(request);
	        HttpEntity entity = response.getEntity();
	
	        if (entity != null){
	            String jsonString = EntityUtils.toString(entity).trim();
	            JSONObject jsonObject = new JSONObject(jsonString.replace("[", "").replace("]", ""));
	            String gender = (String)new JSONObject(jsonObject.get("faceAttributes").toString()).get("gender");
	            Double age = (Double)new JSONObject(jsonObject.get("faceAttributes").toString()).get("age");
	            Integer iAge = (int)(long)Math.round(age);
	            visitor.setGeneration(iAge / 10 * 10);
	            visitor.setAge(iAge);
	            visitor.setGender(gender);
	        }
	    }
	    catch (Exception e){
	        System.out.println(e.getMessage());
	    }
	    return visitor;
	}
}