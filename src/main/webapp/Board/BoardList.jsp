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
<script src="https://kit.fontawesome.com/9125416ae4.js" crossorigin="anonymous"></script>
</head>
<script type="text/javascript">
	var checkMsg = "${param.checkMsg}";
	if (checkMsg) {
		alert("글이 삭제되었습니다.")
	}
</script>
<body>
	<h1>Board/BoardList.jsp</h1>
	<h3>글 목록 페이지</h3>
	<table>	
	
	<c:if test="${param.searchText != '' and param.searchText != null }">
	<!-- test : 조건이 들어가는 영역
	${param.searchText != '' } : parameter에 담긴 값이 길이가 0이 아니거나
	${param.searchText != null } : null값이 아니거나 -->
	<!-- searchText라는 파라미터는 "/Board/boardSearch"가 호출된 후
	dispatcher 방식으로 response 받아 request의 파라미터가 존재하고 있기 때문에 사용 가능 -->
		<tr>
			<th colspan="4">
				[${param.searchText }] 검색 결과입니다.
			</th>
		</tr>
	</c:if>
		<tr>
			<th colspan="4">
			<form action="boardSearch" method=get>
				<select name="searchType" id="searchType" onchange="changePlaceholder()">
					<option value="btitle">제목</option>
					<option value="bwriter">작성자</option>
					<option value="bcontents">내용</option>
					<option value="titleContents">제목/내용</option>
					
				</select>
				<input type="text" name="searchText" id="searchText" placeholder="검색할 글제목">
				<input type="submit" value="검색">
				<!-- 검색 결과는 BoardList.jsp를 재활용 -->
			</form>
			</th>
		</tr>
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
			<td>
				<a href="${pageContext.request.contextPath }/Board/boardView?bno=${board.bno }">${board.btitle }</a>
				<c:if test="${board.bfilename != null }">
					<i class="fa-regular fa-file"></i>
				</c:if>				
			</td>
			<td>${board.bwriter }</td>
			<td>${board.bdate }</td>
		</tr>
		</c:forEach>		
	</table>	
</body>
<script type="text/javascript">
	function changePlaceholder(){
		var check = document.getElementById("searchType").value;
		console.log(check);
		if (check == "btitle") {
			check = "검색할 제목";
		} else if (check == "bwriter") {
			check = "검색할 작성자";
		} else if (check == "bcontents"){
			check = "검색할 내용"
		} else {
			check = "검색할 제목 또는 내용"
		}
		document.getElementById("searchText").placeholder = check;
	}
</script>
</html>