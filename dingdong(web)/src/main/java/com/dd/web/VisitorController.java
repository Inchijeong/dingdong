/**
 * @author Inchijeong
 */
package com.dd.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dd.domain.Visitor;
import com.dd.dto.Gender;
import com.dd.dto.Generation;
import com.dd.dto.VisitCount;
import com.dd.service.VisitorService;
import com.dd.util.CognitiveServiceUtil;
import com.dd.util.KmeansUtil;
import com.dd.util.KmeansUtil.Point;

import lombok.extern.java.Log;

@Log
@Controller
public class VisitorController {
	
	@Autowired
	VisitorService service;
	
	@GetMapping("/chart")
	public void chart() {

	}
	
	// 방문자 패턴 테스트
	@GetMapping("/kmeans")
	public void kmeans() {
		int k = 4; // 생성할 개수 지정
		double timeRange = 1; // 시간 범위 지정
		Point newHour = new Point(1.2f); // 새로 들어온 사람의 방문 시간
		
		List<Float> hours = service.getVisitHour(1, 7); // 1 <- 유저번호, 7 <- 일주일 동안
		List<Point> dataset= new ArrayList<>();
		
		try {
			dataset = KmeansUtil.changePoint(hours);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Point> centers = KmeansUtil.initializeRandomCenters(k, 0, 24);		
		List<Point> kmeansCenterList = KmeansUtil.kmeans(centers, dataset, k);


		boolean warning = KmeansUtil.checkWarning(kmeansCenterList, newHour, timeRange);
		if (warning) System.out.println("위험한 방문자");		
	}
	
	// 방문자 등록 테스트
	@GetMapping("/register")
	public void register() {
		Visitor visitor = new Visitor();
		visitor.setUserNo(1);
		String filePath = "C:\\Users\\Bit\\Desktop\\hy.jpg";
		visitor = new CognitiveServiceUtil().cognitiveService(filePath, visitor);
		service.register(visitor);
	}
	
	// 방문자 정보 가져오기 (사용x)
	@GetMapping("/getVisitor")
	@ResponseBody
	public List<Visitor> getVisitor(Integer userNo) {
		return service.getVisitor(userNo);
	}
	
	// 남녀 차트
	@GetMapping("/getGenderCount")
	@ResponseBody
	public List<Gender> getGenderCount(Integer userNo, Integer term) {
		return service.getGenderCount(userNo, term);
	}
	
	// 연령 차트
	@GetMapping("/getGeneration")
	@ResponseBody
	public List<Generation> getGeneration(Integer userNo, Integer term) {
		return service.getGeneration(userNo, term);
	}
	
	// 방문 횟수 차트
	@GetMapping("/getVisitCount")
	@ResponseBody
	public List<VisitCount> getVisitCount(Integer userNo, Integer term) {
		return service.getVisitCount(userNo, term);
	}
	
}
