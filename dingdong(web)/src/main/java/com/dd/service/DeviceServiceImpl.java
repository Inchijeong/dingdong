package com.dd.service;

import java.util.List;
import com.dd.domain.Device;
import com.dd.dto.Criteria;
import com.dd.mappers.DeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.java.Log;

@Log
@Service
public class DeviceServiceImpl implements DeviceService {


	@Autowired
	private DeviceMapper mapper;


	@Override
	public void register(Device vo) throws Exception {
		log.info("device-service - register");
		log.info(vo.toString());
		
		mapper.create(vo);
	}

	@Override
	public Device get(Long cid) {
		log.info("get");
		return mapper.read(cid);
	}

	@Override
	public void remove(String token) throws Exception {
		log.info("remove");
		log.info(token);
		String tokenArr[] = token.split("/");
		String noti_token="";
		log.info(String.valueOf(tokenArr.length));
		
		for(int i=0;i<tokenArr.length;i++) {
			noti_token = tokenArr[i];
			log.info(noti_token+i);
			mapper.delete(noti_token);
		}
		
		log.info("finish delete");
	}

	@Override
	public int getListCount(Criteria cri) {
		return 0;
	}

	@Override
	public List<Device> getList(Integer user_no) throws Exception {
		log.info("getList");
		return mapper.getlist(user_no);
	}
	
	@Override
	public List<String> getNotiTokenList(Integer user_no) throws Exception {
		log.info("deviceService > getNotiTokenList");
		log.info("user_no : "+user_no);
		return mapper.getNotiTokenList(user_no);
	}

	@Override
	public String getBellToken(Integer user_no) {
		return mapper.getBellToken(user_no);
	}

	@Override
	public void bellRemove(Integer user_no) throws Exception {
		mapper.bellRemove(user_no);
	}
}
