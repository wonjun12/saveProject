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
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("./shIn.jsp");	//로그인 화면
		rd.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		Connection conn = null;			
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
//		MemberDto memberDto = memberDao.memberExist(email, pwd);
		
		//오라클 ID, PWD 입력
		String driver = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "SYSTEM_ADMIN";
		String pwd = "absolute0922";
		
		
		//로그인하면서 DB에 넘길 자료 입력
					// jsp(web)에서 입력한 값 들고오기
		
		
		//위에 것은 더 이상 것드릴 게 없음
		try {
			String semail = req.getParameter("sEmail");
			String spwd = req.getParameter("sPwd");
			
			HttpSession session = req.getSession(); //임시적으로 들어옴
			
			Class.forName("oracle.jdbc.driver.OracleDriver");		// 오라클 주소
			conn = DriverManager.getConnection(driver, user, pwd);	// DB 연결
			
			String sql ="";
			
			sql = "SELECT SNO, SEMAIL, SPWD" + 							//비밀번호는 보안상 select에서는 안 들고 오고
					" FROM SLAVE" + 								//where에서 들고온다.
					" WHERE SEMAIL =? AND SPWD = ?";
			
			pstmt = conn.prepareStatement(sql);						
			pstmt.setString(1, semail);								
			pstmt.setString(2, spwd);
			
			rs = pstmt.executeQuery();	
			
			// 회원이 존재한다면 세션에 담고
			while(rs.next()) {
				System.out.println(rs.getString("SPWD"));	// 현재 값을 아예 안 들고옴 WHY?
				System.out.println(spwd);
				if((rs.getString("SEMAIL").equals(semail)) && (rs.getString("SPWD").equals(spwd))) {
					session.setAttribute("SNO", rs.getInt("SNO"));
					session.setAttribute("email", semail);					// 이 때 고정적으로 들어옴
					session.setAttribute("spwd", spwd);
					
					resp.sendRedirect("/TrivialGOO/board/list");
					return;
				}
			}
			resp.sendRedirect("./shIn.jsp");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
