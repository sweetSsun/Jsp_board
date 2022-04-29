<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>TestPage02.jsp</h1>

	<% String testData = (String)request.getAttribute("testData01"); %>
	<h2>전달받은 데이터 : <%=testData %></h2>
</body>
</html>