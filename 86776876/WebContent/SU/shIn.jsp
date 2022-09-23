<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SUIN FORM</title>
</head>
<body>
	<form action="./shJoin.jsp">
		<input type="text" name="sEmail" placeholder="이메일 또는 휴대전화"
			onfocus="this.placeholder=''" onblur="this.placeholder='이메일 또는 휴대전화'">
		<br>
		<input type="password" name="sPwd" placeholder="비밀번호 입력"
			onfocus="this.placeholder=''" onblur="this.placeholder='비밀번호 입력'">
		<br>
		<a>이메일을 잊으셨나요?</a>
		<br>
		내 컴퓨터가 아닌가요? 게스트 모드를 사용하여 비공개로 로그인하세요. <a>자세히 알아보기</a>
		<br>
		<input type="submit" value="계정 만들기">
	</form>
	
	<form action='' method='POST'>
		<input type="submit" value="로그인">
	</form>
	
</body>
</html>