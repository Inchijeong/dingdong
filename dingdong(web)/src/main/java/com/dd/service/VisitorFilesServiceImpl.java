package com.dd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dd.domain.VisitorFiles;
import com.dd.mappers.VisitorFilesMapper;

import lombok.extern.java.Log;

@Log
@Service
public class VisitorFilesServiceImpl implements VisitorFilesService{

	@Autowired
	VisitorFilesMapper mapper;
	
	@Override
	public Integer register(VisitorFiles vo) throws Exception {
		mapper.create(vo);
		return vo.getVisitFileNo();
	}

	@Override
	public void remove(Integer fileNo) throws Exception {
		mapper.delete(fileNo);
	}

	@Override
	public List<VisitorFiles> getList(Integer userNo) throws Exception {
		return mapper.getList(userNo);
	}
	
	@Override
	public VisitorFiles getFiles(VisitorFiles vf) throws Exception {
		return mapper.getFiles(vf);
	}
}
