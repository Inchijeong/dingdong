package com.dd.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dd.domain.ResVoice;
import com.dd.service.ResVoiceService;
import com.dd.util.Firebase;

import lombok.extern.java.Log;

@Log
@CrossOrigin(origins = "*")
@RestController
public class ResVoiceController {
	private static final String ROOT_PATH = "/resfile";

	@Autowired
	private ResVoiceService service;

	@GetMapping(ROOT_PATH)
	public ResponseEntity<List<ResVoice>> list(Integer userNo) throws Exception {
		ResponseEntity<List<ResVoice>> entity = null;
		
		try {
			entity = new ResponseEntity<List<ResVoice>>(service.getList(userNo), HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@PostMapping(ROOT_PATH)
	public ResponseEntity<String> register(@RequestParam("file") MultipartFile[] files, @RequestParam("userNo") Integer userNo) throws Exception {
		log.info("register resfile");
		ResponseEntity<String> entity = null;
		log.info("length : " + files.length);
		
		try {
			service.fileUpload(files, userNo);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch(Exception e) {
			log.warning(e.getMessage());
			entity = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@DeleteMapping(ROOT_PATH + "/{resFileNo}")
	public ResponseEntity<String> remove(@PathVariable("resFileNo") Integer resFileNo){
		log.info("remove resfile");
		log.info("resFileNo : "+ resFileNo);
		ResponseEntity<String> entity = null;
		
		try {
			service.remove(resFileNo);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	@PostMapping("/sendVoice")
	public ResponseEntity<String> sendVoice(ResVoice rv) {
		log.info("resFileNo : " +rv.getResFileNo());
		log.info("userNo : " +rv.getUserNo());
		ResponseEntity<String> entity = null;
		try {
			rv = service.getResVoice(rv);
			Firebase.putResVoiceInfo(rv.getBellToken(), rv.getResFilePath()+"/"+rv.getResFileName());
			
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
