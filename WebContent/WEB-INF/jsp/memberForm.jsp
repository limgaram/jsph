<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입 페이지</h1>
	<form action="/board/article">
		아이디 <br> 
		<input type="test" name="loginId" /> 
		비밀번호 <br> 
		<input type="test" name="loginPw" /> 
		이름 <br> 
		<input type="test" name="nickname" />
		<input type="hidden" name="action" value="doInsertMember">
	</form>
</body>
</html>