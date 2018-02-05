package com.dd.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/device/*")
public class DeviceController {

	@GetMapping("/main")
	public void uploadGet() {
		log.info("device-main");
	}
}
