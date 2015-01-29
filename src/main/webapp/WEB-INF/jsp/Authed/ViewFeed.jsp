<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>Feed</h1>
<form action="" method="get">
	<div class="row">
		<div class="col-md-2">
			<div class="form-group">
				<label for="pageNumber">Page number</label>
				<input class="form-control" type="text" id="pageNumber" name="pageNumber" value="${requestScope.pageNumber}" />
			</div>
		</div>
		<div class="col-md-2">
			<div class="form-group">
				<label for="pageSize">Items per page</label>
				<input class="form-control" type="text" id="pageSize" name="pageSize" value="${requestScope.pageSize}" />
			</div>
		</div>
		<div class="col-md-2">
			<div class="form-group">
				<label for="orderBy">Order by</label>
				<select class="form-control" id="orderBy" name="orderBy">
					<option value="ID" ${requestScope.orderBy == 'ID' ? 'selected' : ''}>ID</option>
					<option value="FEEDID" ${requestScope.orderBy == 'FEEDID' ? 'selected' : ''}>FEEDID</option>
					<option value="GUID" ${requestScope.orderBy == 'GUID' ? 'selected' : ''}>GUID</option>
					<option value="LINK" ${requestScope.orderBy == 'LINK' ? 'selected' : ''}>LINK</option>
					<option value="DESCRIPTION" ${requestScope.orderBy == 'DESCRIPTION' ? 'selected' : ''}>DESCRIPTION</option>
					<option value="PUBDATE" ${requestScope.orderBy == 'PUBDATE' ? 'selected' : ''}>PUBDATE</option>
				</select>
			</div>
		</div>
		<div class="col-md-2">
			<div class="form-group">
				<label for="orderDirection">Order type</label>
				<select class="form-control" id="orderDirection" name="orderDirection">
					<option value="DESC" ${requestScope.orderDirection == 'DESC' ? 'selected' : ''}>DESC</option>
					<option value="ASC" ${requestScope.orderDirection == 'ASC' ? 'selected' : ''}>ASC</option>
				</select>
			</div>
		</div>
		<div class="col-md-2">
			<label>&nbsp;</label>
			<input class="btn btn-primary form-control" type="submit" value="Submit" />
		</div>
	</div>
</form>
<%-- <ul>
	<li>title: ${requestScope.feed.title}</li>
	<li>guid: ${requestScope.feed.guid}</li>
	<li>link: ${requestScope.feed.link}</li>
	<li>description: ${requestScope.feed.description}</li>
	<li>pubDate: ${requestScope.feed.pubDate}</li>
</ul> --%>
<c:forEach items="${requestScope.feed.getItems()}" var="item">
<h2>
	
	<a href='${pageContext.request.contextPath}/Article/Read?articleId=${item.id}' class="btn btn-default" title="Mark as ${item.isRead ? 'Unread' : 'Read'}">
		<span class="glyphicon ${item.isRead ? 'glyphicon-ok text-success' : 'glyphicon-certificate text-danger'}" aria-hidden="true"></span>
		${item.isRead ? 'Read' : 'New'}
	</a>
	<a href="${item.link}">${item.title}</a>
</h2>
<p>${item.description}</p>
<input type="hidden" name="articleGuid" value="${item.guid}" />
<p>${item.pubDate}</p>
</c:forEach>
