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
<hr>
<div>
	<!-- 전제1 : 페이지 당 게시물 수는 10개로 한다. -->   
	<!-- 컨트롤러에서 전체 페이지수(cnt)와 페이지번호(페이지네이션:pageNum)가 넘어온다. -->
	<!-- 전체 페이지 수에 따라 페이지번호 개수가 결정된다. ex) 89개 : 9개 - 89/10(올림)-->
	<!-- 페이지번호에 따라 화면에 보여질 게시글의 시작번호, 끝번호가 결정된다. -->
	<!-- 페이지번호에 따라 페이지블록도 결정된다. -->
	<!-- 페이지 블록의 시작과 끝이 결정된다는 뜻이다. --><!-- 페이지 번호가 6이면 페이지블록은 2 -->
	<c:set var="start" value="1" />
	<c:set var="end" value="${Math.ceil(cnt/(10.0)) }" />
	
	<span><a href="">[이전]</a></span>&nbsp;&nbsp;
	<c:forEach var="i" begin="${start }" end="${end }"> <!--  -->
		<a href="list2?pageNum=${i }">${i}</a> &nbsp;&nbsp;
	</c:forEach>&nbsp;&nbsp;
	<span><a href="">[다음]</a></span>
	
	
	
</div>
</body>
</html>