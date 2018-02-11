/**
 * @author Inchijeong
 */
package com.dd.service;

import java.util.List;

import com.dd.domain.Visitor;
import com.dd.dto.Gender;
import com.dd.dto.Generation;
import com.dd.dto.VisitCount;

public interface VisitorService {
	public void register(Visitor visitor);
	public List<Visitor> getVisitor(Integer userNo);
	public List<Gender> getGenderCount(Integer userNo, Integer term);
	public List<Generation> getGeneration(Integer userNo, Integer term);
	public List<VisitCount> getVisitCount(Integer userNo, Integer term);
	public List<Float> getVisitHour(Integer userNo, Integer term);
}
