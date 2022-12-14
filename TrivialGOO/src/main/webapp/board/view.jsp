<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="nine.twotwo.boardView"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세페이지</title>
<link rel="stylesheet" href="./css/write.css">

<script type="text/javascript" src="./js/view.js">
	
</script>

</head>
<body>


	<div id="kingDiv">
		<div id="writeDiv" class="secondCl">
			<h3 id="h3Id">▢상세 글정보▢</h3>
		</div>

		<div>

			<%
			ResultSet rs = (ResultSet) request.getAttribute("detailview");
			while (rs.next()) {
			%>

			<table class="secondCl">
				<tr>
					<td class="centerCl">게시글 번호</td>
					<td><%=rs.getInt("bno")%></td>
				</tr>
				<tr class="borderCl">
					<td class="centerCl">이름</td>
					<td id="nameId" class="nameCl"><%=rs.getString("sname")%></td>
				</tr>
				<tr class="borderCl">
					<td class="centerCl">이메일</td>
					<td id="emailId"><%=rs.getString("semail")%></td>
				</tr>
				<tr class="borderCl">
					<td class="centerCl">제목</td>
					<td class="titleCl" id="titleId"><%=rs.getString("btitle")%></td>
				</tr>
				<tr class="borderCl">
					<!-- 글 내용 (줄바꿈 표시 \r\n\으로 변경해서 웹에서 <br>로 안보이게 설정)-->
					<td colspan="2"><%=rs.getString("bcontents").replace("<br>", "\r\n")%></td>
				</tr>
			</table>
			<!-- 게시글 *댓글 기능*   댓글이 있다면 표시 없다면 표시 X -->
			<div id="comtitleId">
				<p id="ptagId">▢댓글▢</p>
			</div>
			<div id="commentId">
				<%
				if (request.getAttribute("commtView") != null) {
					ResultSet comRs = (ResultSet) request.getAttribute("commtView");
					while (comRs.next()) {
				%>
				<table>
					<tr id="firstTrId">
						<td><img alt="프로필사진"
							src="https://ssl.pstatic.net/static/cafe/cafe_pc/default/cafe_profile_77.png?type=c77_77"
							width="22" height="20"> <%=comRs.getString("SNAME")%></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=comRs.getString("COMMENTS")%></td> <br>
						<%=comRs.getString("CCREATEAT") %>
					</tr>
				</table>
				<%
				}
				}
				%>
			</div>
			<form action="./commt" method="POST" id="formId2">

				<input type="text" name="inputCommt" id="commentWId"
					placeholder="댓글을 남겨 보세요">
				<div id="tlqkfId">
					<input type="button" value="작성" onclick="vwFnc()" id="gktlId">
				</div>

			</form>
		</div>

		<form action="./view" method="POST" id="fomrId">
			<div id="buttonId">
				<!-- 다른 사용자의 게시글을 수정하거나 삭제할 수 없게 만든 기능 (관리자는 가능) -->
				<%
				int SNO = (int) session.getAttribute("SNO");
				if (rs.getInt("SNO") == SNO) {
				%>
				<a href="./edit?bno=<%=rs.getInt("bno")%>"><input type="button"
					value="수정"></a> &nbsp;
				<%
				}
				%>
				<%
				if ((int) session.getAttribute("SNO") == 1 || rs.getInt("SNO") == (int) session.getAttribute("SNO")) {
				%>
				<input type="button" value="삭제" id="viewinputId"
					onclick="deleteFnc()">&nbsp;&nbsp;

				<%
				}
				%>

				<a href="./list"> <input type="button" value="목록">
				</a>
			</div>
		</form>
		<%
		}
		%>
	</div>
</body>
</html>