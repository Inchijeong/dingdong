package com.dd.mappers;

import java.util.List;

import com.dd.domain.VisitorFile;

public interface VisitorFileMapper extends CRUDMapper<VisitorFile, Integer> {
	public List<VisitorFile> getList(Integer userNo);
}