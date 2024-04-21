<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${initParam.cssPath }">
</head>
<body>
<h1>Reg Board Form</h1>
<hr>
<div>
	<form action="regist_board.do" method="post" enctype="multipart/form-data">
		<input type="text" name="title" placeholder="Input title...."><br>
		<input type="text" name="content" placeholder="Input content...."><br>
		<input type="text" name="writer" value="${id }"><br>
		<input type="file" name="uploadFile" ><br>
		<input type="submit" value="등록">
		<input type="button" value="목록보기" onclick="javascript:location.href='boardList.do'">
	</form>
</div>
</body>
</html>