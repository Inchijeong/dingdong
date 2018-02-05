<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
<head>
	<!-- Meta Tags -->
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	
	<title>DingDong</title>   
	<link href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
	<link href="/resources/_include/css/user.css" rel="stylesheet">
		<style>
			#findAccount,#resultSearch
			{
				display: none;
			}
		
			
			#Email{
				color : black;
				size: 12px;
				font: bold;
				
			}
		</style>
	
</head>
<body>
	<div class="materialContainer">

	<div id="resultSearch" class="box">
	<div class="title">회원정보검색결과<span id="Email"></span></div>
	</div>
 <div id="findAccount">
			<div class="title">Find Email</div>
				
				<form>
				
				<div class="input">
					<label for="FindUserName">Name</label>
					<input id="FindUserName" type="text" name="userName">
					<span class="spin"></span>		
				</div>
				
				<div class="input">
					<label for="FindPhone">Phone</label>
					<input id="FindPhone" type="text" name="phone">
					<span class="spin"></span>		
				</div>
				
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				
				<div class="button login">
        	<button type="button" id="findEmail"><span>Search</span><i class="fa fa-check"></i></button>
      	</div>
      	
      	
	
			</form>
		</div>
	
		<div id="login" class="box">
			<div class="title">LOGIN</div>
			<form method="post">
				<div class="input">
					<label for="username">Email</label> 
					<input type="text" id="username" name="username" />
					<span class="spin"></span>
				</div>
				
				<div class="input">
					<label for="password">Password</label> 
					<input type="password" id="password" name="password" />
					<span class="spin"></span>		
				</div>
		
				<div class="remember-me">
					<input type="checkbox" id="remember-me" name="remember-me" />
					<label for="text">Remember-Me</label> 
				</div>
		
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<div class="button login">
        	<button type="submit" id="formBtn"><span>Login</span><i class="fa fa-check"></i></button>
      	</div>
      	
      	<a id="findAccountBtn" class="pass-forgot">Forgot your password?</a>
      	
			</form>
		</div>
	
		<div id="signup" class="overbox">
			<div class="material-button alt-2"><span class="shape"></span></div>
			<div class="title">REGISTER</div>
			<form action="/user/signUpPost" method="post">
				<div class="input">
					<label for="email">Email</label>
					<input type="text" name="email" id="email">
					<span class="spin"></span>
				</div>
				
				<div class="input">
					<label for="regUserName">Name</label>
					<input type="text" name="userName" id="regUserName">
					<span class="spin"></span>
				</div>
				<div class="input">
					<label for="regPassword">Password</label>
					<input type="password" name="password" id="regPassword">
					<span class="spin"></span>
				</div>
				
				<div class="input">
					<label for="regPasswordChk">Confirm Password</label>	
					<span id="result"></span>
					<input type="password" name="passwordChk" id="regPasswordChk">
					<span class="spin"></span>
				</div>
				
				<div class="input">
					<label for="phone">Cell Phone</label>
					<input type="text" name="phone" id="phone">
					<span class="spin"></span>
				</div>
				
				
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<br>
				<div id="result">
				</div>
				
				<div class="button">
					<button class="btn btn-lg btn-success btn-block" id="signUpBtn" type="submit">Sign Up</button>
				</div>
			</form>
		</div>
	</div>	
			
	<!--jQuery -->
	<script
		  src="https://code.jquery.com/jquery-3.2.1.min.js"
		  integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
		  crossorigin="anonymous"></script>
	<script src="/resources/_include/js/user.js"></script> 
	
	<script>
		$(document).ready(function(){
			var $signUpBtn = $("#signUpBtn");
			var $result = $("#result");
			var $passwordChk = $("#regPasswordChk");
			var $password = $("#regPassword");
			var button = $("#findEmail");
			
			$("#findAccountBtn").on("click", function(e){
				$("#login").hide();
				$(".material-button").hide();
				$("#findAccount").show();
			});
			
			button.on("click",function(e){
				var userName = $("#FindUserName").val();
				var phone = $("#FindPhone").val();
				console.log("이메일찾기 버튼 눌림");
				console.log("나와라"+userName + phone);
				$("#findAccount").hide();
				$("#resultSearch").show();
				$.ajax({
					url:"/find/email",
					type:"post",
					data:{
				
							userName: userName,
							phone: phone
						/* 	${_csrf.parameterName}: '${_csrf.token}' */
					},
					dataType:"json"
				}).done(function(result){
					if(result.email == null){
						$("#Email").html('해당하는 정보가 없습니다');
					}else{
						$("#Email").html(
								"<div class='input'>" +
								"<input type='text' value='검색된 아이디' readonly >" +
								"<br><br><br>" +
								result.email +
								"</div>");
					}
				});
				
			});
		
			
			
			$(".remember-me").on("click", function(){
				var $rememberMe = $("#remember-me");
				$rememberMe.prop("checked",!$rememberMe.prop("checked"));
			});
			
			$passwordChk.blur(function(){
				if ($password.val() !== $passwordChk.val()){
					$signUpBtn.attr('type', "button");
					$result.html("비밀번호를 확인해주세요.");
				}else{
					$result.html("");
					$signUpBtn.attr('type', "submit");
				}
			});
		});
	</script>
			
</body>
</html>