package servlets.feed;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

/**
 * Servlet implementation class Home
 */
public class FeedViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedViewServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
		// Maybe parse date to timestamp and keep it in DB as timestamp
		RSSFeedParser parser = new RSSFeedParser(feedUrl);
		Collection feed = parser.readFeed();
		List<Item> articles = feed.getItems();
		Boolean isGuidInDb = false;
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
				DB.update("INSERT INTO ARTICLES (ID, FEEDID, TITLE, GUID, LINK, DESCRIPTION, PUBDATE) VALUES " +
						"(null, " +
						feedId + ", '" +
						article.getTitle().replaceAll("'", "''") + "', '" +
						article.getGuid().replaceAll("'", "''") + "', '" +
						article.getLink().replaceAll("'", "''") + "', '" +
						article.getDescription().replaceAll("'", "''") + "', '" +
						article.getPubDate().replaceAll("'", "''") + "');");
			}
		}
		
		ResultSet rs3 = DB.select("select ID, FEEDID, TITLE, GUID, LINK, DESCRIPTION, PUBDATE from articles where feedid = " + feedId + " order by id limit 10 offset 0;");
		List<Item> newFeedItems = new ArrayList<Item> ();
		try {
			while(rs3.next()) {
				Item item = new Item (rs3.getString(3), rs3.getString(4), rs3.getString(5), rs3.getString(6), rs3.getString(7));
				newFeedItems.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		feed.setItems(newFeedItems);
		request.setAttribute("feed", feed);
		
		request.setAttribute("viewId", "/WEB-INF/jsp/Authed/ViewFeed.jsp");
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Common/Index.jsp");
		rd.forward(request, response);
	}

}
