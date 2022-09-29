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

@WebServlet(value="/SU/in")
public class suIn extends HttpServlet{
	private String saveEmail;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		Connection conn = null;			
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		//오라클 ID, PWD 입력
		String driver = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "SYSTEM_ADMIN";
		String pwd = "absolute0922";
		
		//로그인하면서 DB에 넘길 자료 입력
		String semail = "";
		String spwd = "";
		
		
		String sql ="";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");		// 오라클 주소
			conn = DriverManager.getConnection(driver, user, pwd);
			
//			-----------------------sEmail
			if(req.getParameter("sEmail") != null) {				// SuInId.jsp의 sEmail 값을 가져오기
				semail = req.getParameter("sEmail");
				sql = "SELECT SNO, SEMAIL" + 
						" FROM SLAVE" + 
						" WHERE SEMAIL =?";
				pstmt = conn.prepareStatement(sql);				
				pstmt.setString(1, semail);

				rs = pstmt.executeQuery();				
				while(rs.next()) {								//id 값이 있을 경우
					saveEmail = rs.getString("SEMAIL");			//SuInId.jsp의 sEmail 값을 PWD확인하면서도 쓸 거니까 전역변수로 옮기기
					req.setAttribute("MyEmail", saveEmail);	
					RequestDispatcher rd = req.getRequestDispatcher("./shInPwd.jsp");
					rd.forward(req, resp);
//					resp.sendRedirect("./shInPwd.jsp");
					return;
				}
				//아이디가 틀릴 경우
				req.setAttribute("IdError", "Google 계정을 찾을 수 없습니다.");
				RequestDispatcher rd = req.getRequestDispatcher("./shInId.jsp");
//				resp.sendRedirect("./shInId.jsp");
				rd.forward(req, resp);
				
//	-----------------------sPwd
			}else if(req.getParameter("sPwd") != null) {		// SuInPwd.jsp의 sPwd 값을 가져오기
				spwd = req.getParameter("sPwd");
				sql = "SELECT SNO, SEMAIL, SNAME" + 							
						" FROM SLAVE" + 							
						" WHERE SEMAIL = ? AND SPWD = ? ";
				
				pstmt = conn.prepareStatement(sql);						
				pstmt.setString(1, saveEmail);					// 전역변수 값을 DB로 보내기
				pstmt.setString(2, spwd);
				
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					HttpSession session = req.getSession();
					session.setAttribute("SNO", rs.getInt("SNO"));
					session.setAttribute("email", saveEmail);	// 전역변수 값을 보내기
					session.setAttribute("name", rs.getString("SNAME"));
					resp.sendRedirect("../board/list");			// 로그인 성공 
					return;										// 로그인 실패 시 while 종료
				}
				
				req.setAttribute("MyEmail", saveEmail);	
				req.setAttribute("pwdError", "비밀번호가 틀렸습니다.");
				RequestDispatcher rd = req.getRequestDispatcher("./shInPwd.jsp");
				
				rd.forward(req, resp);
//				resp.sendRedirect("./shInPwd.jsp");
			}
			
//			resp.sendRedirect("-1");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
