<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>admin</h1>
<sec:authentication var="user" property="principal" />

<sec:authorize access="hasRole('ROLE_ADMIN')">
${user.userNo}
</sec:authorize>

</body>
<script>
	console.log("${user}");
	console.log('${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}');
</script>
</html>