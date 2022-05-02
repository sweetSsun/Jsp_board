<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- prefix에 쓰여져있는 부분이 태그로 사용하겠다는 명칭 약속 -->
    
<%@ page import="java.util.ArrayList"%>
<%@ page import="dto.InputTest" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Success.jsp</h2>
	<h3>입력 성공!</h3>
	<hr>
	<% ArrayList<InputTest> testList = (ArrayList<InputTest>) request.getAttribute("testList");%>
	<!-- object 타입을 갖게되기 때문에 형 변환을 시켜 저장해주어야 함 -->
	
	<h2>데이터 출력</h2>
	<% for(int i = 0; i < testList.size(); i++) { %>
		<p> testCol1 : <%=testList.get(i).getTestcol1() %>
		  , testCol2 : <%=testList.get(i).getTestcol2() %> </p>
	<% } %>
	<!-- = 표시는 해당 데이터를 페이지에 출력하겠다는 뜻 -->	
	
	<hr>
	<table border="1">
		<tr>
			<th>TESTCOL1</th>
			<th>TESTCOL2</th>
		</tr>
		<% for(int i = 0; i < testList.size(); i++) { %>
			<% if(testList.get(i).getTestcol1() != null ) { %>
		<tr>
			<td><%=testList.get(i).getTestcol1() %></td>
			<td><%=testList.get(i).getTestcol2() %></td>
		</tr>
			<% } %>
		<% } %>
	</table>
	
	<hr>
	<table border="1">
		<tr>
			<th>TESTCOL1</th>
			<th>TESTCOL2</th>
		</tr>		
		<c:forEach items="${testList }" var="tData">
		<tr>
			<td>${tData.testcol1 }</td>
			<td>${tData.testcol2 }</td>
		</tr>
		</c:forEach>
	</table>
		<!-- c: core 라이브러리를 사용하는 태그 -->
		<!-- forEach : for문 -->
		<!-- items : 반복을 시킬 객체 -->
		<!-- var : 반복을 시킬 객체를 담을 변수 (해당 인덱스의 값을 tData에 저장) -->
		<!-- $ : %=와 같은 표현식 -->
		
		<!-- ${testList } : request.Attribute에 담겨진 testList -->
		<!-- tData의 testcol1 필드 (getter, setter가 있을 때만 사용할 수 있음) -->
		
		
</body>
</html>