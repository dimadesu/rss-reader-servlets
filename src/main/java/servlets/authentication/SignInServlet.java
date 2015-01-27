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

public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignInServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("viewId", "/WEB-INF/jsp/NotAuthed/SignIn.jsp");
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Common/Index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String returnType = null;
		String returnMessage = null;
		ResultSet rs = null;
		Integer resultId = null;
		String username = (String) request.getParameter("username");
		if(username != null && username.trim().equals("")) {
			username = null;
		}
		String password = (String) request.getParameter("password");
		if(password != null && password.trim().equals("")) {
			password = null;
		}
		String resultPassword = null; 
		if(username != null && password != null){
			rs = DB.select("SELECT * FROM USERS WHERE USERNAME = '" + username + "';");
			try {
				if (rs.next()) {
					returnType = "success";
					resultId = rs.getInt(1);
					resultPassword = rs.getString(3);
				} else {
					returnType = "error";
					returnMessage = "Username not found.";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(returnType != null && returnType.equals("success") && password != null && resultPassword != null && password.equals(resultPassword)) {
				HttpSession session = request.getSession();
				session.setAttribute("USERNAME", username);
				session.setAttribute("USERID", resultId);
				returnMessage = "Successfully signed in.";
			} else if (returnType.equals("success")) {
				returnType = "error";
				returnMessage = "Password mismatch.";
			}
		} else if (username == null) {
			returnType = "error";
			returnMessage = "Please fill in user.";
		} else if (password == null) {
			returnType = "error";
			returnMessage = "Please fill in password.";
		}
		
		request.setAttribute("username", username);
		request.setAttribute("returnType", returnType);
		request.setAttribute("returnMessage", returnMessage);
		request.setAttribute("viewId", "/WEB-INF/jsp/NotAuthed/SignIn.jsp");
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Common/Index.jsp");
		rd.forward(request, response);
	}

}
