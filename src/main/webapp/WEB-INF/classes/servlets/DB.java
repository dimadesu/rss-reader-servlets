package servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;

public class DB {
	private static Connection con;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DB() {
		super();
	}

	private static void init() {
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace(System.out);
		}
		try {
			con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/",
					"sa", "");
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
	}

	public static ResultSet exec(String query) {
		init();
		ResultSet rs = null;
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.clearParameters();
			rs = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
		return rs;
	}
}
