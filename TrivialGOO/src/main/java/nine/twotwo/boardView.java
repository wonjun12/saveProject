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
import javax.servlet.http.HttpSession;

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
		HttpSession session = req.getSession();
		
		if(session.getAttribute("SNO") == null) {
			res.sendRedirect("../SU/shInId.jsp");
		}
		
		BNO = Integer.parseInt(req.getParameter("no"));
		boardCommt.BNO = BNO;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(sqlUrl, id, pwd);
			// sql 수정문
			sql = "update board"
					+ " set bviews = bviews + 1"
					+ " where bno = ?";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, BNO);

			pstmt.executeUpdate();
			// sql 사용자,게시판 테이블 조회 (사용자 게시글 조회)
			sql = "select b.bno, b.btitle, s.semail, b.bcontents, b.bcreateat, b.bviews, s.sname, s.sno"
					+ " from board b join slave s on b.sno = s.sno"
					+ " where b.bno = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, BNO);
			
			rs = pstmt.executeQuery();

			req.setAttribute("detailview", rs);
			
			// sql 사용자,댓글 테이블 조회 (댓글조회 및 정렬)
			sql = "select *"
					+ " from (select rownum rownums, commBo.bno, commBo.comments, commBo.cCreateAt, s.sname ,s.sno"
					+ " from (select c.bno, c.comments, c.cCreateAt, c.sno"
					+ " from comments c join board b on b.bno = c.bno)commBo join slave s on commBo.sno = s.sno) commBo2"
					+ " where commBo2.bno = ?"
					+ " order by commBo2.rownums ASC";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, BNO);
			
			rs = pstmt.executeQuery();
			
			req.setAttribute("commtView", rs);
			
			// 웹에서 전달한 정보를 view.jsp에 전달
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
		String sql = "";
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(sqlUrl, id, pwd);
			sql = "DELETE comments"
					+ " where bno = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, BNO);
			
			pstmt.executeUpdate();
			//sql delete문 (게시글 삭제)
			sql = "DELETE board"
					+ " WHERE bno = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, BNO);
			
			pstmt.executeUpdate();
			//작업을 수행한 후 list페이지로 이동
			res.sendRedirect("./list");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
