<%@page import="java.sql.ResultSet"%>
<%@ page import="nine.twotwo.boardList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
</head>
<body>

<%
int SNO = (int) session.getAttribute("SNO");
ResultSet rs = null;

/* if((ResultSet)request.getAttribute("notice") != null) { */
rs = (ResultSet)request.getAttribute("notice");
while (rs.next()){%>
번호 :	공지
작성자 :	<%=rs.getString("sname") %>
제목 :	<a href="./view?no=<%=rs.getInt("bno") %>"><%=rs.getString("btitle") %></a>
작성일 :  <%=rs.getDate("BCREATEAT") %>
조회 :	<%=rs.getInt("bviews") %>
<br>
<% } %>

<%
rs = (ResultSet)request.getAttribute("RS");

int maxList = (int) request.getAttribute("maxList");

while (rs.next()){ 
	
%>
번호 :	<%=rs.getInt("bno") %>
작성자 :	<%=rs.getString("sname") %>
제목 :	<a href="./view?no=<%=rs.getInt("bno") %>"><%=rs.getString("btitle") %></a>
작성일 :  <%=rs.getDate("BCREATEAT") %>
조회 :	<%=rs.getInt("bviews") %>
<br>
<%  }  %>

<% String searchInput =  (String) request.getAttribute("searchInput");
for(int i = 0, j = 1; i < maxList; i++) { 
	if(i % 5 == 0) {%>
	
	<a href="./search?listNum=<%=i%>&inputsc=<%=searchInput%>"><%=j%></a> &nbsp;
	
<% j++;} }%>

	<form action="./write" method="get">
		<!-- <input type="text" name="inputTest">	 -->	
		<input type="submit" value="작성">
	</form>
	<br>
	<form action="./search" method="get">
		<input type="text" name="inputsc">
		<input type="submit" value="찾기">
	</form>
</body>
</html>