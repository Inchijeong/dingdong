package com.dd.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dd.domain.DingUser;
import com.dd.domain.UserRole;



public interface UserService {

	public List<DingUser> loginInfo(@Param("email")String username);
	
	public List<UserRole> getAuthority(@Param("email")String username);
	
	public DingUser checkLoginBefore(String value);
	
	public void signUp(DingUser member);
	
	public void userInfoMod(DingUser member);
	
	public DingUser findEmail(DingUser member);
	
	public Integer getUserNo(String deviceId) throws Exception;
	
}
