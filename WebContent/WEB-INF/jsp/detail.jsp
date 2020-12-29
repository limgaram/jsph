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

	번호 : ${detailDate.id}
	<hr>
	제목 : ${detailDate.title}
	<hr>
	내용 : ${detailDate.body}
	<hr>
	<a href="/board/article?action=update&id=2&title=cccc&body=dddd">수정</a>
	<a href="/board/article?action=delete&id=2">삭제</a>
</body>
</html>