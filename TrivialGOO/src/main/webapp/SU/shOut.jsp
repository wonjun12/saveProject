<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
  <%@ page session = "true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나는 게시판이얌</title>
<link rel="stylesheet" href="./css/out.css">

</head>
<body>
	
	<% session = request.getSession();
		String strName = (String) session.getAttribute("name");
	%>
	<% session = request.getSession();
		String strEmail = (String) session.getAttribute("email");
	%>
	<div class="center">
		<jsp:include page="../google/Header.jsp" />
		
		<div class="line">
			<div class="name"><%=strName%></div>
			<div class="name"><%=strEmail%></div>
		</div>
		<div class="name">
			<form action="./out" method='get'>
			<input type="submit" value="로그아웃">
		</form>
		</div>
		
	</div>
</body>
</html>