<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- Header -->
<header>
<sec:authentication var="user" property="principal" />
  <div class="sticky-nav">
  	<a id="mobile-nav" class="menu-nav" href="#menu-nav"></a>
      
    <div id="logo">
    	<a id="goUp" href="#home-slider" title="Brushed | Responsive One Page Template">Brushed Template</a>
    </div>
    
    <nav id="menu">
    	<ul id="menu-nav">
	    	<div>
	       	 <!-- <li><a href="#">Visitor</a></li>
	         <li><a href="#">Response Voice</a></li>
	         <li><a href="/chart/chart">Statistics</a></li>
					 <li><a href="#">Device</a></li> -->
					 <li><a href="/file/file">방문자</a></li>
					 <li><a href="/file/device">기기 관리</a></li>
	         <li><a href="https://192.168.0.62:8443/resVoice">응대 메시지</a></li>
	         <li><a href="/chart">통계</a></li>
	         <sec:authorize access="hasRole('ROLE_USER')">
							<li><a href="/logout">로그아웃</a>
					 </sec:authorize>
	    	</div>
    	</ul>
    </nav>
  </div>
</header>
<!-- End Header -->