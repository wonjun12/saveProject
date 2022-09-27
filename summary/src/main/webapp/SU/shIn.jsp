<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SUIN FORM</title>
</head>
<body>

	<form action="./shJoin.jsp" >
		<input type="submit" value="계정 만들기">
	</form>
	
	<form action="./in" method='POST'>
		<input type="text" name="sEmail" placeholder="이메일 또는 휴대전화"
			onfocus="this.placeholder=''" onblur="this.placeholder='이메일 또는 휴대전화'">
		<input type="password" name="sPwd" placeholder="비밀번호 입력"
			onfocus="this.placeholder=''" onblur="this.placeholder='비밀번호 입력'">
		<input type="submit" value="로그인">
	</form>
	
	
</body>
</html>