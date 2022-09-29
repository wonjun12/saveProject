<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
  <%@ page session = "true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나는 게시판이얌</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	거의 다 왔어 친구 힘내!!<br>
	<% session = request.getSession();
		String str = (String) session.getAttribute("email");
	%>
	
	<%=str%>
	
	
	<form action="./out" method='get'>
		<input type="submit" value="로그아웃">
	</form>
	<a href="./newlog.jsp"><input type="button" value ="버튼"/></a>
</body>
</html>