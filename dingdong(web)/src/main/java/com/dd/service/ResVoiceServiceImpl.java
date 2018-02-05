package com.dd.service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.dd.domain.ResVoice;
import com.dd.mappers.ResVoiceMapper;

import lombok.extern.java.Log;

@Log
@Service
public class ResVoiceServiceImpl implements ResVoiceService {
	private static final String ATT_ROOT = "D:\\FileUpload\\";
	
	@Autowired
	private ResVoiceMapper mapper;
	
	@Override
	public void fileUpload(MultipartFile[] files, Integer userNo) throws Exception {

		for(MultipartFile file:files) {
			log.info("=========================================");
			log.info(file.getOriginalFilename());
			log.info(file.getContentType());
			log.info(""+file.getSize());
			
			String fileName = file.getOriginalFilename();
			int pos = fileName.indexOf(".");
			
			ResVoice rv = new ResVoice();
			rv.setResFileTitle(fileName.substring(0, pos));
			rv.setResFilePath("voice");
			rv.setResFileName(UUID.randomUUID().toString() + fileName.substring(pos));
			rv.setResFileSize(file.getSize());
			rv.setUserNo(userNo);
			
			OutputStream out = new FileOutputStream(ATT_ROOT + rv.getResFilePath() +"\\"+ rv.getResFileName());
			FileCopyUtils.copy(file.getInputStream(), out);
			
			//임시- 라파 음성 출력용
			out = new FileOutputStream("C:\\java97\\server-work\\wtpwebapps\\dingdong\\resources\\"+rv.getResFilePath() +"\\"+ rv.getResFileName());
			FileCopyUtils.copy(file.getInputStream(), out);
			
			
			mapper.create(rv);
		}
	}

	@Override
	public List<ResVoice> getList(int userNo) throws Exception {
		return mapper.getList(userNo);
	}

	@Override
	public void remove(Integer resFileNo) throws Exception {
		mapper.delete(resFileNo);
	}

	@Override
	public ResVoice getResVoice(ResVoice rv) throws Exception {
		return mapper.getResVoice(rv);
	}
}