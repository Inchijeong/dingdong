package com.dd.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dd.domain.Visitor;
import com.dd.dto.Gender;
import com.dd.dto.Generation;
import com.dd.dto.VisitCount;

public interface VisitorMapper extends CRUDMapper<Visitor, Integer>{
	public List<Visitor> readVisitor(Integer userNo);
	public List<Gender> readGenderCount(@Param(value="userNo")Integer userNo, @Param(value="term") Integer term);
	public List<Generation> readGeneration(@Param(value="userNo")Integer userNo, @Param(value="term") Integer term);
	public List<VisitCount> readVisitCount(@Param(value="userNo")Integer userNo, @Param(value="term") Integer term);
	public List<Float> readVisitHour(@Param(value="userNo")Integer userNo, @Param(value="term") Integer term);
}
