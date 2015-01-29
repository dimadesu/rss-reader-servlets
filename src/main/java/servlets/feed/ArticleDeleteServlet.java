package servlets.feed;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlets.db.DB;

public class ArticleDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ArticleDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String articleId = (String) request.getParameter("articleId");
		Integer userId = (Integer) request.getSession().getAttribute("USERID");
		Integer rowsUpdated = 0;
		
		if (articleId != null) {
			rowsUpdated = DB.update("delete from articles where id = " + articleId + ";");
			DB.update("delete from article_state where articleid = " + articleId + " and userid = " + userId + ";");
		}
		
		if (rowsUpdated > 0) {
			request.setAttribute("returnType", "success");
		} else {
			request.setAttribute("returnType", "warning");
		}
		
		request.setAttribute("viewId", "/WEB-INF/jsp/Authed/ArticleDelete.jsp");
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Common/Index.jsp");
		rd.forward(request, response);
	}

}
