<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${initParam.cssPath }" >
</head>
<body>
<h1>Detail Notice</h1>
<hr>
<table>
	<tr>
		<th>제목</th>
		<td>${notice.title }</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>${notice.content }</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>${notice.writer }</td>
	</tr>
	<tr>
		<th>작성일자</th>
		<td>${notice.regdate }</td>
	</tr>
</table>
<div>
	<span id="txt_reply">댓글작성</span>
</div>
<div id="reply_div">
	
	<input type="hidden" id="reply_nno" name="reply_nno" value="${notice.nno }">
	<input type="text" id="reply_content" name="reply_content">
	<input type="hidden" id="reply_writer" name="reply_writer" value="${id }">
	<input type="button" value="등록" onclick="reg_reply()">
</div>
<div id="demo"></div>
<script>
	const txt_reply = document.querySelector("#txt_reply");
	const reply_div = document.querySelector("#reply_div");
	const reply_nno = document.querySelector("#reply_nno");
	const reply_content = document.querySelector("#reply_content");
	const reply_writer = document.querySelector("#reply_writer");
	
	reply_div.style.display = "none";
	txt_reply.addEventListener("click", function(){
		reply_div.style.display = "block";
	});
	
	function reg_reply(){
		const xhttp = new XMLHttpRequest();
		xhttp.onload = function() {
		    document.getElementById("demo").innerHTML = this.responseText;
		}
		xhttp.open("post", "reg_reply.do", true);
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhttp.send("nno=" + reply_nno.value + "&content=" + reply_content.value + "&writer=" + reply_writer.value);
	}
	
	
</script>
</body>
</html>