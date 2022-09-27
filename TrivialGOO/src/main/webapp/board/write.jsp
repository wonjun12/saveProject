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
	
	<input type="text" value="<%=request.getAttribute("name")%>" disabled><br>
	<input type="text" placeholder="제목" name="inputTitle" required><br>
	<textarea cols="50" rows="50" placeholder="내용" name="inputContents" required></textarea>
	<br>
	<input type="submit" value="작성">
</form>

</body>
</html>