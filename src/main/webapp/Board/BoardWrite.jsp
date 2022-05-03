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
<script type="text/javascript">
                   // request.getParameter("checkMsg");
                   // request에서 값을 받아올 때 object 타입이라서 꼭 형 변환을 해주어야 하듯이
	var checkMsg = "${param.checkMsg}";
	// "" 안에 넣어서 문자로 형 변환을 해준 것
	// 컴퓨터가 봤을 때 1이 숫자인지, 문자인지 알 수 없기 때문에
	console.log(checkMsg);
	console.log(typeof checkMsg);
	if (checkMsg == "1") {
		alert("글 작성에 실패했습니다.");
	}		 
</script>
</head>
<body>
	<h1>Board/BoardWrite.jsp</h1>
	<h3>글 작성 페이지</h3>
	<form action="boardWrite" method="post">
	<table>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="bwriter"></td>
		</tr>	
		<tr>
			<th>글 제목</th>
			<td><input type="text" name="btitle"></td>
		</tr>	
		<tr>
			<th>글 내용</th>
			<td><textarea rows="10" cols="20" name="bcontents"></textarea></td>
		</tr>	
		<tr>
			<th colspan="2">
				<input type="reset" value="다시작성">
				<input type="submit" value="글작성">
			</th>
		</tr>		
	</table>
	</form>
	
</body>
</html>