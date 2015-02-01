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

public class FeedViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FeedViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer userId = (Integer) request.getSession().getAttribute("USERID");
		String feedId = (String) request.getParameter("id");
		
		String defaultOrderBy = "PUBDATE"; 
		String orderBy = (String) request.getParameter("orderBy");
		
		String defaultOrderDirection = "DESC";
		String orderDirection = (String) request.getParameter("orderDirection");
		
		String queryEnd = "";
		
		String queryStart1 = "select articles.ID, articles.FEEDID, articles.TITLE, articles.GUID, " +
				"articles.LINK, articles.DESCRIPTION, articles.PUBDATE";
		
		String queryStart2 = "select count(*)";
		
		String queryMiddle = " from articles" +
			" JOIN FEEDUSER ON FEEDUSER.FEEDID = articles.feedid" +
			" where FEEDUSER.userid = " + request.getSession().getAttribute("USERID");
		
		Long dateStart = null;
		if(request.getParameter("dateStart") != null) {
			try {
				dateStart = Long.parseLong(request.getParameter("dateStart"));
			} catch (NumberFormatException e2) {
				e2.printStackTrace();
			}
		}
		
		Long dateEnd = null;
		if(request.getParameter("dateEnd") != null){
			try {
				dateEnd = Long.parseLong(request.getParameter("dateEnd"));
			} catch (NumberFormatException e2) {
				e2.printStackTrace();
			}
		}
		
		if (dateStart != null && dateEnd != null) {
			queryMiddle += new String (" and articles.pubdate BETWEEN " + dateStart + " AND " + dateEnd);
		} else if (dateStart != null) {
			queryMiddle += new String (" and articles.pubdate >= " + dateStart);
		} else if (dateEnd != null) {
			queryMiddle += new String (" and articles.pubdate <= " + dateEnd);
		}
		
		if (feedId != "" && feedId != null) {
			queryMiddle += new String (" and FEEDUSER.feedid = " + feedId);
		}
		
		if(orderBy == null || !orderBy.matches("FEEDID|TITLE|GUID|LINK|DESCRIPTION|PUBDATE")){
			orderBy = defaultOrderBy;
		}
		
		queryEnd += new String (" order by articles." + orderBy);
		
		if(orderDirection == null || !orderDirection.matches("DESC|ASC")){
			orderDirection = defaultOrderDirection;
		}
		
		queryEnd += new String (" " + orderDirection);
		
		Integer defaultPageNumber = 1;
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
		queryEnd += new String (" limit " + pageSize + " offset " + (pageNumber - 1) * pageSize);
		
		ResultSet rs3 = DB.select(queryStart1 + queryMiddle + queryEnd + ";");
		List<Item> newFeedItems = new ArrayList<Item> ();
		try {
			while(rs3.next()) {
				Integer articleId = rs3.getInt(1);
				Item item = new Item (rs3.getString(3), rs3.getString(4), rs3.getString(5), rs3.getString(6), rs3.getLong(7));
				item.setId(articleId);
				ResultSet rs4 = DB.select("select isread from article_state where articleid = " + articleId + " and userid = " + userId + ";");
				// If we have row for isread state use it. isRead if false by default in model
				if(rs4.next()) {
					item.setIsRead(rs4.getBoolean(1));
				}
				newFeedItems.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Collection feed = new Collection();
		feed.setItems(newFeedItems);
		request.setAttribute("feed", feed);
		
		ResultSet rsCountAllItems = DB.select(queryStart2 + queryMiddle + ";");
		Integer numberOfArticles = 0;
		Integer numberOfPages = 0;
		try {
			if(rsCountAllItems.next()) {
				numberOfArticles = rsCountAllItems.getInt(1);
				numberOfPages = (int) Math.ceil(numberOfArticles * 1.0 / pageSize);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("feedId", feedId);
		request.setAttribute("orderBy", orderBy);
		request.setAttribute("orderDirection", orderDirection);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("pageSize", pageSize);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("dateStart", dateStart);
        request.setAttribute("dateEnd", dateEnd);
		
		request.setAttribute("viewId", "/WEB-INF/jsp/Authed/ViewFeed.jsp");
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Common/Index.jsp");
		rd.forward(request, response);
	}

}
