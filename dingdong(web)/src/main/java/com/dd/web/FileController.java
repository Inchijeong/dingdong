package com.dd.web;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dd.domain.DDKey;
import com.dd.domain.Device;
import com.dd.domain.DingUser;
import com.dd.domain.VisitorFiles;
import com.dd.dto.Criteria;
import com.dd.security.UserService;
import com.dd.service.DeviceService;
import com.dd.service.FileService;
import com.dd.service.TokenService;

import lombok.extern.java.Log;

@Log
@Controller
public class FileController {

	@Autowired
	private FileService fileService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private UserService userService;

	@GetMapping("/file")
	public void fileGet() {
		log.info("file-main-get");
	}

	@GetMapping("/device")
	public void deviceGet() {
	}
	
	@PostMapping("/device")
	public ResponseEntity<String> devicePost(Device device){
		log.info("register device");
		
		ResponseEntity<String> entity = null;
		try {
			deviceService.register(device);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("fail", HttpStatus.OK);
		}
		
		return entity;
	}
	
	
	@PostMapping("/deleteKey")
	public ResponseEntity<String> deleteKey(Integer userNo) {
		log.info("deleteKey:::::");
		ResponseEntity<String> entity = null;
		try {
			deviceService.bellRemove(userNo);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("fail", HttpStatus.OK);
		}
		return entity;
		
	}
	
	@PostMapping("/getDevice")
	public ResponseEntity<List<Device>> device(Integer userNo) {
		log.info("device" + userNo);
		
		ResponseEntity<List<Device>> entity = null;
		try {
			entity = new ResponseEntity<List<Device>>(deviceService.getList(userNo), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(null, HttpStatus.OK);
		}
		return entity;
	}
	@PostMapping("/getDingdong")
	public ResponseEntity<String> dingdong(Integer userNo) {
		String bellToken = deviceService.getBellToken(userNo);
		log.info("bellToken :::::::"+bellToken);
		
		ResponseEntity<String> entity = null;
		try {
			entity = new ResponseEntity<String>(bellToken, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(null, HttpStatus.OK);
		}
		return entity;
	}
	
	@PostMapping("/more")
	public ResponseEntity<List<VisitorFiles>> FileMore(int count, Integer userNo, Model model) {
		log.info("file-more-post");
		ResponseEntity<List<VisitorFiles>> entity = null;
		try {
			int listCount = fileService.getListCount(userNo);
			System.out.println("***********************************************************************************************************");
			entity = new ResponseEntity<List<VisitorFiles>>(fileService.getListMore(userNo,count,listCount), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(null, HttpStatus.OK);
		}
		return entity;
	}

	@GetMapping("/mainDelete")
	@ResponseBody
	public ResponseEntity<String> deleteGet(String token, Criteria cri, Model model) {
		log.info("file-delete-main-get");
		log.info(token);
		ResponseEntity<String> entity = null;
		try {
			deviceService.remove(token);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	@PostMapping("/deviceRegister")
	public String indexPost(Device device, Criteria cri, Model model) {
		log.info("file-main-post");
		try {
			deviceService.register(device);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/file/main";
	}

	//딩동 제품키 생성
	@PostMapping("/bellToken")
	@ResponseBody
	public DDKey ddkey() {
		log.info("key");
		String uuid = UUID.randomUUID().toString();

		DDKey bellToken = new DDKey();
		bellToken.setKey(uuid);
		
		return bellToken;
	}
	
	//딩동 기기 등록
	@PostMapping("/registerKey")
	public ResponseEntity<String> registerKey(String key, Integer userNo) {
		log.info("key-register");
		ResponseEntity<String> entity = null;

		DingUser user = new DingUser();
		user.setBellToken(key);
		user.setUserNo(userNo);
		
		try {
			tokenService.register(user);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
