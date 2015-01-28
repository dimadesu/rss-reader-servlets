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

public class FeedUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FeedUpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String feedUrl = null;
		String feedId = (String) request.getParameter("id");
		Integer userId = (Integer) request.getSession().getAttribute("USERID");
		// Returns: url
		String query = "SELECT FEEDS.URL " +
			"FROM FEEDUSER JOIN FEEDS ON FEEDUSER.FEEDID = FEEDS.ID " +
			"WHERE FEEDUSER.USERID = " + userId.toString() + "AND FEEDUSER.FEEDID = " + feedId + ";";
		ResultSet rs = DB.select(query);
		try {
			if(rs.next()) {
				feedUrl = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Parser needs userid, feedid to locate feed url
		// it will request url parse to collection, then iterate collection and fill in DB for articles
		// when it iterates it should check if item id is already in DB for each item before inserting
		// TODO: Maybe parse date to timestamp and keep it in DB as timestamp
		RSSFeedParser parser = new RSSFeedParser(feedUrl);
		Collection feed = parser.readFeed();
		List<Item> articles = feed.getItems();
		Boolean isGuidInDb = false;
		int rowsUpdated = 0;
		for(Item article : articles) {
			ResultSet rs2 = DB.select("select id from articles where guid = '" + article.getGuid() + "';");
			try {
				if(rs2.next()) {
					isGuidInDb = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (!isGuidInDb) {
				rowsUpdated += DB.update("INSERT INTO ARTICLES (ID, FEEDID, TITLE, GUID, LINK, DESCRIPTION, PUBDATE) VALUES " +
						"(null, " +
						feedId + ", '" +
						article.getTitle().replaceAll("'", "''") + "', '" +
						article.getGuid().replaceAll("'", "''") + "', '" +
						article.getLink().replaceAll("'", "''") + "', '" +
						article.getDescription().replaceAll("'", "''") + "', '" +
						article.getPubDate().replaceAll("'", "''") + "');");
			}
			if (rowsUpdated > 0) {
				request.setAttribute("returnType", "success");
			} else {
				request.setAttribute("returnType", "warning");
			}
		}
		request.setAttribute("rowsUpdated", rowsUpdated);
		
		request.setAttribute("viewId", "/WEB-INF/jsp/Authed/UpdateFeed.jsp");
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Common/Index.jsp");
		rd.forward(request, response);
	}

}
