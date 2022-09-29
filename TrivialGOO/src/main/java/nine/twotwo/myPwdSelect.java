package nine.twotwo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class myPwdSelect extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		Connection conn = null;			
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
//		MemberDto memberDto = memberDao.memberExist(email, pwd);
		
		//오라클 ID, PWD 입력
		String driver = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "SYSTEM_ADMIN";
		String pwd = "absolute0922";
		
		try {
			String semail = req.getParameter("sEmailSelect");
			String sname = req.getParameter("myNameSelect");
			
			HttpSession session = req.getSession(); //임시적으로 들어옴
			
			Class.forName("oracle.jdbc.driver.OracleDriver");		// 오라클 주소
			conn = DriverManager.getConnection(driver, user, pwd);	// DB 연결
			
			String sql ="";
			
			sql = "SELECT SNO, SEMAIL, SNAME" +
					" FROM SLAVE" +
					" WHERE SEMAIL =? NAME=?";
			pstmt = conn.prepareStatement(sql);						
			pstmt.setString(1, semail);
			pstmt.setString(2, sname);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("SEMAIL").equals(semail)) {
					if(rs.getString("SNAME").equals(sname)) {
						System.out.println("");
					}
					else {
						System.out.println("계정 이름이 다릅니다.");
						resp.sendRedirect("./selectFile.jsp");
					}
				}
				else {
					System.out.println("계정 이메일이 다릅니다.");
					resp.sendRedirect("./selectFile.jsp");
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
