<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>댓글 등록하기</h1>
	<form action="/board/article">
		<input type="hidden" name="id" value="${reply.id}">
		<input type="hidden" name="parentid" value="${article.id}">		
		댓글 내용 : <input type="text" name="reply" /><br>
		작성자 : ${loginedMember.nickname}
		<input type="submit" />
	</form>
	

</body>
</html>