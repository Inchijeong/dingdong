package com.dd.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dd.domain.DingUser;
import com.dd.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	// 회원가입
	@RequestMapping(value="/signUp", method = RequestMethod.GET)
	public void signUp() {
		
	}
	
		
	@RequestMapping(value="/signUpPost", method = RequestMethod.POST)
	public String signUp(@ModelAttribute("member")DingUser user) {
		service.signUp(user);
		return "redirect:/user/login";
	}
	

	@RequestMapping(value="/userMod", method = RequestMethod.GET)
	public void userMod(String email, Model model) {
		
		List<DingUser> user = service.loginInfo(email);
		model.addAttribute("userName", user.get(0).getUserName().toString());
		model.addAttribute("phone", user.get(0).getPhone().toString());
		model.addAttribute("email", user.get(0).getEmail().toString());
		model.addAttribute("userNo", user.get(0).getUserNo().toString());
	}
	
	@RequestMapping(value="userModPost", method = RequestMethod.POST)
	public String userModify(@ModelAttribute("member")DingUser member) {
		service.userInfoMod(member);
		return "redirect:/";
	}
	
	
	
	
	// 계정찾기
	@RequestMapping(value="/findAccount", method = RequestMethod.GET)
	public void findAccount(DingUser user) {
		
	}
	
	
	@GetMapping("/login")
	public void login() {
		
	}
	
	// 권한 거절시 이동 페이지
	@GetMapping("/accessDenied")
	public void accessDenied() {
		
	}
	
	@GetMapping("/logoutGet")
	public void logout() {
		
	}

	
	
	
	
	
	
}
