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
<h1>Login Form</h1>
<hr>

<div>
	<form action="login.do" method="post">
		<input type="text" name="id" placeholder="Input id...."><br>
		<input type="text" name="pw" placeholder="Input pw...."><br>
		<input type="submit" value="로그인" >
		<input type="button" value="회원가입" onclick="javascript:location.href='regist_member_form.do'">
	</form>
</div>
</body>
</html>