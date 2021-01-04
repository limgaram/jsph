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

	번호 : ${detailData.id}
	<br> 
	제목 : ${detailData.title}
	<br> 
	내용 : ${detailData.body}
	<hr>
	댓글
	<br>

	<a href="/board/article?action=commentUpdate&id=${detailData.id}">댓글
		수정</a>
	<a href="/board/article?action=commentdelete&id=${detailData.id}">댓글
		삭제</a>

	<hr>


	<c:choose>
		<c:when test="${loginedMember.id == detailData.id}">
			<a href="/board/article?action=showUpdate&id=${detailData.id}">수정</a>
			<a href="/board/article?action=delete&id=${detailData.id}">삭제</a>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>


</body>
</html>