package com.dd.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dd.domain.DingUser;
import com.dd.domain.UserRole;
import com.dd.mappers.UserMapper;

import lombok.extern.java.Log;


@Log
@Service // 스프링에서 빈으로 처리
public class UserService implements UserDetailsService {

	@Autowired
	UserMapper mapper;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		
		List<UserRole> authority = mapper.getAuthority(username);

		DingUser mem = new DingUser();
		mem = mapper.loginInfo2(username);
		mem.setRoleName(authority.get(0).getRoleName());
		
		SecurityUser user = new SecurityUser(mem, authority.get(0).getRoleName());
		log.info("user: " + user.getUserNo());
		return user;
		
	}



}
