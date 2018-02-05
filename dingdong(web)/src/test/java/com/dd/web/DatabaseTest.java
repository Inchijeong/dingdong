package com.dd.web;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dd.domain.VisitorFile;
import com.dd.service.ResVoiceService;
import com.dd.service.VisitorFileService;

import lombok.extern.java.Log;

@Log
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
   locations ={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DatabaseTest {
	
	@Autowired
	private ResVoiceService service;
	
	@Autowired
	private VisitorFileService vfService;
	
	@Test
	public void testConnection() throws Exception{
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://192.168.0.62:3306/dingdong";
		String user = "wipy";
		String pw = "java97";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, user, pw);
		
		System.out.println(con);
		
		con.close();
	}
	
	@Test
	public void getTest() {
		try {
			log.info("" + service.getList(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void insertVisitorFile() {
		VisitorFile vf = new VisitorFile();
		vf.setVisitFileName("e9a7c7b1-9b40-43c2-9112-6cb5a131d62f_image.jpg");
		vf.setVisitFilePath("image");
		vf.setVisitFileSize(46697L);
		vf.setVisitFileType('i');
		vf.setUserNo(1);
		try {
			vfService.register(vf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void getVisitorFileList() {
		try {
			vfService.getList(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
