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

@WebServlet("/board/view")
public class boardView extends HttpServlet{
	//기능분리를 하기 전 재사용
private int BNO;	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "SYSTEM_ADMIN";
		String pwd = "absolute0922";
		String sql = "";

		BNO = Integer.parseInt(req.getParameter("no"));
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(sqlUrl, id, pwd);
			
			sql = "update board"
					+ " set bviews = bviews + 1"
					+ " where bno = ?";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, BNO);

			pstmt.executeUpdate();

			sql = "select b.bno, b.btitle, s.semail, b.bcontents, b.bcreateat, b.bviews, s.sname"
					+ " from board b join slave s on b.sno = s.sno"
					+ " where b.bno = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, BNO);
			
			rs = pstmt.executeQuery();
			
			req.setAttribute("detailview", rs);
			
			RequestDispatcher rd = 
					req.getRequestDispatcher("./view.jsp");
			
			rd.forward(req, res);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sqlUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "SYSTEM_ADMIN";
		String pwd = "absolute0922";
		String sql = "DELETE board"
				+ " WHERE bno = ?";
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(sqlUrl, id, pwd);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, BNO);
			
			pstmt.executeUpdate();
			
			res.sendRedirect("./list");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
