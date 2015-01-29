package servlets.feed;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlets.db.DB;

public class ArticleReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ArticleReadServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String articleId = (String) request.getParameter("articleId");
		Integer userId = (Integer) request.getSession().getAttribute("USERID");
		Boolean isRead = null;
		Integer feedId = null;
		Integer articleStateId = null;
		Boolean isStateInDb = null;
		
		// Find feedId
		ResultSet rs2 = DB.select("select feedid from articles where id = " + articleId + ";");
		try {
			if(rs2.next()) {
				feedId = rs2.getInt(1);

				ResultSet rs = DB.select("select isread, id from article_state where feedid = " + feedId + " and articleid = " + articleId + " and userid = " + userId + ";");
				if(rs.next()) {
					isStateInDb = true;
					isRead = rs.getBoolean(1);
					articleStateId = rs.getInt(2);
				} else {
					isStateInDb = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int rowsUpdated = 0;
		
		if(isStateInDb == false) {
			isRead = true;
			rowsUpdated = DB.update("insert into article_state (isread, feedid, articleid, userid) values (" + isRead +
				", " + feedId +
				", " + articleId +
				", " + userId +
				");");
		} else {
			isRead = !isRead;
			rowsUpdated = DB.update("UPDATE article_state SET ISREAD =" + isRead +
				" WHERE ID = " + articleStateId + ";");			
		}
		if (rowsUpdated > 0) {
			request.setAttribute("returnType", "success");
		} else {
			request.setAttribute("returnType", "warning");
		}
		request.setAttribute("isRead", isRead);
		
		request.setAttribute("viewId", "/WEB-INF/jsp/Authed/ArticleRead.jsp");
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Common/Index.jsp");
		rd.forward(request, response);
	}

}
