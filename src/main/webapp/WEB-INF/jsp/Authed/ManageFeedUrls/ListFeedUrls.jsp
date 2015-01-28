<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>Manage Feeds</h1>
<c:forEach items="${requestScope.feedUrlsList}" var="item">  
<p>
	${item.url}
	<a href='${pageContext.request.contextPath}/Feeds/Update?id=${item.id}'
		class="btn btn-success">
		<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
		Update
	</a>
	<a href='${pageContext.request.contextPath}/Feeds/View?id=${item.id}'
		class="btn btn-primary">
		<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
		View
	</a>
	<a href='${pageContext.request.contextPath}/ManageFeedUrls/AddFeedUrl?id=${item.id}'
		class="btn btn-warning">
		<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
		Edit
	</a>
	<a href='${pageContext.request.contextPath}/ManageFeedUrls/DeleteFeedUrl?id=${item.id}'
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
