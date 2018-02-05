package com.dd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.dd.domain.VisitorFiles;
import com.dd.service.VisitorFilesService;

import lombok.extern.java.Log;

@Log
@Controller
public class VisitorFilesController {
	
	@Autowired
	private VisitorFilesService vfService; 
	
	@GetMapping("/visitorFiles")
	public void visitorFiles() {
		
	}
	
	@PostMapping("/getFiles")
	public ResponseEntity<VisitorFiles> getFiles(VisitorFiles vf) {
		ResponseEntity<VisitorFiles> entity = null;
		
		log.info("getFiles");
		log.info("userNo : " + vf);
		try {
			entity = new ResponseEntity<VisitorFiles>(vfService.getFiles(vf), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
