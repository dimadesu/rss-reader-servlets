package servlets.feedurl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlets.db.DB;

public class DeleteFeedUrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteFeedUrlServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String) request.getParameter("id");;
		if(id != "null") {
			Integer rowsDeleted = DB.update("delete from feeduser where feedid =" + id + " and userid = " + request.getSession().getAttribute("USERID") + ";" +
				"delete from feeds where id =" + id + ";");
			if(rowsDeleted > 0) {
				request.setAttribute("returnType", "success");
			} else {
				request.setAttribute("returnType", "error");
			}
		}
		request.setAttribute("viewId", "/WEB-INF/jsp/Authed/ManageFeedUrls/DeleteFeedUrl.jsp");
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Common/Index.jsp");
		rd.forward(request, response);
	}

}
