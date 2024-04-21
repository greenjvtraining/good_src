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
<h1>Board List</h1>
<hr>
<div>
	<a href="regist_board_form.do">게시글 작성하기</a>
	<c:if test="${sessionScope.id ne null}"><a href="logout.do">로그아웃</a></c:if>	
</div>
<div id="container">
	<table>
		<thead>
			<tr>
				<th>no</th><th>title</th><th>writer</th><th>regdate</th><th>modifydate</th>
			</tr>
		</thead>
		<tbody>
			<!-- 반복 시작 -->
			<c:forEach var="board" items="${boardList }" varStatus="status">
			<tr>
				<td>${status.count }</td>
				<td><a href="detail_board.do?bno=${board.bno }">${board.title }</a></td>
				<td>${board.writer }</td>
				<td>${board.filename }</td>
				<td>${board.regdate }</td>
				<td>${board.modifydate }</td>
			</tr>
			</c:forEach>
			<!-- 반복 끝 -->
		</tbody>
	</table>
</div>
</body>
</html>