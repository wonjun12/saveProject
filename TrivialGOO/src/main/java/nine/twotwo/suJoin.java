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


@WebServlet(value="/SU/join")
public class suJoin extends HttpServlet {
	private String saveName;
	private String saveEmail;
	private String savePwd;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		//DB연결하기 위한 변수 설정
		Connection conn = null;			
		PreparedStatement pstmt = null;
		//오라클 ID, PWD 입력
		String driver = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "SYSTEM_ADMIN";
		String pwd = "absolute0922";
		ResultSet rs = null;
		
		//회원 가입하면서 DB에 넘길 자료 입력
		String sphone = "";
		String sql = "";
		
//		String email = req.getParameter("sEmail");
//		String smame = req.getParameter("sName1") +
//				req.getParameter("sName2");
//		String spwd = req.getParameter("sPwd");
//		String spho = req.getParameter("sPho");
//		String sql = "";
		
		try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(driver, user, pwd);
				
//	-----------------------sPhone 넣기
				if(req.getParameter("sPhone") != null) {
					sphone  = req.getParameter("sPhone");
					
					sql = "INSERT INTO SLAVE"
							+ " VALUE(SNO, SNAME, SEMAIL, SPWD, SPHONE)"
							+ " VALUES(SLAVE_SNO_SEQ.NEXTVAL, ?, ?, ?, ?)";	
					//DB로 Web에서 입력한 자료 보내기
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, saveName);
					pstmt.setString(2, saveEmail);
					pstmt.setString(3, savePwd);
					pstmt.setString(4, sphone);
					
					saveName = "";
					saveEmail = "";
					savePwd = "";
					
					pstmt.executeUpdate();
					resp.sendRedirect("./shInId.jsp");
//	-----------------------sPhone외 전부 넣기
				} else {
					saveEmail = req.getParameter("sEmail");
					
					saveName = req.getParameter("sName1")
							+ req.getParameter("sName2");		//DB에 저장할 것
					
					savePwd = req.getParameter("sPwd");
//---------------------------------email 중복 확인 구문					
					sql = "SELECT SEMAIL" + 
							" FROM SLAVE" + 
							" WHERE SEMAIL =?";
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, saveEmail);
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						req.setAttribute("emailOver", "사용중인 email입니다.");
						RequestDispatcher rd = req.getRequestDispatcher("./shJoin.jsp");
						rd.forward(req, resp);
						return;
					}
					
					resp.sendRedirect("./shJoinPhone.jsp");
				}
				
				// web에서 입력한 data를 DB에 자료 전송
//				pstmt.setString(1, smame);
//				pstmt.setString(2, email);
//				pstmt.setString(3, spwd);
//				pstmt.setString(4, spho);
//					rs= pstmt.executeQuery();		// select문에서만 실행 쿼리 받아온다
				
//					값을 넘겨주고실행x 경로이동 x
//				pstmt.executeUpdate();				//	select문 외 전부에서 쓰임
				
				
//				-----------------------sPhone 넣기

			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
