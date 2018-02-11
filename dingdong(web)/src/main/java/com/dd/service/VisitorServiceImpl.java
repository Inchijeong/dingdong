/**
 * @author Inchijeong
 */
package com.dd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dd.domain.Visitor;
import com.dd.dto.Gender;
import com.dd.dto.Generation;
import com.dd.dto.VisitCount;
import com.dd.mappers.VisitorMapper;

@Service
public class VisitorServiceImpl implements VisitorService {
	
	@Autowired
	VisitorMapper mapper;
	
	@Override
	public void register (Visitor visitor) {
		mapper.create(visitor);
	}

	@Override
	public List<Visitor> getVisitor(Integer userNo) {
		return mapper.readVisitor(userNo);
	}

	@Override
	public List<Gender> getGenderCount(Integer userNo, Integer term) {
		return mapper.readGenderCount(userNo, term);
	}

	@Override
	public List<Generation> getGeneration(Integer userNo, Integer term) {
		return mapper.readGeneration(userNo, term);
	}

	@Override
	public List<VisitCount> getVisitCount(Integer userNo, Integer term) {
		return mapper.readVisitCount(userNo, term);
	};
	
	@Override
	public List<Float> getVisitHour(Integer userNo, Integer term) {
		return mapper.readVisitHour(userNo, term);
	};
}
