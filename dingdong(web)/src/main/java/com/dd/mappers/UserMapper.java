package com.dd.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dd.domain.DingUser;
import com.dd.domain.UserRole;


public interface UserMapper {
	
	public DingUser checkUserWithSessionKey(String value);
	
	public void createMember(DingUser user);
	
	public void defaultRole(DingUser user);
	
	public void userMod(DingUser user);
	
	public DingUser findEmail(DingUser user);
	
	public List<DingUser> loginInfo(@Param("email")String username);
	
	public DingUser loginInfo2(@Param("email")String username);
	
	public List<UserRole> getAuthority(@Param("email")String username);
	
	public Integer getUserByDeviceId(String deviceId);
}
