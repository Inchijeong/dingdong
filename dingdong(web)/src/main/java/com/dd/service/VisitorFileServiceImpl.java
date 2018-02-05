package com.dd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dd.domain.VisitorFile;
import com.dd.mappers.VisitorFileMapper;

import lombok.extern.java.Log;

@Log
@Service
public class VisitorFileServiceImpl implements VisitorFileService{

	@Autowired
	VisitorFileMapper mapper;
	
	
	@Override
	public void register(VisitorFile vo) throws Exception {
		mapper.create(vo);
	}

	@Override
	public void remove(Integer fileNo) throws Exception {
		mapper.delete(fileNo);
	}

	@Override
	public List<VisitorFile> getList(Integer userNo) throws Exception {
		return mapper.getList(userNo);
	}
}
