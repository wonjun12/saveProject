<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SUIN FORM</title>
<link rel="stylesheet" href="./css/in.css">

<script type="text/javascript"></script>

</head>
<body>
	<div id = "center">
		<jsp:include page="../google/Header.jsp" />
		
		<div class="nomal">
			<h1 class="slogIn">로그인</h1>
			<h4 class="slgIn2">Google 계정 사용</h4>
		</div>
		<div class="inputgo">
			<form action="./in" method='POST'>
				
				<input type="text" name="sEmail" placeholder="이메일 또는 휴대전화"
				onfocus="this.placeholder=''"
				onblur="this.placeholder='이메일 또는 휴대전화'">
			
				
<!-- 		에러 시 가져올 것 -->
			<div class="warning">	
				<%if (request.getAttribute("IdError") != null) {%>
				<%=(String) request.getAttribute("IdError")%>
				<%}	%>
			</div>
			<div>
				<a href="./myEmailSelect.jsp">이메일을 잊으셨나요?</a>
			</div>
			<p>내 컴퓨터가 아닌가요? 게스트 모드를 사용하여 비공개로 로그인하세요. 자세히 알아보기</p>
			<a href="./shJoin.jsp"> <input type="button" value="계정 만들기" /></a>
				<div id="nextSubmitId">
				<input type="submit" value="다음" >
				</div>
			</form>
				
		</div>
		

	</div>
</body>
</html>