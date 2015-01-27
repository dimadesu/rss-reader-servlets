package servlets.feed;

import java.io.PrintWriter;

import servlets.xml.Collection;
import servlets.xml.Item;
import servlets.xml.RSSFeedParser;

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
