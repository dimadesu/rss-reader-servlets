<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>Feed</h1>
<ul>
	<li>title: ${requestScope.feed.title}</li>
	<li>guid: ${requestScope.feed.guid}</li>
	<li>link: ${requestScope.feed.link}</li>
	<li>description: ${requestScope.feed.description}</li>
	<li>pubDate: ${requestScope.feed.pubDate}</li>
</ul>
<c:forEach items="${requestScope.feed.getItems()}" var="item">
<ul>
	<li>title: ${item.title}</li>
	<li>guid: ${item.guid}</li>
	<li>link: ${item.link}</li>
	<li>description: ${item.description}</li>
	<li>pubDate: ${item.pubDate}</li>
</ul>
</c:forEach>