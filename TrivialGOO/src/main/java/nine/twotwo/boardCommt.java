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

@WebServlet("/board/commt")
public class boardCommt extends HttpServlet{
	static public int BNO;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");

		String sqlUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "SYSTEM_ADMIN";
		String pwd = "absolute0922";
		
		String sql = "";
		//sql insert문 (댓글)
		sql += "INSERT INTO comments"
				+ " (CNO, BNO, SNO, COMMENTS)"
				+ " VALUES(comments_CNo_SEQ.NEXTVAL, ? , ? , ? )";
		
		HttpSession session = req.getSession();
		
		// 로그인 상태가 아니라면 로그인페이지로 이동
		if(session.getAttribute("SNO") == null) {
			res.sendRedirect("../SU/shInId.jsp");
		}
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(sqlUrl, id, pwd);
			
			
			String commtStr = req.getParameter("inputCommt");
			int boardBno = BNO;
			int slaveSno = (int) session.getAttribute("SNO");

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardBno);
			pstmt.setInt(2, slaveSno);
			pstmt.setString(3, commtStr);
			
			pstmt.executeUpdate();
			
			res.sendRedirect("./view?no=" + boardBno);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
