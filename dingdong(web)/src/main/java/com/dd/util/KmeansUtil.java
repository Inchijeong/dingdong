package com.dd.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

// 단일축 k-means
public class KmeansUtil {
	
	// (병렬화를 위해 )파일에서 읽을 점들 복사 개수
	private static final int REPLICATION_FACTOR = 1;
	
	// 포인트 정적 내부 클래스
	public static class Point {
	
	    private float x;
	
	    // 기준점 좌표 저장(생성자)
	    public Point(float x) {
	        this.x = x;
	    }
	    
	    // 기준점과 주어짐 점의 거리계산
	    private double getDistance(Point other) {
	        return Math.sqrt(Math.pow(this.x - other.x, 2));
	    }
	    
	    // 기준점과 제일 가까운 점목록의 인덱스 반환
	    public int getNearestPointIndex(List<Point> points) {
	        int index = -1;
	        double minDist = Double.MAX_VALUE;
	        for (int i = 0; i < points.size(); i++) {
	            double dist = this.getDistance(points.get(i));
	            if (dist < minDist) {
	                minDist = dist;
	                index = i;
	            }
	        }
	        return index;
	    }
	    
	    // 점들의 평균(무게중심) 찾기
	    public static Point getMean(List<Point> points) {
	        float accumX = 0;
	        if (points.size() == 0) return new Point(accumX);
	        for (Point point : points) {
	            accumX += point.x;
	        }
	        return new Point(accumX / points.size());
	    }
	    
	    
	    // 점의 좌표를 알려줄 toString
	    @Override
	    public String toString() {
	        return "[" + this.x + "]";
	    }
	    
	    // 기준점과 주어진점이 같은지 판별
	    @Override
	    public boolean equals(Object obj) {
	        if (obj == null || !(obj.getClass() != Point.class)) {
	            return false;
	        }
	        Point other = (Point) obj;
	        return this.x == other.x;
	    } 
	}
	
	// 입력 파일에 대한 경로를 받고 점 목록 반환
	public static List<Point> getDataset(String inputFile) throws Exception {
	    List<Point> dataset = new ArrayList<>();
	    BufferedReader br = new BufferedReader(new FileReader(inputFile));
	    String line;
	    while ((line = br.readLine()) != null) {
	        String token = line;
	        float x = Float.valueOf(token);
	        Point point = new Point(x);
	        for (int i = 0; i < REPLICATION_FACTOR; i++)
	            dataset.add(point);
	    }
	    br.close();
	    // System.out.println(dataset);
	    return dataset;
	}
	
	// List<Float>를 List<Point>로 변경
	public static List<Point> changePoint(List<Float> hours) throws Exception {
		List<Point> dataset = new ArrayList<>();
		for (Float hour : hours) {
			Point point = new Point(hour);
			for (int i = 0; i < REPLICATION_FACTOR; i++) {
				dataset.add(point);
			}
		}
		return dataset;
	}
	
	// n개의 랜덤 중심점 목록을 반환
	// upperBound - 기존 집합의 원소보다 크거나 같은값
	// lowerBound - 기존 집합의 원소보다 작거나 같은값
	public static List<Point> initializeRandomCenters(int n, int lowerBound, int upperBound) {
	    List<Point> centers = new ArrayList<>(n);
	    for (int i = 0; i < n; i++) {
	        float x = (float)(Math.random() * (upperBound - lowerBound) + lowerBound);
	        Point point = new Point(x);
	        centers.add(point);
	    }
	    return centers;
	}
	
	// 기존 중심점들을 데이터에 맞춰 새로운 중심점으로 반환
	public static List<Point> getNewCenters(List<Point> dataset, List<Point> centers) {
		// 클러스터 목록 생성(기존 중심점 크기만큼)
	    List<List<Point>> clusters = new ArrayList<>(centers.size());
	    for (int i = 0; i < centers.size(); i++) {
	        clusters.add(new ArrayList<Point>());
	    }
	    for (Point data : dataset) {
	    	// 더미의 각각의 점들과 가까운 중심점을 찾아 클러스터 목록에 추가
	        int index = data.getNearestPointIndex(centers);
	        clusters.get(index).add(data);
	    }
	    // 클러스터 목록에서 중심점을 찾음
	    List<Point> newCenters = new ArrayList<>(centers.size());
	    for (List<Point> cluster : clusters) {
	        newCenters.add(Point.getMean(cluster));
	    }
	    return newCenters;
	}
	
	// 기존 중심점들과 새로운 중심점들간 거리의 합 반환
	public static double getDistance(List<Point> oldCenters, List<Point> newCenters) {
		double accumDist = 0;
		for (int i = 0; i < oldCenters.size(); i++) {
			double dist = oldCenters.get(i).getDistance(newCenters.get(i));
			accumDist += dist;
		}
		return accumDist;
	}
	
	// 새로운 중심점이 고정될때까지 반복 수행
	public static List<Point> kmeans(List<Point> centers, List<Point> dataset, int k) {
	    boolean converged;
	    do {
	    	// 새로운 중심점 목록
	        List<Point> newCenters = getNewCenters(dataset, centers);
	        // 기존 중심점들과 새로운 중심점의 거리들간 거리의 합
	        double dist = getDistance(centers, newCenters);
	        centers = newCenters;
	        converged = dist == 0;
	    } while (!converged);
	    return centers;
	}
	
	// 이상한 방문자 체크
	public static boolean checkWarning(List<Point> kmeansCenterList, Point newHour, Double timeRange) {
		// 클러스터 범위 외 이벤트 발생시 true
		boolean warning = false;
		
		//System.out.println("새로운 방문자가 들어온 시간" + newHour);
		loof :
		for (Point center : kmeansCenterList) {
			// 점들 표시
			//System.out.println("중심점: " + center);
			
			// 범위를 지정
			warning = (newHour.getDistance(center) > timeRange / 2);
			if (warning == false) {
				//System.out.println("정상 범위에 포함된 방문자");
				break loof;
			}

		}
		return warning;
	}
	
		
	
}
