<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>Manage Feed Urls</h1>
<p>
	<a href="${pageContext.request.contextPath}/ManageFeedUrls/AddFeedUrl"
		class="btn btn-success">
		<span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
		New Url
	</a>
	<a href='${pageContext.request.contextPath}/Feeds/View'
		class="btn btn-primary">
		<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
		View All Articles
	</a>
</p>
<c:forEach items="${requestScope.feedUrlsList}" var="item">
<h2>${item.url}</h2>  
<p>
	<a href='${pageContext.request.contextPath}/Feeds/Update?id=${item.id}'
		class="btn btn-success">
		<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
		Update Articles
	</a>
	<a href='${pageContext.request.contextPath}/Feeds/View?id=${item.id}'
		class="btn btn-primary">
		<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
		View Articles
	</a>
	<a href='${pageContext.request.contextPath}/Feeds/DeleteOld?id=${item.id}'
		class="btn btn-danger">
		<span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>
		Delete Old Articles
	</a>
	|
	<a href='${pageContext.request.contextPath}/ManageFeedUrls/AddFeedUrl?id=${item.id}'
		class="btn btn-warning">
		<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
		Edit Url
	</a>
	<a href='${pageContext.request.contextPath}/ManageFeedUrls/DeleteFeedUrl?id=${item.id}'
		class="btn btn-danger">
		<span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>
		Delete Url
	</a>
</p>
</c:forEach>
