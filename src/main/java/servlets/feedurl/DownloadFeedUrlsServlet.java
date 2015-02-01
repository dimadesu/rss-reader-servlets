package servlets.feedurl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlets.db.DB;

public class DownloadFeedUrlsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DownloadFeedUrlsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		Integer userId = Integer.parseInt(request.getSession().getAttribute("USERID").toString());
		String query = "SELECT FEEDS.URL  " +
				"FROM FEEDUSER JOIN FEEDS ON FEEDUSER.FEEDID = FEEDS.ID " +
				"WHERE FEEDUSER.USERID = "+ userId +";";
		ResultSet rs = DB.select(query);
		try {
			PrintWriter out = response.getWriter();
			while(rs.next()){
				out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		response.setContentType("text/plain");
		response.setHeader("Content-Disposition", "attachment;filename=my-rss-feed-urls.txt");
	}

}
