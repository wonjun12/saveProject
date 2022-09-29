<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="nine.twotwo.boardView"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세페이지</title>
<link rel="stylesheet" href="./css/write.css">

<script type="text/javascript" src="./js/view.js">



</script>

</head>
<body>


	<div id="kingDiv">
		<div id="writeDiv" class="secondCl">
			<h3 id="h3Id">▢상세 글정보▢</h3>
		</div>

		<div>

			<%
			ResultSet rs = (ResultSet) request.getAttribute("detailview");
			while (rs.next()) {
			%>

			<table class="secondCl">
				<tr>
					<td class="centerCl">게시글 번호</td>
					<td><%=rs.getInt("bno")%></td>
				</tr>
				<tr class="borderCl">
					<td class="centerCl">이름</td>
					<td id="nameId" class="nameCl"><%=rs.getString("sname")%></td>
				</tr>
				<tr class="borderCl">
					<td class="centerCl">이메일</td>
					<td id="emailId"><%=rs.getString("semail")%></td>
				</tr>
				<tr class="borderCl">
					<td class="centerCl">제목</td>
					<td class="titleCl" id="titleId"><%=rs.getString("btitle")%></td>
				</tr>
				<tr class="borderCl">
					<td colspan="2"><%=rs.getString("bcontents").replace("<br>", "\r\n")%></td>
				</tr>

			</table>
			<form action="./view" method="POST" id="fomrId">
			<div id="buttonId">
				<%
				int SNO = (int)session.getAttribute("SNO");
				if (rs.getInt("SNO") == SNO) {
				%>
					<a href="./edit?bno=<%=rs.getInt("bno")%>"><input
						type="button" value="수정"></a> &nbsp; <%} %>
					<% if((int) session.getAttribute("SNO") == 1){ %>
						<input type="button" value="삭제" id="viewinputId" onclick="deleteFnc()">&nbsp;&nbsp;
					
					<%
					 }
					%>
	
					<a href="./list"> <input type="button" value="목록">
					</a>
				</div>
			</form>
			<%
				if(request.getAttribute("commtView") != null){
				ResultSet comRs = (ResultSet) request.getAttribute("commtView");
					while(comRs.next()) {
				%>
					작성자 : <%=comRs.getString("SNAME")%>
					내용 : <%=comRs.getString("COMMENTS")%>
					<br>
				<% } }
				%>
			<form action="./commt" method="POST">
				<input type="text" name="inputCommt">
				<input type="submit" value="작성">
			</form>	
		</div>

		<%
		}
		%>
	</div>
</body>
</html>