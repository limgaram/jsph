<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시물 수정</h1>

	<form action="/board/article">
		번호 : ${ updateData.id } <br> 
		제목 : <input type="text" name="title"
			value="${ updateData.title }" /> <br> 
		내용 : <input type="text" name="body" value="${ updateData.body }" /> 
		<br> 
			<input type="hidden" name="id" value="${ updateData.id }" /> 
			<input type="hidden" name="action" value="update" /> 
			<input type="submit" />
	</form>
</body>
</html>