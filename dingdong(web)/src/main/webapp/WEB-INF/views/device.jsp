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

<!-- 합쳐지고 최소화된 최신 CSS -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->
<!-- 부가적인 테마 -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css"> -->
<!-- Bootstrap -->
<link href="/resources/_include/css/bootstrap.min.css" rel="stylesheet">
<!-- Main Style -->
<link href="/resources/_include/css/main.css" rel="stylesheet">
<!-- Supersized -->
<!-- <link href="/resources/_include/css/supersized.css" rel="stylesheet">
<link href="/resources/_include/css/supersized.shutter.css"	rel="stylesheet"> -->
<!-- FancyBox -->
<link href="/resources/_include/css/fancybox/jquery.fancybox.css" rel="stylesheet">
<!-- Font Icons -->
<link href="/resources/_include/css/fonts.css" rel="stylesheet">
<!-- Shortcodes -->
<link href="/resources/_include/css/shortcodes.css" rel="stylesheet">
<!-- Responsive -->
<link href="/resources/_include/css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="/resources/_include/css/responsive.css" rel="stylesheet">
<!-- Supersized -->
<link href="/resources/_include/css/supersized.css" rel="stylesheet">
<link href="/resources/_include/css/supersized.shutter.css" rel="stylesheet">
<!-- Google Font -->
<link	href='https://fonts.googleapis.com/css?family=Titillium+Web:400,200,200italic,300,300italic,400italic,600,600italic,700,700italic,900' rel='stylesheet' type='text/css'>

<!-- sweetalert2 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

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
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	crossorigin="anonymous"></script>

<style>
	#contact-form input, #contact-form textarea {
		background: #fff;
		border: 1px solid #ccc;
		color: black;
		width: 35%;
	} 
	#getToken{
		width: 0px;
		height: 0px;
	}
	.button{
		background-color: #fff;
	}
	.divisionLine{
		border: 0.5px #DE5E60 solid;
	}
	#radioButton, .radioTitle{
		display: inline-block;
	}
	#radioButton{
		width: 30px !important;
		margin-right:20px;
	}
	.deviceToken{
		/* border: 0px !important; */
	}
	.contact-message{
		margin-top: 20px;
		margin-bottom: 20px;
		margin-right:20px;
		display:inline-block;
	}
	.radio-container{
		margin-top: 20px;
		margin-bottom: 20px;
		display:inline-block;
	}
	.device-details input{
		background: #fff;
		border: 1px solid #ccc;
		color: black;
		padding : 13px;
		margin-right:20px;
	}
	.mg-bottom10{
		margin-bottom:10px !important;
	}
</style>

</head>


<body>	
	<%@include file="/WEB-INF/views/common/header.jsp" %>		
	
	<sec:authentication var="user" property="principal" />
	
	<!-- Contact Section -->
	<div id="contact" class="page">
		<div class="container">
			<!-- Title Page -->
			<div class="row">
				<div class="span12">
					<div class="title-page">
						<h2 class="title">기기 관리</h2>
						<h3 class="title-description"><span class="emphasis">기기</span>들을 <span class="emphasis">등록</span>하고 <span class="emphasis">관리</span>하세요.</h3>
					</div>
				</div>
			</div>
			<!-- End Title Page -->

			<!-- Contact Form -->
			<div class="row">
				<div class="span12">
					<div class="tabbable">
						<ul class="nav nav-tabs" id="myTab">
							<li class="active"><a href="#tab1" data-toggle="tab">알림기기</a></li>
							<li><a href="#tab2" data-toggle="tab">딩동</a></li>
						</ul>
	
						<div class="tab-content">
						
							<div class="tab-pane fade in active" id="tab1">
								<div class="span9" id="selectionDevice">
									<div class="contact-details">
										<div class="table-responsive">
											<table class="table" id="deviceTable">
												<!-- <thead>
												<tr>
													<th>기기명</th>
													<th>기기 종류</th>
													<th>알림 수신 여부</th>
												</tr>
												</thead>
												<tbody></tbody> -->
<%-- 												<c:forEach items="${deviceList}" var="device">
													<tr>
														<td hidden><input id='checkBoxToken' type='checkbox' value='${device.noti_token}'></td>
														<td>${device.noti_name}</td>
														<td>${device.cate_name}</td>
														<c:if test="${device.noti_receive == 0}">
															<td>X</td>
														</c:if>
														<c:if test="${device.noti_receive == 1}">
															<td>O</td>
														</c:if>
													</tr>
												</c:forEach>
 --%>											</table>
										</div>
									</div>
									<a class="button button-small" id="registerDevice">알림기기 추가</a>
									<a class="button button-small" id="deleteDevice">알림기기 삭제</a>
									<span hidden id="deleteSpan"><a class='button button-small' id="delete">삭제</a></span>
									<span hidden id="cancelSpan"><a class='button button-small' id="cancel">취소</a></span>
								</div>
								
								
								<div id="registerContainer" class="tab-pane fade in active" hidden >
									<div class="span9">
										<div class="contact-details">
										<hr class="divisionLine">
										<form id="contact-form" class="contact-form"  method="post">
											<p class="contact-name">
												<span>기기명</span><br />
												<input id="contact_name" type="text" placeholder="기기 명을 적으세요"
													value="" name="noti_name" class="mg-bottom10" />
											</p>
											<p class="contact-email">
												<span>기기종류</span><br />
												<select id="cate_code" name="cate_code">
													<option value="0">Phone</option>	
													<option value="1">노트북</option>
													<option value="2">데스크탑</option>
													<option value="3">태블릿</option>
												</select>
											</p>
											<br>
											<p class="contact-token">
												<input type="text" id="noti_token" class="deviceToken" readonly placeholder="기기정보를 가져오세요">
	   										<button type="button" class="tokenBtn button">기기정보 가져오기</button>
	    									<iframe id="getToken" ></iframe>
											</p>

											<p class="contact-message">
												알림 수신에 동의하시겠습니까?</p>	
												<div class="radio-container">
													<label for="radioButton" class="radioTitle">동의함</label>
													<input type="radio" id="radioButton" value='1' name="noti_receive">
													
													<label for="radioButton" class="radioTitle">동의 안함</label>
													<input type="radio" id="radioButton" value='0' name="noti_receive">
												</div>
											
	<!-- 										<p class="contact-message"> -->
	<!-- 											<textarea id="contact_message" placeholder="Your Message" -->
	<!-- 												name="message" rows="15" cols="40"></textarea> -->
	<!-- 										</p> -->
											<p class="contact-submit">
												<button type="button" class="submitBtn button" id="register">등록</button>
											</p>
											<div id="response"></div>
										</form>
										</div>
									</div>
								</div>
								
							</div>
							<div class="tab-pane fade in" id="tab2">
								<div class="span10">
									<div class="contact-details device-details">
										<ul id="keyUL">
<%-- 	 									<c:if test="${로그인 회원에 bellToken이 존재하지 않으면}"> --%>
<!-- 											<li>IP주소입력<input id="ip" type="text" /><a class="button button-small" id="submitIP">확인</a></li> -->
<!-- 											<li>받아온키 <input id="dingdongKey" type="text" /></li> -->
<!-- 											<a class="button button-small" id="keyRegister">등록</a> -->
<%-- 	 									</c:if> --%>
<%-- 	 									<c:if test="${로그인 회원에 bellToken이 존재하면}">  --%>
<!-- 	 									<li>딩동이 등록되었습니다.</li>  -->
<!-- 	 									<a class="button button-small" id="keyDelete">Dingdong 삭제!!</a>  -->
<%-- 	 									</c:if>  --%>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
					
				</div>
			</div>
			<!-- End Contact Form -->
		</div>
	</div>
	<!-- End Contact Section -->

	<!-- Back To Top -->
	<a id="back-to-top" href="#"> 
		<i class="font-icon-arrow-simple-up"></i>
	</a>
	<!-- End Back to Top -->
	
	<!-- Footer -->
	<footer>
		<p class="credits">&copy;2017. WIPY all right reserved. </p>
	</footer>
	<!-- End Footer -->
	
	<!-- Js -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> <!-- jQuery Core -->
<!-- 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>  --><!-- 합쳐지고 최소화된 최신 자바스크립트 -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.min.js"></script>
	<script src="/resources/_include/js/bootstrap.min.js"></script> <!-- Bootstrap -->
	<script src="/resources/_include/js/waypoints.js"></script> <!-- WayPoints -->
	<script src="/resources/_include/js/waypoints-sticky.js"></script> <!-- Waypoints for Header -->
	<script src="/resources/_include/js/jquery.isotope.js"></script> <!-- Isotope Filter -->
	<script src="/resources/_include/js/jquery.fancybox.pack.js"></script> <!-- Fancybox -->
	<script src="/resources/_include/js/jquery.fancybox-media.js"></script> <!-- Fancybox for Media -->
	<script src="/resources/_include/js/plugins.js"></script> <!-- Contains: jPreloader, jQuery Easing, jQuery ScrollTo, jQuery One Page Navi -->
	<script src="/resources/_include/js/main.js"></script> <!-- Default JS -->
	<script src="/resources/_include/js/common.js"></script>
	<!-- End Js -->
	
<script>
var token = ""

$("#keyUL").on("click", "#keyDelete", function(e) {
	$.ajax({
		url : "/file/deleteKey",
		type : "POST",
		dataType : "text",
		data : {
			userNo : ${user.userNo}
		}
	}).done(function(data){
		console.log(data);
		if(data == "success"){
			showAlert("딩동이 삭제되었습니다.");
			$("#keyUL").html("<li><span>IP주소입력</span><br /><input id='ip' type='text' /><a class='button button-small' id='submitIP'>제품 키 가져오기</a></li>"+
				"<li><span>제품 키</span><br /><input id='dingdongKey' type='text' style='background:#fff' readonly /></li><a class='button button-small' id='keyRegister'>등록</a>");
		}else{
			showAlert("삭제 중 오류가 발생하였습니다.","error");
		}
	}); 
});

	
$("#keyUL").on("click", "#keyRegister",function(e) {
	var key = $("#dingdongKey").val();
	$.ajax({
		url : "/file/registerKey",
		type : "POST",
		dataType : "text",
		data : {
			key : key,
			userNo : ${user.userNo}
		}
	}).done(function(data){
		if(data == "success"){
			showAlert("딩동이 등록되었습니다.");
			$("#keyUL").html("<li>딩동이 등록되었습니다.</li><a class='button button-small' id='keyDelete'>Dingdong 삭제</a>");
		}else{
			showAlert("등록 중 오류가 발생하였습니다.","error");
		}
		console.log("key has been registered---------done");
	}); 
});

$("#keyUL").on("click", "#submitIP", function(e) {
	var ipNo = $("#ip").val();
	console.log(ipNo);
	
	$.ajax({
		url : "http://" + ipNo + ":3000/check",
		type : "GET",
		dataType : "json"
	}).done(function(data){
		var dingdongKey = data.key
		console.log("받아온 키 : " + dingdongKey);
		$("#keyUL #dingdongKey").val(dingdongKey);
	}); 
});

/* function getDeviceList( deviceList ) {
	var li="";
	li += '<div class="contact-details"><br><br>'+
	'<div class="table-responsive"><table class="table" id="deviceTable"><tr>'+
	'<th>기기명</th><th>기기 종류</th><th>알림 수신 여부</th></tr>';
	
	for(var i = 0; i<deviceList.length; i++) {
		li += '<tr><td hidden><input id="checkBoxToken" type="checkbox" value="'+deviceList[i].noti_token+'"></td><td>'+deviceList[i].noti_name+'</td><td>'+deviceList[i].cate_name+'</td>';
		
		if( deviceList[i].noti_receive == '0') {
			li += '<td>X</td></tr>';
		}
		else {
			li += '<td>O</td></tr>';
		}
	}
	li += '</table></div><a class="button button-small" id="registerAjax">알림기기 추가</a>'+
			'<a class="button button-small" id="deleteDeviceAjax">알림기기 삭제</a>'+
			'<span hidden id="deleteSpan"><a class="button button-small" id="delete">삭제</a></span>';

	$("#selectionDevice").html(li);
}; */

$("#register").on("click", function(){
	console.log("aaa");
	console.log("noti_token : "+$("#noti_token").val());
	console.log("noti_name : "+ $("input[name='noti_name']").val());
	console.log("cate_code : " + $("#cate_code option:selected").val());
	console.log("noti_receive : " +$("#radioButton:checked").val());
	
	$.ajax({
		url:"/file/device",
		type:"post",
		data:{
			user_no : ${user.userNo},
			noti_token : $("#noti_token").val(),
			noti_name : $("input[name='noti_name']").val(),
			cate_code :  $("#cate_code option:selected").val(),
			noti_receive :  $("#radioButton:checked").val()
		},
		dataType:"text"
	}).done(function(result){
		console.log("result : "+ result);
		if(result == 'success'){
			showAlert("알림기기가 등록되었습니다.");
			$("#registerContainer").attr("hidden","true");
			getDeviceList();
		}else{
			showAlert("알림기기 등록 중 오류가 발생하였습니다.","error");
		}
	});
});


function getDeviceList() {
	$.ajax({
		url:"/file/getDevice",
		type:"post",
		dataType:"json",
		data: {
			userNo : ${user.userNo}
		}
	}).done(function(deviceList){
		console.log(deviceList);
		var li="";
		li += '<thead><tr><th class="delCol" hidden>&nbsp;</th><th>기기명</th><th>기기 종류</th><th>알림 수신 여부</th></tr></thead>';
		li += '<tbody>';
		
		if(deviceList.length > 0){
			for(var i = 0; i<deviceList.length; i++) {
				li += '<tr><td class="delCol" hidden><input id="checkBoxToken" type="checkbox" value="'+deviceList[i].noti_token+'"></td><td>'+deviceList[i].noti_name+'</td><td>'+deviceList[i].cate_name+'</td>';
				
				if( deviceList[i].noti_receive == '0') {
					li += '<td>X</td></tr>';
				}
				else {
					li += '<td>O</td></tr>';
				}
			}
		}else{
			li += '<tr><td colspan="3">등록된 알림기기가 없습니다.</td></tr>';
		}
		li += '</tbody>';
		$("#deviceTable").html(li);
	
	});
	
	$.ajax({
		url:"/file/getDingdong",
		type:"post",
		dataType:"text",
		data: {
			userNo : ${user.userNo}
		}
	}).done(function(bellToken){
		console.log("bellToken 받아옴");
		var str="";
		
		if(bellToken) {
			str+="<li>딩동이 등록되었습니다.</li><a class='button button-small' id='keyDelete'>Dingdong 삭제</a>";
		}
		else {
			str+="<li><span>IP주소입력</span><br /><input id='ip' type='text' /><a class='button button-small' id='submitIP'>제품 키 가져오기</a></li>"+
						"<li><span>제품 키</span><br /><input id='dingdongKey' type='text' style='background:#fff' readonly /></li><a class='button button-small' id='keyRegister'>등록</a>";
		}
		$("#keyUL").html(str);
	
	});
};

$(".tokenBtn").on("click", function(e){
    $("#getToken").attr("src","https://dingdong-34347.firebaseapp.com").trigger("load");
    $(window).on("message", function(e) {
        $(".deviceToken").val(e.originalEvent.data);
    });
});

	var i=0;
	$("#registerDevice").on("click", function(event){
		console.log("register yyyyyyy");
		
		if (i==0) {
			$("#registerContainer").removeAttr("hidden");
			i=1;
		}
		else {
			$("#registerContainer").attr("hidden","true");
			i=0;
		}
	});
	var j=0;
	$("#registerAjax").on("click", function(event){
		console.log("registerAjax yyyyyyy");
		
		if (j==0) {
			$("#registerContainer").removeAttr("hidden");
			j=1;
		}
		else {
			$("#registerContainer").attr("hidden","true");
			j=0;
		}
	});
	
	
	$("#deleteDevice").on("click", function(e) {
		
		$("#registerDevice").hide();
		$("#deleteSpan").show();
		$("#cancelSpan").show();
		$("#deleteDevice").hide();
		$(".delCol").show();
	});
	
	$("#delete").on("click", function(e){
		var noti_token="";
		$(":checkbox[id='checkBoxToken']:checked").each(function(pi,po){
			noti_token += po.value+"/";
		  });
		console.log("noti_token : "+noti_token);
		
		
		$.ajax({
			url : "/file/mainDelete",
			data : {
				token : noti_token
			},
			dataType:"text"
		}).done(function(data) {
				console.log(data);
				if(data == 'success'){
					showAlert("삭제되었습니다.");
				}else{
					showAlert("삭제되었습니다.","error");
				}
				getDeviceList();

				$("#registerDevice").show();
				$("#deleteDevice").show();
				$("#deleteSpan").hide();
				$("#cancelSpan").hide();
		});
	});
	
	$("#cancel").on("click", function(e){
		$("#registerDevice").show();
		$("#deleteDevice").show();
		$("#deleteSpan").hide();
		$("#cancelSpan").hide();
		$(".delCol").hide();
	});
	
	getDeviceList();
</script>

</body>
</html>