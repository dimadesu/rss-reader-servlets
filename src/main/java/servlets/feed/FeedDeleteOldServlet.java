package servlets.feed;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlets.db.DB;
import servlets.xml.Collection;
import servlets.xml.Item;
import servlets.xml.RSSFeedParser;

public class FeedDeleteOldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FeedDeleteOldServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String feedUrl = null;
		String feedId = (String) request.getParameter("id");
		Integer userId = Integer.parseInt(request.getSession().getAttribute("USERID").toString());
		
		// Returns: url
		String query = "SELECT FEEDS.URL " +
			"FROM FEEDUSER JOIN FEEDS ON FEEDUSER.FEEDID = FEEDS.ID " +
			"WHERE FEEDUSER.USERID = " + userId + "AND FEEDUSER.FEEDID = " + feedId + ";";
		ResultSet rs = DB.select(query);
		try {
			if(rs.next()) {
				feedUrl = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RSSFeedParser parser = new RSSFeedParser(feedUrl);
		Collection feed = parser.readFeed();
		List<Item> articles = feed.getItems();
		int articlesDeleted = 0;
		
		String currentGuids = "";
		
		for(int i = 0; i < articles.size(); i++) {
			Item article = articles.get(i);
			currentGuids += ("'" + article.getGuid() + "'");
			// Except last element
			if((i + 1) != articles.size()) {
				currentGuids += ","; 
			}
		}
		
		if(currentGuids != ""){
			
			// Collect article ids to delete from article_state
			String articleIds = "";
			ResultSet rs2 = DB.select("select id FROM articles WHERE " +
					"feedid = " + feedId +
					" and guid NOT IN (" + currentGuids +");");
			try {
				while(rs2.next()) {
					articleIds += rs2.getString(1);
					if(!rs2.isLast()) {
						articleIds += ",";
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			articlesDeleted = DB.update("DELETE FROM articles WHERE guid NOT IN (" + currentGuids +");");
			
			if(articleIds != ""){
				DB.update("DELETE FROM article_state WHERE userid = " + userId +
						" and feedid = " + feedId +
						" and articleid NOT IN " + articleIds + ");");
			}
			
		}
		
		if (articlesDeleted > 0) {
			request.setAttribute("returnType", "success");
		} else {
			request.setAttribute("returnType", "warning");
		}
		request.setAttribute("articlesDeleted", articlesDeleted);
		
		request.setAttribute("viewId", "/WEB-INF/jsp/Authed/DeleteOld.jsp");
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Common/Index.jsp");
		rd.forward(request, response);
	}

}
