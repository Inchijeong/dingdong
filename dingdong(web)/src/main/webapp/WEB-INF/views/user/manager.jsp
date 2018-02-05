<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<sec:authentication var="user" property="principal" />
<h1>관리자</h1>
	<sec:authorize access="hasRole('ROLE_USER')">
		<form action="/user/userMod">
		<input type="hidden" name="email" value="${user.username}">
		<button>정보수정하기</button>
		</form>
	</sec:authorize>
</body>
</html>