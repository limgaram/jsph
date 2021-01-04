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
	<form action="/board/member">
		아이디 <br> 
		<input type="test" name="loginId" /> <br> 
		비밀번호 <br>
		<input type="test" name="loginPw" /> <br> 
		이름 <br> <input type="test" name="nickname" /> <br> 
		<input type="submit" value="가입하기" /><br> 
		<input type="hidden" name="action" value="doInsertMember">
	</form>
</body>
</html>