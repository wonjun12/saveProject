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
		//임시보내기 //sql 사용자,게시판 테이블 조회 (수정 할 떄 게시글 정보)
		String sql = "select s.sno, b.bno, b.btitle, b.bcontents, s.sname, s.semail"
				+ " from board b join slave s on b.sno = s.sno"
				+ " where b.bno = ?";
		BNO = Integer.parseInt(req.getParameter("bno"));
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(sqlUrl, id, pwd);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, BNO);
			
			rs = pstmt.executeQuery();
			
			req.setAttribute("edit", rs);
			
			// 웹에서 전달한 정보를 edit.jsp에 전달
			RequestDispatcher rd = 
					req.getRequestDispatcher("./edit.jsp");
			
			rd.forward(req, res);
			
		} catch (Exception e) {
			//예외처리
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
		String notice = req.getParameter("selectNotice");
		// sql update문 (게시글 수정)
		String sql = "update board"
				+ " set btitle = ?, bcontents = ?, bnotice = ?"
				+ " where bno = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(sqlUrl, id, pwd);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			pstmt.setString(3, notice);
			pstmt.setInt(4, BNO);
			
			
			pstmt.executeUpdate();
			//작업을 수행한 후 list페이지로 이동
			res.sendRedirect("./list");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}