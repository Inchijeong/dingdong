package com.dd.mappers;

import java.util.List;

import com.dd.domain.VisitorFiles;

public interface VisitorFilesMapper extends CRUDMapper<VisitorFiles, Integer> {
	public List<VisitorFiles> getList(Integer userNo);
	
	public VisitorFiles getFiles(VisitorFiles vf);
}