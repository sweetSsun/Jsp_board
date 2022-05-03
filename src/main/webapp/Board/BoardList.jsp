<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dto.Board" %>
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
		 
		<!-- jsp 형식으로 입력 -->
<%--  		<% ArrayList<Board> boardList = (ArrayList<Board>)request.getAttribute("bdList"); %>
		<% for (int i = 0; i < boardList.size(); i++) { %>
		<tr>
			<td> <%=boardList.get(i).getBno() %></td>
			<td> 
				<a href="<%=request.getContextPath() %>/Board/boardView?bno=<%=boardList.get(i).getBno() %>">
					<%=boardList.get(i).getBtitle() %>
				</a>
			</td>
			<td> <%=boardList.get(i).getBwriter() %></td>
			<td> <%=boardList.get(i).getBdate() %></td>
		</tr>
		<% } %> --%>
		
		<!-- JSTL 사용 -->
 		<c:forEach items="${bdList }" var="board" >
		<tr>
			<td>${board.bno }</td>
			<td><a href="${pageContext.request.contextPath }/Board/boardView?bno=${board.bno }">${board.btitle }</a></td>
			<td>${board.bwriter }</td>
			<td>${board.bdate }</td>
		</tr>
		</c:forEach>
  
		
	</table>
	
</body>
</html>