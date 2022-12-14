<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="nine.twotwo.boardEdit"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정</title>
<link rel="stylesheet" href="./css/write.css">

<script type="text/javascript" src="./js/edit.js">
</script>

</head>
<body>



	<div id="kingDiv">
		<div id="writeDiv" class="secondCl">
			<h3 id="h3Id">내용수정</h3>
		</div>

		<div>
			<%
			ResultSet rs = (ResultSet) request.getAttribute("edit");
			while (rs.next()) {
			%>

			<form action="./edit" method="POST" id="fomrId">
				<table class="secondCl">
					<tr>
						<td class="centerCl">이름</td>
						<td><input class="nameCl" type="text"
							value="<%=rs.getString("sname")%>" disabled>
							<%if((int)session.getAttribute("SNO") == 1) {%>
							<select name="selectNotice">
								<option value=0> 선택</option>
								<option value=1> 공지</option>
							</select>
							<%} %>
							</td>
					</tr>
					<tr class="borderCl">
						<td class="centerCl">이메일</td>
						<td><input id="emailId" type="text"
							value="<%=rs.getString("semail")%>" disabled></td>
					</tr>
					<tr class="borderCl">
						<td class="centerCl">제목</td>
						<td><input id="titleId" class="titleCl" type="text"
							placeholder="제목" value="<%=rs.getString("btitle")%>"
							name="inputTitle" required></td>
					</tr>
					<tr class="borderCl">
						<td colspan="2"><textarea id="areaId" placeholder="내용"
								name="inputContents" required><%=rs.getString("bcontents").replace("<br>", "\r\n")%></textarea></td>
					</tr>
				</table>

				<div id="buttonId">
					<input type="button" value="수정" onclick="editFnc()">&nbsp; <a
						href="./view?no=<%=rs.getInt("BNO")%>"><input type="button"
						value="취소"></a>
				</div>
			</form>
		</div>
	</div>

	<%
	}
	%>




</body>
</html>