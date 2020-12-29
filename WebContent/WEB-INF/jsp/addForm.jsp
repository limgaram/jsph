<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시물 등록</h1>
<body>
	<form action="/board/article">
		제목 : <input type="text" name="title" /> 
		<br>
		내용 : <input type="text" name="body" />
		<br>
		<input type="hidden" name="mid" value="${loginedMember}" />
		<input type="hidden" name="action" value="insert" />
		<input type="submit" />
	</form>	
</body>


</html>