<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table, th, td{
		border: 1px solid black;
		border-collapse: collapse;
	}
	th, td{
		padding: 10px;
	}
</style>
</head>
<body>
	<h1>Board/BoardList.jsp</h1>
	<h3>글 목록 페이지</h3>
	<table>
		<tr>
			<th>글번호</th>
			<th>글제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
		<c:forEach items="${bdList }" var="bdData" >
		<tr>
			<td>${bdData.bno }</td>
			<td><a href="">${bdData.btitle }</a></td>
			<td>${bdData.bwriter }</td>
			<td>${bdData.bdate }</td>
		</tr>
		</c:forEach>
	</table>
	
</body>
</html>