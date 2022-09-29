<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Google 계정만들기</title>
<link rel="stylesheet" href="./css/join.css">

<script type="text/javascript" src="./js/in.js">
</script>
</head>
<body>
	<div id = "center">
		<jsp:include page="../google/Header.jsp" />
		<div class = "nomal">
			<h1 class="slogIn">Google 계정 만들기</h3>
		</div>
		<div class="inputgo">
			<form action="./join" method='POST' id="formId">
				<div>
					<div class="grid">
						<span class="gridMid"> 성<input type="text" name = 'sName1' id="id">	</span>
						<span class="gridMid2"> 이름 <input type="text" name = 'sName2' id="id2"> </span>
						 
					
						<div class="warning">
							<p id="searchId"></p>
						</div>
					</div>
					
				</div>
		<%-- 		<% if(request.getAttribute("nameError1") != null){ %> --%>
		<%-- 					<%=(String)request.getAttribute("nameError1") %> --%>
				<div>
					사용자이름<input type="text" name = 'sEmail' id="emailId">
					<div class="warning">
						<p id="searchEamilId">
							<%
								String emailError = (String)request.getAttribute("emailOver");
								if(emailError != null){
							%>
								<%=emailError%>
							<%} %>
						</p>
					</div>
					문자,숫자,마침표를 사용할 수 있습니다.
				</div>		
				<div class="grid">
					<span class="gridMid"> 비밀번호<input type="password" name = 'sPwd' id="PwdId1"> </span>
					<span class="gridMid2"> 확인<input type="password" name = 'sPwd2' id="PwdId2"> </span>
					
					<div class="warning">
						<p id="searchPwdId"></p>
					</div>
					문자,숫자,기호를 조합하여 8자 이상을 사용하세요.
				</div>
				<input type="button" value="다음" onclick="nextFnc()">
			</form>
		</div>
	</div>
	
	
</body>
</html>