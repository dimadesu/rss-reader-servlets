package servlets.xml;

public class Item {

	private String title;
	private String guid;
	private String link;
	private String description;
	private String pubDate;
	private Boolean isRead = false;
	private Integer id;

	public Item() {
	}
	
	public Item(String title, String guid, String link,
			String description, String pubDate) {
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

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
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

	@Override
	public String toString() {
		return "<h2>" + title + "</h2>" +
				"<p>" + description + "</p>" +
				"<p>" + pubDate + "</p>" +
				"<p><a href='" + link + "'>" + link + "</a></p>";
	}

}
