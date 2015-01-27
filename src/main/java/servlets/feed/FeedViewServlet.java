package servlets.feed;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		RSSFeedParser parser = new RSSFeedParser("http://rss.cnn.com/rss/cnn_topstories.rss");
		Collection feed = parser.readFeed();
		request.setAttribute("feed", feed);
		
		request.setAttribute("viewId", "/WEB-INF/jsp/Authed/ViewFeed.jsp");
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Common/Index.jsp");
		rd.forward(request, response);
	}

}
