package com.dd.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dd.domain.ResVoice;

public interface ResVoiceService {
	public void fileUpload(MultipartFile[] files, Integer userNo) throws Exception;
	public List<ResVoice> getList(int userNo) throws Exception;
	public void remove(Integer resFileNo) throws Exception;
	public ResVoice getResVoice(ResVoice rv) throws Exception;
}