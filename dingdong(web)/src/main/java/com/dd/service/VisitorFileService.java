package com.dd.service;

import java.util.List;

import com.dd.domain.VisitorFile;

public interface VisitorFileService {
	public void register(VisitorFile vo) throws Exception;
	public void remove(Integer fileNo) throws Exception;
	public List<VisitorFile> getList(Integer userNo) throws Exception;
}