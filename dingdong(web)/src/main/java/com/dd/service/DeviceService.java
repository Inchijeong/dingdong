package com.dd.service;

import java.util.List;
import com.dd.dto.Criteria;
import com.dd.domain.Device;

public interface DeviceService {
	public void register(Device vo) throws Exception;
	public Device get(Long tno);
	public void remove(String token) throws Exception;
	public List<Device> getList(Integer user_no) throws Exception;
	public int getListCount(Criteria cri);
	public List<String> getNotiTokenList(Integer user_no) throws Exception;
	public String getBellToken(Integer user_no);
	public void bellRemove(Integer user_no) throws Exception;
}