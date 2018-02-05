<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  

<!DOCTYPE html>
<html>

<!-- Meta Tags -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>DINGDONG</title>
<meta name="description" content="Insert Your Site Description" />
<!-- Mobile Specifics -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="HandheldFriendly" content="true" />
<meta name="MobileOptimized" content="320" />
<!-- Mobile Internet Explorer ClearType Technology -->

<!-- Bootstrap -->
<link href="/resources/_include/css/bootstrap.min.css" rel="stylesheet">
<!-- Main Style -->
<link href="/resources/_include/css/main.css" rel="stylesheet">
<!-- Supersized -->
<!-- <link href="/resources/_include/css/supersized.css" rel="stylesheet">
<link href="/resources/_include/css/supersized.shutter.css" rel="stylesheet"> -->
<!-- FancyBox -->
<link href="/resources/_include/css/fancybox/jquery.fancybox.css" rel="stylesheet">
<!-- Font Icons -->
<link href="/resources/_include/css/fonts.css" rel="stylesheet">
<!-- Shortcodes -->
<link href="/resources/_include/css/shortcodes.css" rel="stylesheet">
<!-- Responsive -->
<link href="/resources/_include/css/bootstrap-responsive.min.css"
	rel="stylesheet">
<link href="/resources/_include/css/responsive.css" rel="stylesheet">
<!-- Google Font -->
<link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,200,200italic,300,300italic,400italic,600,600italic,700,700italic,900' rel='stylesheet' type='text/css'>
<!-- Fav Icon -->
<link rel="shortcut icon" href="#">
<link rel="apple-touch-icon" href="#">
<link rel="apple-touch-icon" sizes="114x114" href="#">
<link rel="apple-touch-icon" sizes="72x72" href="#">
<link rel="apple-touch-icon" sizes="144x144" href="#">

<!-- Modernizr -->
<script src="/resources/_include/js/modernizr.js"></script>
<!-- Analytics -->
<script type="text/javascript">
	var _gaq = _gaq || [];
	_gaq.push([ '_setAccount', 'Insert Your Code' ]);
	_gaq.push([ '_trackPageview' ]);

	(function() {
		var ga = document.createElement('script');
		ga.type = 'text/javascript';
		ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl'
				: 'http://www')
				+ '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ga, s);
	})();
</script>
<!-- End Analytics -->
	
<body>	
	<%@include file="/WEB-INF/views/common/header.jsp" %>	
		
	<sec:authentication var="user" property="principal" />

	<!-- Our Work Section -->
	<div id="work" class="page">
		<div class="container">
			<!-- Title Page -->
			<div class="row">
				<div class="span12">
					<div class="title-page">
						<h2 class="title">방문자 목록</h2>
						<h3 class="title-description"><span class="emphasis">방문자</span>들의 <span class="emphasis">기록</span>을 확인하세요.</h3>
					</div>
				</div>
			</div>
			<!-- End Title Page -->

			<!-- Portfolio Projects -->
			<div class="row">
				<div class="span3">
					<!-- Filter -->
					<nav id="options" class="work-nav">
					<ul id="filters" class="option-set" data-option-key="filter">
						<li><a href="#filter" data-option-value="*" class="selected">모든 방문 기록</a></li>
						<li><a href="#filter" data-option-value=".photography" id="photo">사진</a></li>
						<li><a href="#filter" data-option-value=".video" id="video">비디오</a></li>
					</ul>
					</nav><br>
				<a class="button button-small" id="more" >View more</a>
				<br><br>
					<!-- End Filter -->
				</div>

				<div class="span9" id="divFile">
					<div class="row" id="rowFile">
						<%-- <section id="projects">
							<ul id="thumbs">
	 						</ul>
						</section> --%>
					</div>
				</div>
			</div>
			<!-- End Portfolio Projects -->
		</div>
	</div>
	<!-- End Our Work Section -->


	<!-- Back To Top -->
	<a id="back-to-top" href="#"> <i class="font-icon-arrow-simple-up"></i>
	</a>
	<!-- End Back to Top -->
<!-- Footer -->
	<footer>
		<p class="credits">&copy;2017. WIPY all right reserved. </p>
	</footer>
	<!-- End Footer -->
	
	<!-- Js -->
	<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> <!-- jQuery Core -->
	<script src="/resources/_include/js/bootstrap.min.js"></script> <!-- Bootstrap -->
	<script src="/resources/_include/js/waypoints.js"></script> <!-- WayPoints -->
	<script src="/resources/_include/js/waypoints-sticky.js"></script> <!-- Waypoints for Header -->
	<script src="/resources/_include/js/jquery.isotope.js"></script> <!-- Isotope Filter -->
	<script src="/resources/_include/js/jquery.fancybox.pack.js"></script> <!-- Fancybox -->
	<script src="/resources/_include/js/jquery.fancybox-media.js"></script> <!-- Fancybox for Media -->
	<script src="/resources/_include/js/plugins.js"></script> <!-- Contains: jPreloader, jQuery Easing, jQuery ScrollTo, jQuery One Page Navi -->
	<script src="/resources/_include/js/main.js"></script> <!-- Default JS -->
	<script src="/resources/_include/js/common.js"></script> <!-- Default JS -->
	<!-- End Js -->

	<script>
	var token = ""
	var fileCount = 0;
	var moreCount = 0;
	
	function getVisitorFiles() {
		console.log("getVisitorFiles");
		$.ajax({
			url : "/file/more",
			type : "POST",
			dataType : "json",	
			data : {
				count : moreCount,
				userNo : ${user.userNo}
			}
		}).done(function(data){	
			console.log(data);
			console.log("---------------------------------------------------------------moremore");
			console.log("len : "+ $("#projects").length);
			var projectCnt = $("#projects").length;
			var str = projectCnt > 0 ? '' : '<section id="projects" class="isotope" style="position: relative;"><ul id="thumbs">';
			if(data.length > 0){
				for(var i=0;i<data.length;i++) {
					if(data[i].visitFileType == 'i') {
						str += "<li class='item-thumbs span3 photography isotope-item'>"+
									 "	<a class='hover-wrap fancybox' data-fancybox-group='gallery' title='"+data[i].visitFileName+"' href='"+data[i].visitFileName+"'>"+
									 "		<span class='overlay-img'></span><span class='overlay-img-thumb font-icon-plus'></span>"+
									 "	</a>"+
									 "	<img src='/displayFile/image?name="+data[i].visitFileName+"' alt='time : "+getDateTime(data[i].visitFileDate)+"' width='270px' height='179.5px'>"+
									 "</li>";
					}else if(data[i].visitFileType == 'v') {
						str += "<li class='item-thumbs span3 video isotope-item'><video controls='true' preload='metadata' width='270px' height='179.5px' src='/displayFile/video?name="+data[i].visitFileName+"'></video></li>";
					}
					console.log(i);
				}
				str += projectCnt > 0 ? '' : '</ul></section>';
				projectCnt > 0 ? $("#thumbs").append(str) :	$("#rowFile").append(str);
//				$("#thumbs").append(str);
			}else{
				str += "<li><span><b>방문 내역이 없습니다.</b></span></li>";
				str += projectCnt > 0 ? '' : '</ul></section>';
				projectCnt > 0 ? $("#thumbs").append(str) :	$("#rowFile").append(str);
			}
		});	
	}
	
	$("#more").on("click", function(){
		moreCount++;
		getVisitorFiles();
	});
	
	getVisitorFiles();
	</script>
</body>
</html>