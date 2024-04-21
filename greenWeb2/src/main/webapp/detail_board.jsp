<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${initParam.cssPath }">
</head>
<body>
<h1>Detail Board</h1>
<hr>
<div>
	<table>
		<tr>
			<th>제목</th>
			<td>${board.title }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea cols="20" row="5">${board.title }</textarea></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.writer }</td>
		</tr>
		<tr>
			<th>파일</th>
			<td><img src="images/${board.filename}" width="100px"/></td>
		</tr>
		<tr>
			<th>작성일자</th>
			<td>${board.regdate }</td>
		</tr>
		<tr>
			<th>수정일자</th>
			<td>
				<c:if test="${board.modifydate ne null }">${board.modifydate }</c:if>
				<c:if test="${board.modifydate eq null }">${"수정전" }</c:if>
			</td>
		</tr>
	</table>
</div>
</body>
</html>