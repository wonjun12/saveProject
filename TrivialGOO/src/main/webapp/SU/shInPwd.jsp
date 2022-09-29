<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SUIN FORM</title>
<link rel="stylesheet" href="./css/pwd.css">

<script type="text/javascript" src="./js/pwd.js">
</script>
</head>
<body>
<jsp:include page="../google/Header.jsp" />
	
	<h3>시작하기</h3>
	
	
	<a href="./shInId.jsp" >
		<% if(request.getAttribute("MyEmail") != null) {%>
			<input type="button" value=<%=(String)request.getAttribute("MyEmail") %>>
		<%} %>
	</a>
	
	<form action="./in" method='POST' id="formId">
		<input type="password" name="sPwd" placeholder="비밀번호"
			onfocus="this.placeholder=''" onblur="this.placeholder='비밀번호'" id="pwdId">
			<br>
		<div class="warning">
		<p id="searchPwdId">
		<% if(request.getAttribute("pwdError") != null) {%>
		<%=(String)request.getAttribute("pwdError") %>
		<%} %>
		</p>
		</div>
		<div>
			<input type="checkbox" id="checkId" onclick="pwdCkFnc()"> 비밀번호 표시
		</div>
		<span>
			<a href="./myEmailSelect.jsp" >비밀번호 찾기</a>
		</span>
		<input type="button" value="다음" onclick="submitFnc()">
	</form>
	
</body>
</html>