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
	<div style="background-color: skyblue; width:250px; height:100px;">
		번호 : ${detailData.id} <br> 
		제목 : ${detailData.title} <br> 
		내용 : ${detailData.body}
	</div>
	<hr>
	<h3>댓글</h3>
	<br>
	<form action="/board/article">
		<input type="hidden" name="id" value="${reply.id}"> 
		<input type="hidden" name="parentid" value="${article.id}"> 
		댓글 내용 : <input type="text" name="reply" /><br> 
		작성자 : ${loginedMember.nickname}<br> 
		<input type="submit" />
	</form>

	<a href="/board/article?action=commentUpdate&id=${detailData.id}">댓글
		수정</a>
	<a href="/board/article?action=commentdelete&id=${detailData.id}">댓글
		삭제</a>

	<hr>


	<c:choose>
		<c:when test="${loginedMember.id == detailData.id}">
			<a href="/board/article?action=showUpdate&id=${detailData.id}">게시물 수정</a>
			<a href="/board/article?action=delete&id=${detailData.id}">게시물 삭제</a>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>


</body>
</html>