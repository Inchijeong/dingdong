<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<style>
	#pass{
		color: red;
	}
</style>
</head>
<body>
				<hr>
					<!-- 회원정보 수정 -->
				<div id="signup">
				  <h3>회원수정</h3><br>
						<form action="/user/userModPost" method="post">
						 	<label>이름</label>
							<input type="text" value="${userName}" name="userName" id="userName" required readonly><br>
							<label>이메일</label>
							<input type="email" value="${email}" name="email" id="email" required readonly><br>
							<label>비밀번호</label>
							<input type="password"  name="password" id="password" required><br>
							<label>비밀번호 확인</label>
							<input type="password"  name="passwordChk" id="passwordChk" required>
							<span id="result"></span><br>
							<label>휴대폰 번호</label>
							<input type="text" value="${phone}" name="phone" id="phone" required>
							<input type="hidden" value="${userNo}" name="userNo">
							<br>
							<button class="btn btn-lg btn-success btn-block" id="signUpBtn" type="submit">수정하기</button>
						</form>
				</div>
				
				<hr>

		           <a class="text-muted" href="/board/recruit/list">취소</a>
		    
				
			
	
	<!--jQuery -->
	<script
		  src="https://code.jquery.com/jquery-3.2.1.min.js"
		  integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
		  crossorigin="anonymous"></script>
	
	<script>

		console.log("${member}");
	
	
		var $signUpBtn = $("#signUpBtn");
		var $result = $("#result");
		var $passwordChk = $("#passwordChk");
		var $password = $("#password");
		$(document).ready(function(){
			
			
			$passwordChk.blur(function(){
				if ($password.val() !== $passwordChk.val()){
					$signUpBtn.attr('type', "button");
					$result.html("<span id='pass'>비밀번호를 확인해주세요.</span>");
				}else{
					$result.html("");
					$signUpBtn.attr('type', "submit");
				}
			});
			
			
			
		});
	</script>
			
</body>
</html>