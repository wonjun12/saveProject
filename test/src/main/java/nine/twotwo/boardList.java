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

@WebServlet("/board/list")
public class boardList extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement pstmt = null;
				

		String sql = "";
		
		sql = "SELECT BNO, SNO, BTITLE, BCONTENTS, BCREATEAT, BVIEWS"
				+ " FROM board"
				+ " ORDER BY BNO ASC";
		
		String sqlUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "SYSTEM_ADMIN";
		String pwd = "absolute0922";
		
		System.out.println("DB 연동 쌉가능?");
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(sqlUrl, id, pwd);
			
			RequestDispatcher rd =
					req.getRequestDispatcher("./list.jsp");
					
			rd.forward(req, res);
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}