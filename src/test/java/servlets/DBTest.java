package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DBTest extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		ResultSet rs = DB.select("select * from users");
		try {
			while (rs.next()) {
				out.write("<br/>" + rs.getString(1));
				out.write(", " + rs.getString(2));
				out.write(", " + rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
