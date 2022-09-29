<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Google 계정만들기</title>
</head>
<body>
	<h3>Google</h3>
	<h3>전화번호 인증</h3>	
	<p>Google에서는 보안을 위해 본인 확인을 진행합니다. Google에서 6자리 인증 코드가 포함된 문자 메시지를 전송합니다</p>
	<form action="./join" method='POST'>
		핸드폰번호 <input type="text" name = 'sPhone'><br>
		표준 요금이 부과됩니다.<br>
		<a href="./shJoin.jsp">뒤로</a>		<input type="submit" value = '다음' >	
	</form>
	