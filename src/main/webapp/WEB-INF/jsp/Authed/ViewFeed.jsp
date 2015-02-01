<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>Feed</h1>
<form action="" method="get">
	<div class="row">
		<div class="col-md-3">
			<div class="form-group">
				<label for="searchTerm">Search term</label>
				<input class="form-control" type="text" id="searchTerm" name="searchTerm" value="${requestScope.searchTerm}" />
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
		<div class="col-md-3">
			<label>Date range</label>
			<div class="input-daterange input-group" id="datepicker">
			    <input type="text" class="input-sm form-control" id="date-start" />
			    <input type="hidden" name="dateStart" id="date-start-hidden" value="${requestScope.dateStart}" />
			    <span class="input-group-addon">to</span>
			    <input type="text" class="input-sm form-control" id="date-end" />
			    <input type="hidden" name="dateEnd" id="date-end-hidden" value="${requestScope.dateEnd}" />
			</div>
			<input type="hidden" id="datepicker-hidden" name="" />
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
	<span class="glyphicon ${item.isRead ? 'glyphicon-ok text-success' : 'glyphicon-certificate text-danger'}" aria-hidden="true"
		title="${item.isRead ? 'Read' : 'New'}"></span>
	<a href="${item.link}">${item.title}</a>
</h2>
<p>${item.description}</p>
<input type="hidden" name="articleGuid" value="${item.guid}" />
<p>${item.getPubDateFormatted()}</p>
<p>
	<a href='${pageContext.request.contextPath}/Article/Read?articleId=${item.id}' class="btn btn-default">
		<span class="glyphicon ${!item.isRead ? 'glyphicon-ok text-success' : 'glyphicon-certificate text-danger'}" aria-hidden="true"></span>
		Mark as ${item.isRead ? 'Unread' : 'Read'}
	</a>
	<a href='${pageContext.request.contextPath}/Article/Delete?articleId=${item.id}'
		class="btn btn-default">
		<span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>
		Delete Article
	</a>
</p>
</c:forEach>

<div class="row">
<div class="col-xs-1">
<c:choose>
	<c:when test="${requestScope.pageNumber != 1}">
		<a class="btn btn-default"
			href="${pageContext.request.contextPath}/Feeds/View?searchTerm=${requestScope.searchTerm}&id=${requestScope.feedId}&pageNumber=${requestScope.pageNumber - 1}&orderBy=${requestScope.orderBy}&orderDirection=${requestScope.orderDirection}&dateStart=${requestScope.dateStart}&dateEnd=${requestScope.dateEnd}">
			<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
			Previous
		</a>
    </c:when>
    <c:otherwise>
		<span class="btn btn-default disabled">
			<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
			Previous
		</span>
	</c:otherwise>
</c:choose>
</div>
<div class="col-xs-10 text-center">
<c:forEach begin="1" end="${requestScope.numberOfPages}" var="i">
    <c:choose>
        <c:when test="${requestScope.pageNumber eq i}">
            <span class="btn btn-default disabled">${i}</span>
        </c:when>
        <c:otherwise>
            <a class="btn btn-default"
				href="${pageContext.request.contextPath}/Feeds/View?searchTerm=${requestScope.searchTerm}&id=${requestScope.feedId}&pageNumber=${i}&orderBy=${requestScope.orderBy}&orderDirection=${requestScope.orderDirection}&dateStart=${requestScope.dateStart}&dateEnd=${requestScope.dateEnd}">
				${i}
			</a>
        </c:otherwise>
    </c:choose>
</c:forEach>
</div>
<div class="col-xs-1 text-right">
<c:choose>
	<c:when test="${requestScope.pageNumber lt requestScope.numberOfPages}">
		<a class="btn btn-default"
			href="${pageContext.request.contextPath}/Feeds/View?searchTerm=${requestScope.searchTerm}&id=${requestScope.feedId}&pageNumber=${requestScope.pageNumber + 1}&orderBy=${requestScope.orderBy}&orderDirection=${requestScope.orderDirection}&dateStart=${requestScope.dateStart}&dateEnd=${requestScope.dateEnd}">
			Next
			<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
		</a>
    </c:when>
    <c:otherwise>
		<span class="btn btn-default disabled">
			Next
			<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
		</span>
	</c:otherwise>
</c:choose>
</div>
</div>
