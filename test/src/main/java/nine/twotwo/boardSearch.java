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

@WebServlet("/board/search")
public class boardSearch extends HttpServlet {

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
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		
		String search = "%" + req.getParameter("inputsc") + "%";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(sqlUrl, id, pwd);
			
			sql = "select b.bno, b.btitle, b.bcontents, b.bcreateat, b.bviews, s.sname"
					+ " from board b join slave s on b.sno = s.sno"
					+ " where b.bnotice = 1";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			req.setAttribute("notice", rs);
			
					
			
//			sql = "select b.bno, b.btitle, b.bcontents, b.bcreateat, b.bviews, s.sname"
//					+ " from board b join slave s on b.sno = s.sno"
//					+ " where b.bcontents like ? "
//					+ " ORDER BY b.bno ASC";
			
			sql = "select boardList2.*"
					+ " from (select rownum nums, boardList1.*"
					+ " from(select b.bno, b.btitle, b.bcontents, b.bcreateat, b.bviews, s.sname"
					+ " from board b join slave s on b.sno = s.sno";
			
			int schck = Integer.parseInt(req.getParameter("schck"));
			
			switch (schck) {
				case 0: 
					sql += " where b.btitle like ? ";
					break;
				case 1: 
					sql += " where b.bcontents like ? ";
					break;
			}
			sql += " ORDER BY b.bno ASC) boardList1) boardList2"
					+ " where boardList2.nums > ? AND boardList2.nums <= ?";
			
			
			
			pstmt = conn.prepareStatement(sql);

			int listNum = 0;
			
			if(req.getParameter("listNum") != null) {
				listNum = Integer.parseInt(req.getParameter("listNum"));
			}
			
			pstmt.setString(1, search);
			pstmt.setInt(2, listNum);
			pstmt.setInt(3, listNum + 5);
			
			rs = pstmt.executeQuery();

			req.setAttribute("RS", rs);
			
			
			sql = "select max(rownum) maxNum"
					+ " from board b join slave s on b.sno = s.sno";
			switch (schck) {
			case 0:
				sql += " where b.btitle like ?";
				break;

			case 1:
				sql += " where b.bcontents like ?";
				break;
			}		
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, search);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				req.setAttribute("maxList", rs.getInt("maxNum"));
			}
			
			
			
			req.setAttribute("searchInput", req.getParameter("inputsc"));
			req.setAttribute("schck", schck);
			
			RequestDispatcher rd =
					req.getRequestDispatcher("./list.jsp");

			rd.forward(req, res);
			
		} catch (Exception e) {
			
			// TODO: handle exception
			e.printStackTrace();
			
		}
	}

}
