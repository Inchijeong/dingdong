<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
			<p>email 찾기</p>
			<label>이름 :</label>
			<input id="userName" type="text" name="userName">
			<label>핸드폰번호 :</label>
			<input id="phone" type="text" name="phone">
			<button id="findEmail" >email 찾기</button>
			<span id="email"></span>
	
	</div>
	
	<div>
		<p>비밀번호 찾기</p>
	
	</div>
</body>
<!--jQuery -->
	<script
		  src="https://code.jquery.com/jquery-3.2.1.min.js"
		  integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
		  crossorigin="anonymous"></script>
		  
<script>
	$(document).ready(function(){
		console.log($("#userName"));
		var button = $("#findEmail");
		
		button.on("click",function(e){
			console.log("이메일찾기 버튼 눌림");
			console.log("나와라"+userName + phone);
			var userName = $("#userName").val();
			var phone = $("#phone").val();
			$.ajax({
				url:"/find/email",
				type:"post",
				data:{
					
						userName: userName,
						phone: phone
					
				},
				dataType:"json"
			}).done(function(result){
				console.log(result);
				$("#email").html(result.email);
			});
			
		})
		
		
	});
</script>
</html>