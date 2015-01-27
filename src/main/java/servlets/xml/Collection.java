package servlets.xml;

import java.util.ArrayList;
import java.util.List;

public class Collection {

	final String title;
	final String guid;
	final String link;
	final String description;
	final String pubDate;

	List<Item> entries = new ArrayList<Item>();

	public Collection(String title, String guid, String link,
			String description, String pubDate) {
		this.title = title;
		this.guid = guid;
		this.link = link;
		this.description = description;
		this.pubDate = pubDate;
	}

	public List<Item> getItems() {
		return entries;
	}

	public void setItems(List<Item> list) {
		entries = list;
	}

	public String getTitle() {
		return title;
	}

	public String getGuid() {
		return guid;
	}

	public String getLink() {
		return link;
	}

	public String getDescription() {
		return description;
	}

	public String getPubDate() {
		return pubDate;
	}

	@Override
	public String toString() {
		return "<h2>" + title + "</h2>" +
				"<p>" + description + "</p>" +
				"<p>" + pubDate + "</p>" +
				"<p><a href='" + link + "'>" + link + "</a></p>";
	}

}
