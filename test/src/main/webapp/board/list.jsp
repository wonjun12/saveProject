<%@page import="java.sql.ResultSet"%>
<%@ page import="nine.twotwo.boardList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>

<style type="text/css">
.mainDiv {
	width: 100vh;
	height: 50vh;
	margin-left: auto;
	margin-right: auto;
}

.theadCl {
	background-color: #a29671;
}

.writeCl {
	float: right;
	margin-top: 3px;
	margin-right: 10px;
}

.serachCl {
	float: left;
	margin-top: 3px;
}

#titleId {
	width: 720px;
}

.centerCl {
	width: 15%;
	text-align: center;
}

.noCl {
	width: 6%;
	text-align: center;
}

.viewCl {
	width: 70px;
	text-align: center;
}

#h2Id {
	margin-bottom: 0px;
}

#hrId {
	margin-top: 0px;
	margin-bottom: 30px;
}

#pageId {
	margin-top: 40px;
	text-align: center;
}

#selectId {
	width: 7vh;
	height: 2.5vh;
}

#scId {
	height: 2vh;
}
a:link {
	text-decoration: none;
	color: black;
}
a:visited {
	color: gray;
}
a:hover {
	text-decoration: underline;
	color: pink;
}
</style>

</head>
<body>
	<%
	ResultSet rs = null;
	/* if((ResultSet)request.getAttribute("notice") != null) { */
	rs = (ResultSet) request.getAttribute("notice");
	while (rs.next()) {
	%>
	<div class="mainDiv">
		<h2 id="h2Id">▍공지사항</h2>
		<hr id="hrId">
		<div>
			<h5 id="h2Id">↘아람보감에서 알려드려요. 꼭 읽어주세요 :-)</h5>
		</div>
		<div class="divCl">
			<table>
				<thead class="theadCl">
					<tr>
						<th class="noCl">번호</th>
						<th id="titleId">제목</th>
						<th class="centerCl">작성자</th>
						<th class="centerCl">작성일</th>
						<th class="viewCl">조회</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="noCl"><strong>공지</strong></td>
						<td><a class="aTagCl" href="./view?no=<%=rs.getInt("bno")%>"><%=rs.getString("btitle")%></a></td>
						<td class="centerCl"><%=rs.getString("sname")%></td>
						<td class="centerCl"><%=rs.getDate("BCREATEAT")%></td>
						<td class="viewCl"><%=rs.getInt("bviews")%></td>
					</tr>
					<%
					}
					%>
					<%
					rs = (ResultSet) request.getAttribute("RS");

					int maxList = (int) request.getAttribute("maxList");

					while (rs.next()) {
					%>
					<tr>
						<td class="noCl"><%=rs.getInt("bno")%></td>
						<td><a class="aTagCl" href="./view?no=<%=rs.getInt("bno")%>"><%=rs.getString("btitle")%></a></td>
						<td class="centerCl"><%=rs.getString("sname")%></td>
						<td class="centerCl"><%=rs.getDate("BCREATEAT")%></td>
						<td class="viewCl"><%=rs.getInt("bviews")%></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>

			<div>
				<form action="./search" method="get" class="serachCl">
					<select name="schck" id="selectId">
						<option value=0>제목</option>
						<option value=1>내용</option>
					</select> <input type="text" name="inputsc" id="scId"> <input
						type="submit" value="찾기">
				</form>

				<a class="writeCl" href="./write"> <input type="button"
					value="글쓰기">
				</a>
			</div>

			<div id="pageId">
				<!-- 페이지 -->
				<%
				String searchInput = (String) request.getAttribute("searchInput");
				int schck = (int) request.getAttribute("schck");
				for (int i = 0, j = 1; i < maxList; i++) {
					if (i % 5 == 0) {
				%>

				<a class="aTagCl"
					href="./search?listNum=<%=i%>&schck=<%=schck%>&inputsc=<%=searchInput%>"><%=j%></a>
				&nbsp;

				<%
				j++;
				}
				}
				%>
			</div>

		</div>
	</div>

</body>
</html>