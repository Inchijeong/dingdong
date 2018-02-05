package com.dd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dd.domain.DingUser;
import com.dd.mappers.TokenMapper;

import lombok.extern.java.Log;

@Log
@Service
public class TokenServiceImpl implements TokenService {


	@Autowired
	private TokenMapper mapper;


	@Override
	public void register(DingUser vo) throws Exception {
		log.info("token-service - register");
		log.info(vo.toString());
		
		mapper.create(vo);
	}

}
