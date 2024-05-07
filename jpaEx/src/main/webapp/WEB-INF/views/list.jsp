<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>List Page - <span>${cnt }</span></h1>
<hr>
<table border="1">
	<thead>
		<tr>
			<th>번호</th><th>메모</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="memo" items="${list }">
		<tr>
			<td>${memo.mno }</td>
			<td>${memo.memoText }</td>
		</tr>
		</c:forEach>
	</tbody>
	
</table>
</body>
</html>