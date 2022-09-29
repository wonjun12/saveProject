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
import javax.websocket.Session;

// 보안,java파일을 실행하기 위해 기존 경로를 프로젝트명/URL명으로 변경(매핑)
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
		
		//sql문
		sql = "SELECT SNAME, SNO, SEMAIL"
				+ " FROM SLAVE"
				+ " WHERE sNo = ?";
		
		HttpSession session = req.getSession();		
		if(session.getAttribute("SNO") == null) {
			res.sendRedirect("../SU/shInId.jsp");
		}
		try {
			
			
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(sqlUrl, id, pwd);
			
			pstmt = conn.prepareStatement(sql);
			
//			pstmt.setInt(1, Integer.parseInt(req.getParameter("inputTest"))); 세션값 대신 사용 (합치기전 사용)
			pstmt.setInt(1, (int)session.getAttribute("SNO"));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SNO = rs.getInt("SNO");
				req.setAttribute("SNO", SNO);
				req.setAttribute("name",rs.getString("SNAME"));
				req.setAttribute("email", rs.getString("SEMAIL"));
			}
			// 웹에서 전달한 정보를 write.jsp에 전달
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
		//한글로 인코딩 처리
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sqlUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "SYSTEM_ADMIN";
		String pwd = "absolute0922";
		String sql = "";
		
		String selectNotice = req.getParameter("selectNotice");
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(sqlUrl, id, pwd);
			//sql insert문 (게시글 작성)
			sql = "INSERT INTO BOARD"
					+ " VALUE(SNO, BNO, BTITLE, BCONTENTS, BNOTICE)"
					+ " VALUES(?, BOARD_BNO_SEQ.NEXTVAL, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, SNO);
			pstmt.setString(2, req.getParameter("inputTitle"));
			pstmt.setString(3, req.getParameter("inputContents").replace("\r\n", "<br>"));
			pstmt.setString(4, selectNotice);
			
			// sql 값 반환 
			pstmt.executeUpdate();
			
			//작업을 수행한 후 list페이지로 이동
			res.sendRedirect("./list");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
}
