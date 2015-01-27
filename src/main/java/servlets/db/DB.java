package servlets.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

	public static ResultSet select(String query) {
		init();
		
		ResultSet rs = null;
		PreparedStatement st = null;
		
		try {
			st = con.prepareStatement(query);
			rs = st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
		
		return rs;
	}

	public static Integer update(String query) {
		init();
		
		PreparedStatement st = null;
		int updateResult = 0;
		
		try {
			st = con.prepareStatement(query);
			updateResult = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}
		
		return updateResult;
	}
}
