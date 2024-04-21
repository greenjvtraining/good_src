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
<h1>Reg Member Form</h1>
<hr>
<div>
	<form action="regist_member.do" method="post">
		<input type="text" name="id" placeholder="Input id....">
		<input type="button" name="checkId" value="아이디 중복확인"><br>
		<input type="password" name="pw" placeholder="Input pw...."><br>
		<input type="password" name="repw" placeholder="Input repw...."><br>
		<input type="text" name="name" placeholder="Input name...."><br>
		<input type="date" name="birth" placeholder="Input birth...."><br>
		<input type="tel" name="phone" placeholder="Input phone...."><br>
		<input type="text" name="addr" placeholder="Input addr...."><br>
		<input type="submit" value="등록">
	</form>
</div>
</body>
</html>