package com.dd.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.dd.domain.DingUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecurityUser extends User {
	
	private static final String ROLE_PREFIX = "ROLE_";
	private Integer userNo;
	
	public SecurityUser(DingUser member, String authority) {
		super(member.getEmail(), member.getPassword(), makeGrantedAuthority(authority));
	
		this.userNo = member.getUserNo();
	}
	
	private static List<GrantedAuthority> makeGrantedAuthority(String authority){

		
		List<GrantedAuthority> result = new ArrayList<>();
		result.add(new SimpleGrantedAuthority(ROLE_PREFIX + authority));
		
		return result;
				
	}
			
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append("; ");
		sb.append("userNo: ").append(this.userNo).append("; ");



		return sb.toString();
	}

	
	

}
