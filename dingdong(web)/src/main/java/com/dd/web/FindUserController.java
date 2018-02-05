package com.dd.web;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dd.domain.DingUser;
import com.dd.service.UserService;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/find")
public class FindUserController {
	
	@Inject
	private UserService service;
	
	@RequestMapping(value="email", method = RequestMethod.POST)
	public ResponseEntity<DingUser> findEmail(DingUser user){
		ResponseEntity<DingUser> entity = null;
		log.info("이메일을 찾아보자" + user);
		log.info("찾은 이메일"+service.findEmail(user));
		entity = new ResponseEntity<>(service.findEmail(user),HttpStatus.OK);
		return entity;
	}

}
