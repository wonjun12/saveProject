<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="nine.twotwo.boardView"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
</head>
<body>
	<h2>내 글 조회</h2>
	
 	<%
 	int SNO = (int) session.getAttribute("SNO");
 	ResultSet rs = (ResultSet)request.getAttribute("selectview");
 	while (rs.next()){%>
	
	번호 :	<%=rs.getInt("bno") %>
	제목 :	<%=rs.getString("btitle") %>
	내용 :	<%=rs.getString("bcontents") %>
	작성자 : 	<%=rs.getString("sname") %>
	작성일 :	<%=rs.getDate("bcreateat") %>
	조회수 :	<%=rs.getInt("bviews") %>
	<br>
		<% if(SNO == rs.getInt("SNO")) { %>
		<a href="./edit?bno=<%=rs.getInt("bno")%>">
		<input type="button" value="수정">
		</a>
	<form action="./view" method="POST">
		<input type="submit" value="삭제">
	</form>
	
	<%} } %>
	
	<form action="./list" method="get">
		<input type="submit" value="목록">
	</form>
</body>
</html>