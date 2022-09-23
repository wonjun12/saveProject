package NINE.TWOTWO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/WebContent/SU")
public class suIn extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("WebContent/SU/shIn.jsp");
		rd.forward(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Connection conn = null;			
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String url = "jdbc:oracle:thin@localhost:1521:xe";
		String user = "SYSTEM_ADMIN";
		String pwd = "absolute0922";
		
		String email = req.getParameter("sEmail");
		String spwd = req.getParameter("sPwd");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, pwd);
			
			String sql = "SELECT SNO, SNAME, SNAME, SEMAIL, SPWD" + 
					" FROM slave" + 
					" ORDER BY SNO ASC";
//			pstmt = connection.pre
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
