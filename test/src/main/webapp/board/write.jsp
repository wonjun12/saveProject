<%@page import="org.apache.catalina.connector.Request"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="nine.twotwo.boardWrite"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작성</title>
</head>
<body>

<form action="./write" method="post">
	
	<input type="text" value="<%=request.getAttribute("name")%>"><br>
	<input type="text" placeholder="제목" name="inputTitle"><br>
	<input type="text" placeholder="내용" name="inputContents"><br>
	<input type="submit" value="성공했냐?">
</form>

</body>
</html>