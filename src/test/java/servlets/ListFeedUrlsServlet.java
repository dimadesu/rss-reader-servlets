package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListFeedUrlsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListFeedUrlsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: get user id in request
		String username = (String) request.getSession().getAttribute("USERNAME");
		if(request.getAttribute("id") != null) {
			ResultSet rs = DB.select("select url from feeds where id =" + request.getAttribute("id") + ";");
			try {
				while(rs.next()) {
					request.setAttribute("url", rs.getString(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("viewId", "ListFeedUrlsServlet");
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Common/Index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int updatedRows = -1;// No post
		String id = (String) request.getParameter("id");
		if(id != null && (id.equals("") || id.equals("null"))) {
			id = null;
		}
		String url = (String) request.getParameter("url");
		if(url != null && (url.equals("") || url.trim().equals("null"))) {
			url = null;
		}
		if(url != null){
			String query = null;
			String type = null;
			if(id == null) {
				type = "insert";
				query = "INSERT INTO FEEDS (ID, URL) VALUES (null, '" + url + "');";
			} else {
				type = "update";
				query = "UPDATE FEEDS SET URL = '" + url + "' WHERE ID = " + id + ";";
			}
			updatedRows = DB.update(query);
			if(updatedRows > 0) {
				String message = null;
				if(type == "insert") {
					message = "Successfully added new feed.";
				} else  {
					message = "Successfully updated.";
				}
				// Attribute is server-side only, when parameter are send through request
				request.setAttribute("message", message);
				// Get id of new row
				if(type.equals("insert")) {
					ResultSet rs = DB.select("SELECT TOP 1 Id FROM FEEDS ORDER BY Id DESC");
					try {
						while (rs.next()) {
							id = rs.getString(1);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		request.setAttribute("id", id);
		request.setAttribute("url", url);
		request.setAttribute("viewId", "ListFeedUrlsServlet");
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Common/Index.jsp" +
			(id != null ? "?id=" + id : ""));
		rd.forward(request, response);
	}

}
