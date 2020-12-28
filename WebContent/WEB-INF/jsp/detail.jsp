<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시물 상세보기</h1>

	번호 : ${myData2.id}
	<hr>
	제목 : ${myData2.title}
	<hr>
	내용 : ${myData2.body}
	<hr>
	<a href="/web-exam1/article?action=update&id=2&title=cccc&body=dddd">수정</a>
	<a href="/web-exam1/article?action=delete&id=2">삭제</a>
</body>
</html>