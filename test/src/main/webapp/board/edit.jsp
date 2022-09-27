<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="nine.twotwo.boardEdit"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정</title>
</head>
<body>
	<h2>내용수정</h2>
<%
	ResultSet rs = (ResultSet)request.getAttribute("edit");
while (rs.next()) {%>	
	
	<form action="./edit" method="POST">
		<input type="text" value="<%=rs.getString("btitle")%>" name="inputTitle"><br>
		<textarea rows="50" cols="50" name="inputContents"><%=rs.getString("bcontents").replace("<br>", "\r\n") %></textarea>
		<input type="submit" value="수정!">
	</form>
	
<% } %>	
</body>
</html>