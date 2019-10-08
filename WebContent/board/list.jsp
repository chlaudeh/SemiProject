<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/board/list.jsp</title>
</head>
<body>
<c:set var="cp" value="${pageContext.request.contextPath }"/>
<h1>게시글목록</h1>
<table border="1" width="500">
	<tr>
		<th>글번호</th><th>작성자</th><th>제목</th><th>삭제</th>
	</tr>
	<c:forEach var="vo" items="${list }">
		<tr>
			<td>${vo.num }</td>
			<td>${vo.writer }</td>
			<td><a href="${cp }/board/detail?num=${vo.num}">${vo.title }</a></td>
			<td><a href="${pageContext.request.contextPath }/board/delete?num=${vo.num}">삭제</a></td>
		</tr>
	</c:forEach>
</table>
<div><!-- 페이징처리 -->
	<c:choose>
		<c:when test="${startPageNum>3 }">
			<a href="${cp }/board/list?pageNum=${startPageNum-1}&field=${field}&keyword=${keyword}">[이전]</a> 
		</c:when>
		<c:otherwise>
			[이전]
		</c:otherwise>
	</c:choose>
	<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
		<c:choose>
			<c:when test="${pageNum==i }"><%--현재페이지 색 다르게 표시하기 --%>
				<a href="${cp }/board/list?pageNum=${i}&field=${field}&keyword=${keyword}">
				<span style="color:red;">[${i }]</span>
				</a>
			</c:when>
			<c:otherwise>
				<a href="${cp }/board/list?pageNum=${i}&field=${field}&keyword=${keyword}">
				<span style="color:#555;">[${i }]</span>
				</a>
			</c:otherwise>
		</c:choose>					
	</c:forEach>
	<c:choose>
		<c:when test="${endPageNum<pageCount }">
			<a href="${cp }/board/list?pageNum=${endPageNum+1}&field=${field}&keyword=${keyword}">[다음]</a>
		</c:when>
		<c:otherwise>
			[다음]
		</c:otherwise>
	</c:choose>
</div>

<div>
	<form method="post" action="${cp }/board/list">
		<select name="field">
			<option value="writer"
			 <c:if test="${field=='writer'}">selected</c:if>>작성자</option>
			<option value="title" 
			<c:if test="${field=='title'}">selected</c:if>>제목</option>
			<option value="content" 
			<c:if test="${field=='content'}">selected</c:if>>내용</option>
		</select>
		<input type="text" name="keyword" value="${keyword }">
		<input type="submit" value="검색">
	</form>
</div>
</body>
</html>











