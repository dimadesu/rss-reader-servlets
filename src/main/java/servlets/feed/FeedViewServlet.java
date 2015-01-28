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
		String feedId = (String) request.getParameter("id");
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
		Collection feed = new Collection();
		feed.setItems(newFeedItems);
		request.setAttribute("feed", feed);
		
		request.setAttribute("viewId", "/WEB-INF/jsp/Authed/ViewFeed.jsp");
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Common/Index.jsp");
		rd.forward(request, response);
	}

}
