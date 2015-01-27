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
import servlets.xml.Collection;
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
		DB.select("select f from feeds where ");
		// Returns: url
		String query = "SELECT FEEDS.URL  " +
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
		feed.getItems();
		request.setAttribute("feed", feed);
		
		request.setAttribute("viewId", "/WEB-INF/jsp/Authed/ViewFeed.jsp");
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Common/Index.jsp");
		rd.forward(request, response);
	}

}
