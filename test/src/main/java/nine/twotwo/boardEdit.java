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

@WebServlet("/board/edit")
public class boardEdit extends HttpServlet{
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
		String sql = "select bno, btitle, bcontents"
				+ " from board"
				+ " where bno = ?";
		BNO = Integer.parseInt(req.getParameter("bno"));
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(sqlUrl, id, pwd);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(req.getParameter("bno")));
			
			rs = pstmt.executeQuery();
			
			req.setAttribute("edit", rs);
			
			RequestDispatcher rd = 
					req.getRequestDispatcher("./edit.jsp");
			
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
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		
		
		String title = req.getParameter("inputTitle");
		String contents = req.getParameter("inputContents");
		
		
		String sql = "update board"
				+ " set btitle = ?, bcontents = ?"
				+ " where bno = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(sqlUrl, id, pwd);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			pstmt.setInt(3, BNO);
			
			
			pstmt.executeUpdate();
			
			res.sendRedirect("./list");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}