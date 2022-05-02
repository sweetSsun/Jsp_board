<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>BoardMain.jsp</h1>
	<hr>
	<h3> <a href="Board/BoardWrite.jsp">글 작성 페이지</a></h3>
	<!-- insert 용도 :: redirect -->
	<h3> <a href="Board/BoardList.jsp">글 목록 페이지</a></h3>
	<!-- select 용도 :: dispatcher, 서블릿 호출 (글 목록을 받아오기 위해) -->
</body>
</html>