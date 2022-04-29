<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>TestPage01.jsp</h1>
	<a href="TestController">TestController로</a>

	<form action="TestController" method="post">
		<input type="submit">
	</form>
	<hr>
	<form action="TestController" method="get">
		아이디: <input type="text" name="testId">
		<br>
		비밀번호: <input type="text" name="testPw">
		<br>
		<input type="submit">
	</form>
		
	<% int num = 10; %>
	<h2> 변수 num은 <%= num %></h2>
	
</body>
</html>