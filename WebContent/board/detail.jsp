<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/board/detail.jsp</title>
</head>
<body>
<h1>상세글보기</h1>
<table border="1" width="600">
	<tr>
		<td>글번호</td>
		<td>${vo.num }</td>
	</tr>
	<tr>
		<td>작성자</td>
		<td>${vo.num }</td>
	</tr>
	<tr>
		<td>제목</td>
		<td>${vo.title }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td><textarea rows="5" cols="50" readonly="readonly">${vo.content }</textarea> </td>
	</tr>
	
</table>
</body>
</html>







