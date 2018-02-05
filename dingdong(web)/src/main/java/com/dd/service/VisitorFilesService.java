package com.dd.service;

import java.util.List;

import com.dd.domain.VisitorFiles;

public interface VisitorFilesService {
	public Integer register(VisitorFiles vo) throws Exception;
	public void remove(Integer fileNo) throws Exception;
	public List<VisitorFiles> getList(Integer userNo) throws Exception;
	public VisitorFiles getFiles(VisitorFiles vf) throws Exception;
}