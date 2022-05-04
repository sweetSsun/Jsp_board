<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">
	table, th, td{
		border: 1px solid black;
		border-collapse: collapse;
	}
	th, td{
		padding: 10px;
	}
</style>
<body>
	<h1>Board/BoardModifyForm.jsp</h1>
	<h3>글 수정 페이지</h3>
	<form action="boardModify" method="post">
	<table>
		<tr>
			<th>글번호</th>
			<td>${mdBoard.bno }
				<input type="hidden" name="bno" value="${mdBoard.bno }">
				<!--disabled : input창 색 변함, focus 불가능, 서버전송 불가능
					readonly : text input창 같지만 변경 불가, focus 가능, 서버전송 가능-->
			</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${mdBoard.bwriter }</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${mdBoard.bdate }</td>
		</tr>
		<tr>
			<th>글 제목</th>
			<td><input type="text" name="btitle" value="${mdBoard.btitle }"></td>
		</tr>
		<tr>
			<th>글 내용</th>
			<td><textarea rows=10 col=20 name="bcontents">${mdBoard.bcontents }</textarea></td>
		</tr>		
		<tr>
			<th colspan="2"><button type="submit">수정하기</button></th>
		</tr>
	</table>
	
	</form>
</body>
<script type="text/javascript">
	/* function boardModify(mdbno, mdbtitle, mdbcontents){
		console.log("수정할 글 번호 : " + mdbno);
		console.log("수정할 글 제목 : " + mdbtitle);
		console.log("수정할 글 내용 : " + mdbcontents);
		location.href = "boardModify?mdbno="+mdbno+"&mdbtitle="+mdbtitle+"&mdbcontents="+mdbcontents;
	} */
</script>
</html>