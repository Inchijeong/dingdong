package com.dd.mappers;

import java.util.List;

import com.dd.domain.ResVoice;

public interface ResVoiceMapper extends CRUDMapper<ResVoice, Integer> {
	
	public List<ResVoice> getList(int userNo);
	
	public ResVoice getResVoice(ResVoice rv);
}