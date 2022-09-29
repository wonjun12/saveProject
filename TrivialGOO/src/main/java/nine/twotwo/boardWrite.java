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


@WebServlet("/board/write")
public class boardWrite extends HttpServlet{
	private int SNO;
	private String SEMAIL;
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
		
		//작성자 이름을 조회하는 sql문
		sql = "SELECT SNAME, SNO, SEMAIL"
				+ " FROM SLAVE"
				+ " WHERE sNo = ?";
		
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(sqlUrl, id, pwd);
			
			pstmt = conn.prepareStatement(sql);
			
//			pstmt.setInt(1, Integer.parseInt(req.getParameter("inputTest")));
			pstmt.setInt(1, 4);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SNO = rs.getInt("SNO");
				req.setAttribute("name",rs.getString("SNAME"));
				req.setAttribute("email", rs.getString("SEMAIL"));
			}
			
			RequestDispatcher rd =
					req.getRequestDispatcher("./write.jsp");
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
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sqlUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "SYSTEM_ADMIN";
		String pwd = "absolute0922";
		String sql = "";
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(sqlUrl, id, pwd);
			sql = "INSERT INTO BOARD"
					+ " VALUE(SNO, BNO, BTITLE, BCONTENTS)"
					+ " VALUES(?, BOARD_BNO_SEQ.NEXTVAL, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 4);
			pstmt.setString(2, req.getParameter("inputTitle"));
			pstmt.setString(3, req.getParameter("inputContents").replace("\r\n", "<br>"));
			
			pstmt.executeUpdate();
			
			res.sendRedirect("./list");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
}
