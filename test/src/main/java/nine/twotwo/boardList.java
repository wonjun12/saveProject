package nine.twotwo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/board/list")
public class boardList extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		
		
		String sqlUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "SYSTEM_ADMIN";
		String pwd = "absolute0922";
		
		

		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(sqlUrl, id, pwd);
			
			sql = "select b.bno, b.btitle, b.bcontents, b.bcreateat, b.bviews, s.sname"
					+ " from board b join slave s on b.sno = s.sno"
					+ " where b.bnotice = 1";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			req.setAttribute("notice", rs);
			
//			sql = "select rownum, b.bno, b.btitle, b.bcreateat, b.bviews, s.sname"
//					+ " from board b join slave s on b.sno = s.sno"
//					+ " order by b.bno ASC";
			
//			sql = "select rownum, boardList.*"
//					+ " from (select b.bno, b.btitle, b.bcreateat, b.bviews, s.sname"
//					+ " from board b join slave s on b.sno = s.sno"
//					+ " order by b.bno ASC) boardList";
			
			sql = "select boardList2.*"
					+ " from(select rownum nums, boardList1.*"
					+ " from (select b.bno, b.btitle, b.bcreateat, b.bviews, s.sname"
					+ " from board b join slave s on b.sno = s.sno"
					+ " order by b.bno ASC) boardList1) boardList2"
					+ " where boardList2.nums > ? AND boardList2.nums <= ?";
			
			int listNum = 0;
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, 5);

			rs = pstmt.executeQuery();
			
			req.setAttribute("RS", rs);
			
			sql = "select max(rownum) maxNum"
					+ " from board b join slave s on b.sno = s.sno";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				req.setAttribute("maxList", rs.getInt("maxNum"));
			}
			
			req.setAttribute("searchInput", "");

			RequestDispatcher rd =
					req.getRequestDispatcher("./list.jsp");
					
			rd.forward(req, res);
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
	
	}
}