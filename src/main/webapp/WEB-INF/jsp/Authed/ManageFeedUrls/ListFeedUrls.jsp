<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>Manage Feeds</h1>
<c:forEach items="${requestScope.feedUrlsList}" var="item">  
<p>
	<c:out value="${item.url}"/>
	<a href='${pageContext.request.contextPath}/ManageFeedUrls/AddFeedUrl?id=<c:out value="${item.id}"/>'
		class="btn btn-default">
		<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
		Edit
	</a>
	<a href='${pageContext.request.contextPath}/ManageFeeds/Delete?id=<c:out value="${item.id}"/>'
		class="btn btn-danger">
		<span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>
		Delete
	</a>
</p>
</c:forEach>
<p>
	<a href="${pageContext.request.contextPath}/ManageFeedUrls/AddFeedUrl"
		class="btn btn-primary">
		<span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
		Add
	</a>
</p>
