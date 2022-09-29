<%@page import="java.sql.ResultSet"%>
<%@ page import="nine.twotwo.boardList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>
<link rel="stylesheet" href="./css/list.css">

<script type="text/javascript" src="./js/list.js">
	
</script>


</head>
<body>
	<div class="mainDiv">
		<a href="./list" id="hearId">
			<h2 id="h2Id">▍공지사항</h2>
		</a>
		<hr id="hrId">
		<div id="logoutIdP">
			<h5 id="h2Id">↘아람보감에서 알려드려요. 꼭 읽어주세요 :-)</h5>
			 <div id="logoutId"><a href="../SU/shOut.jsp" ><input type="button" value="로그아웃" ></a></div>
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
					<!-- 공지만 보여준다 -->
					<%
					ResultSet rs = null;
					/* if((ResultSet)request.getAttribute("notice") != null) { */
					rs = (ResultSet) request.getAttribute("notice");
					while (rs.next()) {
					%>
					<tr>
						<td class="noCl">
							<strong>공지</strong>
						</td>
						<td>
							<strong><a class="notiseCl" href="./view?no=<%=rs.getInt("bno")%>">&nbsp;<%=rs.getString("btitle")%></a></strong>
						</td>
						<td class="centerCl"><%=rs.getString("sname")%></td>
						<td class="centerCl"><%=rs.getDate("BCREATEAT")%></td>
						<td class="viewCl"><%=rs.getInt("bviews")%></td>
					</tr>
					<%
					}
					%>
					<!-- 일반 게시글만 보여준다  -->
					<%
					rs = (ResultSet) request.getAttribute("RS");
					// 게시글 총 개수
					int maxList = (int) request.getAttribute("maxList");

					while (rs.next()) {
					%>
					<tr>
						<td class="noCl"><%=rs.getInt("bno")%></td>
						<td>
							<a class="aTagCl" href="./view?no=<%=rs.getInt("bno")%>">&nbsp;<%=rs.getString("btitle")%></a>
						</td>
						<td class="centerCl"><%=rs.getString("sname")%></td>
						<td class="centerCl"><%=rs.getDate("BCREATEAT")%></td>
						<td class="viewCl"><%=rs.getInt("bviews")%></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
			<!-- 셀렉트박스 옵션 value 값대로 찾기 기능-->
			<div>
				<form action="./search" method="get" class="serachCl" id="formId">
					<select name="schck" id="selectId">
						<option value=0>제목</option>
						<option value=1>내용</option>
						<option value=3>제목+내용</option>
						<option value=2>작성자</option>
					</select>
					<input type="text" name="inputsc" id="scId"> <input type="button" value="찾기" onclick="searchFnc()">
				</form>

				<a class="writeCl" href="./write">
					<input type="button" value="글쓰기">
				</a>
			</div>
			
			<div id="pageId">
				<!-- 페이징 게시글 5개마다 다음페이지 생성 -->
				<%
				String searchInput = (String) request.getAttribute("searchInput");
				int schck = (int) request.getAttribute("schck");
				for (int i = 0, j = 1; i < maxList; i++) {
					if (i % 5 == 0) {
				%>

				<a class="aTagCl" href="./search?listNum=<%=i%>&schck=<%=schck%>&inputsc=<%=searchInput%>"><%=j%></a>
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