package com.dd.service;

import java.util.List;
import com.dd.domain.VisitorFiles;

public interface FileService {
	public VisitorFiles get(Integer visitFileNo);
	public void remove(Integer visitFileNo);
	public List<VisitorFiles> getList(Integer userNo, Integer listCount);
	public List<VisitorFiles> getListMore(Integer userNo, Integer count, Integer listCount);
	public int getListCount(Integer userNo);
}