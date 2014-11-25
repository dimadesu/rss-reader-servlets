package servlets;

import java.io.PrintWriter;

public class ReadTest {

	public void renderCollection (PrintWriter out) {
		RSSFeedParser parser = new RSSFeedParser("http://rss.cnn.com/rss/cnn_topstories.rss");
		Collection feed = parser.readFeed();
		out.println(feed);
		for (Item item : feed.getItems()) {
			out.println("<p>" + item + "</p>");
		}
	}

}
