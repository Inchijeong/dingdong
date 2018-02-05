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
<body class="login">
			<hr>
				
				<hr>
					
					<!-- 회원가입 -->
					<div id="signup">
				  <h3>회원가입</h3><br>					
						<form action="/user/signUpPost" method="post">
							<label>이메일</label>
							<input type="text" placeholder="이메일ID" name="email" id="email" value="user01"><br>
							<label>이름</label>
							<input type="text" placeholder="이름" name="userName" id="userName" value="chi"><br>
							<label>비밀번호</label>
							<input type="password" placeholder="비밀번호" name="password" id="password" value="123"><br>
							<label>비밀번호 확인</label>
							<input type="password" placeholder="비밀번호 확인" name="passwordChk" id="passwordChk" value="123">
							<span id="result"></span><br>
							<label>휴대폰 번호</label>
							<input type="text" placeholder="휴대폰 번호" name="phone" id="phone" value="01012345678">
							
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<br>
							<div id="result">
							</div>
							
							
							<button class="btn btn-lg btn-success btn-block" id="signUpBtn" type="submit">회원가입</button>
						</form>
					</div>
				
				<hr>
		    <div class="text-center">
		        <ul class="list-inline">
		            <li><a class="text-muted" href="/user/login">로그인</a></li>
		            <li><a class="text-muted" href="/user/findPw">비밀번호 찾기</a></li>
		        </ul>
		    </div>				
				
			
	
	<!--jQuery -->
	<script
		  src="https://code.jquery.com/jquery-3.2.1.min.js"
		  integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
		  crossorigin="anonymous"></script>
	
	<script>
		var $signUpBtn = $("#signUpBtn");
		var $result = $("#result");
		var $passwordChk = $("#passwordChk");
		var $password = $("#password");
		$(document).ready(function(){
			
			
			$passwordChk.blur(function(){
				if ($password.val() !== $passwordChk.val()){
					$signUpBtn.attr('type', "button");
					$result.html("<p id='pass'>비밀번호를 확인해주세요.</p>");
				}else{
					$result.html("");
					$signUpBtn.attr('type', "submit");
				}
			});
			
			
			
		});
	</script>
			
</body>
</html>