<%@page import="org.apache.catalina.connector.Request"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="nine.twotwo.boardWrite"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작성</title>
<link rel="stylesheet" href="./css/write.css">

</head>
<body>
<div id="kingDiv">
<div id="writeDiv" class="secondCl">
<h3 id="h3Id">▢글쓰기▢</h3>
</div>

	<form action="./write" method="post">
<div>
<table class="secondCl">
	<tr>
		<td class="centerCl">이름</td>
		<td><input id="nameId" class="nameCl" type="text" value="<%=request.getAttribute("name")%>" disabled></td>
	</tr>
	<tr class="borderCl">
		<td class="centerCl">이메일</td>
		<td><input id="emailId" type="text" value="<%=request.getAttribute("email")%>" disabled></td>
	</tr>
	<tr class="borderCl">
		<td class="centerCl">제목</td>
		<td><input id="titleId" class="titleCl"	type="text" placeholder="제목" name="inputTitle" required></td>
	</tr>
	<tr class="borderCl">	
		<td colspan="2"><textarea id="areaId" placeholder="내용" name="inputContents" required></textarea></td>
	</tr>	
</table>
<div id="buttonId">
		<input id="inputWId" type="submit" value="글쓰기">&nbsp;
		<a href="./list"><input id="inputLId" type="button" value="목록"></a>
</div>		
</div>
	</form>
</div>
</body>
</html>