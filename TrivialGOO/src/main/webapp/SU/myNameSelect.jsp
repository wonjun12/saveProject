<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 찾기</title>
</head>
<body>
	<jsp:include page="../google/Header.jsp" />
	<h3>이름을 입력하세요.</h3>
	<p>Google 계정 이름 입력</p>
	<form>
		<input type="text" name="myNameSelect" placeholder="이름"
			onfocus="this.placeholder=''" onblur="this.placeholder='이름'">
		<input type="submit" value="다음">
	</form>
</body>
</html>