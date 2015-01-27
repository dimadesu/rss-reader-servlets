package servlets.authentication;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servlets.db.DB;

public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignUpServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("viewId", "/WEB-INF/jsp/NotAuthed/SignUp.jsp");
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Common/Index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String returnType = null;
		String returnMessage = null;
		ResultSet rs = null;
		String username = (String) request.getParameter("username");
		if(username != null && username.trim().equals("")) {
			username = null;
		}
		String password = (String) request.getParameter("password");
		if(password != null && password.trim().equals("")) {
			password = null;
		}
		if(username == null){
			returnType = "error";
			returnMessage = "Username cannot be empty.";
		} else if (password == null) {
			returnType = "error";
			returnMessage = "Password cannot be empty.";
		} else if(username != null && password != null){
			rs = DB.select("SELECT * FROM USERS WHERE USERNAME = '" + username + "';");
			Boolean isUsernameTaken = false;
			try {
				if (rs.next()) {
					isUsernameTaken = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				returnType = "error";
				returnMessage = "SQLException.";
			}
			if (!isUsernameTaken) {
				DB.update("INSERT INTO USERS (ID, USERNAME, PASSWORD) VALUES (null, '" + username + "', '" + password + "');");
				rs = DB.select("SELECT TOP 1 Id FROM USERS ORDER BY Id DESC");
				try {
					if (rs.next()) {
						returnType = "success";
						returnMessage = "Successfully signed up.";
						HttpSession session = request.getSession();
						session.setAttribute("USERNAME", username);
						session.setAttribute("USERID", rs.getString(1));
					}
				} catch (SQLException e) {
					e.printStackTrace();
					returnType = "error";
					returnMessage = "SQLException.";
				}
			} else {
				returnType = "error";
				returnMessage = "Username already taken.";
			}
		}
		
		request.setAttribute("username", username);
		request.setAttribute("returnType", returnType);
		request.setAttribute("returnMessage", returnMessage);
		request.setAttribute("viewId", "/WEB-INF/jsp/NotAuthed/SignUp.jsp");
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Common/Index.jsp");
		rd.forward(request, response);
	}

}
