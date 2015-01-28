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
		String defaultOrderBy = "articles.PUBDATE"; 
		String orderBy = (String) request.getParameter("orderBy");
		String defaultOrderDirection = "DESC";
		String orderDirection = (String) request.getParameter("orderDirection");
		Integer defaultPageNumber = 1;
		String query = "select articles.ID, articles.FEEDID, articles.TITLE, articles.GUID, articles.LINK, articles.DESCRIPTION, articles.PUBDATE from articles" +
			" JOIN FEEDUSER ON FEEDUSER.FEEDID = articles.feedid" +
			" where FEEDUSER.userid = " + request.getSession().getAttribute("USERID");
		if (feedId != "" && feedId != null) {
			query += (" and feedid = " + feedId);
		}
		if(orderBy == null || !orderBy.matches("FEEDID|TITLE|GUID|LINK|DESCRIPTION|PUBDATE")){
			orderBy = defaultOrderBy;
		}
		query += (" order by " + orderBy);
		if(orderDirection == null || !orderDirection.matches("DESC|ASC")){
			orderDirection = defaultOrderDirection;
		}
		query += (" " + orderDirection);
		Integer pageNumber;
		try {
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		} catch (NumberFormatException e1) {
			pageNumber = defaultPageNumber;
		}
		Integer defaultPageSize = 10;
		Integer pageSize;
		try {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		} catch (NumberFormatException e1) {
			pageSize = defaultPageSize;
		}
		query += (" limit " + pageSize + " offset " + (pageNumber - 1) * pageSize);
		ResultSet rs3 = DB.select(query + ";");
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
		
		request.setAttribute("feedId", feedId);
		request.setAttribute("orderBy", orderBy);
		request.setAttribute("orderDirection", orderDirection);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("pageSize", pageSize);
		
		request.setAttribute("viewId", "/WEB-INF/jsp/Authed/ViewFeed.jsp");
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Common/Index.jsp");
		rd.forward(request, response);
	}

}
