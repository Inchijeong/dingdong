package com.dd.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dd.domain.VisitorFile;
import com.dd.domain.VisitorFiles;
import com.dd.mappers.FileMapper;

import lombok.extern.java.Log;

@Log
@Service
public class FileServiceImpl implements FileService {


	@Autowired
	private FileMapper mapper;


	@Override
	public VisitorFiles get(Integer visitFileNo) {
		log.info("get");
		return mapper.read(visitFileNo);
	}


	@Override
	public void remove(Integer visitFileNo) {
		log.info("remove");
		mapper.delete(visitFileNo);
	}

	@Override
	public int getListCount(Integer userNo) {
		return mapper.getlistCount(userNo);
	}

	@Override
	public List<VisitorFiles> getList(Integer userNo, Integer listCount) {
		log.info("getList" );
		return mapper.getlist(userNo, listCount);
	}

	@Override
	public List<VisitorFiles> getListMore(Integer userNo, Integer count, Integer listCount) {
		log.info("getListMore");
		log.info("userNo : "+ userNo);
		log.info("count : " + count);
		log.info("listCount : " + listCount);
		
		int start = (count * 9);
		start = start > listCount ? listCount : start;
		int end = 9;
		end = end > listCount ? listCount : end;

		log.info("start : "+start+"    end: "+end);
		return mapper.getlistMore(userNo, start, end);
	}
}
