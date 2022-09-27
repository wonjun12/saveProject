package nine.twotwo;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(value="/SU/join")
public class suJoin extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("./shJoin.jsp");	//로그인 화면
		rd.forward(req, resp);
	
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//DB연결하기 위한 변수 설정
		Connection conn = null;			
		PreparedStatement pstmt = null;
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		try {
			//오라클 ID, PWD 입력
			String driver = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "SYSTEM_ADMIN";
			String pwd = "absolute0922";
			
			//회원 가입하면서 DB에 넘길 자료 입력
			String email = req.getParameter("sEmail");
			String smame = req.getParameter("sName1") +
					req.getParameter("sName2");
			String spwd = req.getParameter("sPwd");
//			String spwd2 = req.getParameter("sPwd2");		//유효성 검사 때 사용할 것!!
//			String spho = req.getParameter("sPho");
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String sql = "";

			sql = "INSERT INTO SLAVE" + 
					" VALUE(SNO, SNAME, SEMAIL, SPWD)" + 
					" VALUES(SLAVE_SNO_SEQ.NEXTVAL, ?, ?, ?)";
			
			conn = DriverManager.getConnection(driver, user, pwd);
			
			pstmt = conn.prepareStatement(sql);
			
			// web에서 입력한 data를 DB에 자료 전송
			pstmt.setString(1, smame);
			pstmt.setString(2, email);
			pstmt.setString(3, spwd);
//			pstmt.setString(4, spho);
//				rs= pstmt.executeQuery();		// select문에서만 실행 쿼리 받아온다
			
//				값을 넘겨주고실행x 경로이동 x
			pstmt.executeUpdate();				//	select문 외 전부에서 쓰임
			
			resp.sendRedirect("./shIn.jsp");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
