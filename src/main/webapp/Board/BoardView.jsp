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
	<h1>Board/BoardView.jsp</h1>
	<h3>글상세페이지</h3>
	<table>
		<tr>
			<th>글번호</th>
			<td>${board.bno }</td>
			<!-- request에 담겨져서 넘어온 객체 board의 bno라는 필드 (get이 있어야만 사용 가능한 표현) -->
		</tr>
		<tr>
			<th>글제목</th>
			<td>${board.btitle }</td>
		</tr>
		<tr>
			<th>글작성자</th>
			<td>${board.bwriter }</td>
		</tr>
		<tr>
			<th>글작성일</th>
			<td>${board.bdate }</td>
		</tr>
		<tr>
			<th>글내용</th>
			<td>${board.bcontents }</td>
			<!-- textarea에 넣어주면 개행문자 변환해주지 않아도 입력헀던 대로 볼 수 있음 -->
		</tr>
		<tr>
			<th colspan="2">
				<button>글수정</button>
				<button onclick="boardDelete('${board.bno }', '${board.btitle }')">글삭제</button> 
				<!-- onclick 속성을 표현하기 위해 function이 더블쿼터를 사용하고 있기 때문에
				 그 내부의 문자표현(파라미터)은 싱글쿼터로 표현한다 -->
			</th>
		</tr>
	</table>
</body>

<script type="text/javascript">
	function boardDelete(delBno, delTitle){
		console.log("boardDelete() 호출")
		console.log("삭제할 글번호 : " + delBno);
		console.log("삭제할 글제목 : " + delTitle);
		location.href = "boardDelete?delBno="+delBno+"&delBtitle="+delTitle;
	}
</script>
</html>