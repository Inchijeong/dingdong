package com.dd.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dd.domain.DingUser;
import com.dd.domain.UserRole;
import com.dd.mappers.UserMapper;


@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserMapper mapper;
	

	@Override
	public DingUser checkLoginBefore(String value) {
		return mapper.checkUserWithSessionKey(value);
	}


	@Override
	@Transactional
	public void signUp(DingUser member) {
		mapper.createMember(member);
		mapper.defaultRole(member);
		
	}


	@Override
	public void userInfoMod(DingUser member) {
		mapper.userMod(member);
	}


	@Override
	public DingUser findEmail(DingUser member) {
		return mapper.findEmail(member);
	}


	@Override
	public List<DingUser> loginInfo(String username) {
		return mapper.loginInfo(username);
	}


	@Override
	public List<UserRole> getAuthority(String username) {
		return mapper.getAuthority(username);
	}
	
	@Override
	public Integer getUserNo(String deviceId) throws Exception {
		return mapper.getUserByDeviceId(deviceId);
	}
	
}
