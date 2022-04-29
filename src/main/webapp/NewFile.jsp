<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	h2{
		color: orange;
	}
</style>
</head>
<body>
<!-- 자바코드의 시작과 끝을 알리는 문자 -->
<%
	int num = 10;
	String str1 = "문자";
	boolean boolVal = true;
%>
	<h2>테스트 페이지</h2>
	<h2>정수 num : <%=num %></h2>
	<h3>스트링 str1: <%=str1 %></h3>
	
	<% if(num==10) { %>
		<h3>num은 10입니다</h3>
	<% } else { %>
		<h3>num은 10이 아닙니다</h3>
	<% } %>
</body>
<script type="text/javascript">
</script>
</html>