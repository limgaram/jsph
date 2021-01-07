<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시물 상세보기</h1>
	<div style="background-color: skyblue; width: 250px; height: 100px;">
		번호 : ${detailData.id} <br> 제목 : ${detailData.title} <br> 내용
		: ${detailData.body}
	</div>

	<hr>
	<h3>댓글 리스트</h3>
	<c:forEach var="reply" items="${replyData}">
		<c:choose>
			<c:when test="${flag == 'u' && rid == reply.id}">
				작성자 : ${loginedMember.nickname} <br>
				<form action="/board/article">
					<input type="text" name="rbody" placeholder="${reply.body}"> 
					<input type="text" name="rid" placeholder="${reply.id}"> 
					<input type="hidden" name="aid" value="${detailData.id}"> 
					<input type="hidden" name="action" value="doUpdateReply"> 
					<input type="submit" value="댓글 등록" />
				</form>
			</c:when>
			<c:otherwise>
			${reply.nickname} <br>
			${reply.body} <br>
			${reply.regDate} <br>
				<c:if test="${reply.mid == loginedMember.id}">
					<a href="/board/article?action=showReplyUpdate&id=${reply.id}&aid=${detailDate.id}">댓글수정</a>
					<a
						href="/board/article?action=doDeleteReply&id=${reply.id}&aid=${detailDate.id}">댓글삭제</a>
				</c:if>
			</c:otherwise>
		</c:choose>
	</c:forEach>

	<hr>

	작성자 : ${loginedMember.nickname}
	<br>
	<form action="/board/article">
		<input type="text" name="rbody" placeholder="댓글을 남겨보세요."> <input
			type="hidden" name="aid" value="${detailData.id}"> <input
			type="hidden" name="mid" value="${loginedMember.id}"> <input
			type="hidden" name="action" value="doInsertReply"> <input
			type="submit" value="댓글 등록" />
	</form>

	<hr>
	<!-- 
	<c:choose>
		<c:when test="${loginedMember.id == detailData.id}">
			<a href="/board/article?action=showUpdate&id=${detailData.id}">게시물
				수정</a>
			<a href="/board/article?action=delete&id=${detailData.id}">게시물 삭제</a>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
 -->

</body>
</html>