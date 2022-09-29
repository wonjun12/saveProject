<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Google 계정만들기</title>
</head>
<body>
				
	<form action="./join" method='POST'>
		성<input type="text" name = 'sName1'> 이름 <input type="text" name = 'sName2'>
		<br>
		이메일<input type="text" name = 'sEmail'>
		<br>
		비밀번호 <input type="password" name = 'sPwd'>비밀번호 확인<input type="password" name = 'sPwd2'>
		<br>
		핸드폰번호 <input type="text" name = 'sPho'>
		<br>
		<input type="submit" value = '다음' >	
	</form>
</body>
</html>