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
		번호 : ${ myData3.id } <br> 
		제목 : <input type="text" name="title"
			value="${ myData3.title }" /> <br> 
		내용 : <input type="text" name="body" value="${ myData3.body }" /> 
		<br> 
			<input type="hidden" name="id" value="${ myData3.id }" /> 
			<input type="hidden" name="action" value="update" /> 
			<input type="submit" />
	</form>
</body>
</html>