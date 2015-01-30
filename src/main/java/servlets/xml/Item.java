package servlets.xml;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Item {

	private String title;
	private String guid;
	private String link;
	private String description;
	private Long pubDate;
	private Boolean isRead = false;
	private Integer id;

	public Item() {
	}
	
	public Item(String title, String guid, String link,
			String description, Long pubDate) {
		this.title = title;
		this.guid = guid;
		this.link = link;
		this.description = description;
		this.pubDate = pubDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPubDate() {
		return pubDate;
	}

	public String getPubDateFormatted() {
		return new Date(pubDate).toString();
	}

	public void setPubDate(String pubDate) {
		this.pubDate = parseDate(pubDate);
	}
	
	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Long parseDate (String pubDate) {
		DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
		Date date = null;
		try {
			date = formatter.parse(pubDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date.getTime();
	}

}
