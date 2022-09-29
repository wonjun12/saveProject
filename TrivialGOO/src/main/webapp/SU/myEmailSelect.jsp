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
	<h3>이메일 찾기</h3>
	<p>전화번호 또는 복구 이메일을 입력하세요.</p>
	<form action=myNameSelect.jsp>
		<input type="text" name="sEmailSelect" placeholder="이름"
			onfocus="this.placeholder=''" onblur="this.placeholder='이름'">
		<input type="submit" value="다음">
	</form>
</body>
</html>