<h1>
	${requestScope.id != null ? "Edit" : "Add"}
	RSS Feed URL
</h1>
<% if(request.getAttribute("message") != null) { %>
	<div class='alert alert-success' role='alert'>${requestScope.message}</div>
<% } else { %>
	<form action="" method="post">
		<div class="form-group">
			<input name="url" type="text" class="form-control" placeholder="Feed URL" value="${requestScope.url}" />
		</div>
		<div class="text-center">
			<button type="submit" class="btn btn-primary">
				${requestScope.id != null ? "Save" : "Add"}
			</button>
			<input name="id" type="hidden" value="${requestScope.id}"/>
			<a class="btn btn-default" href="${pageContext.request.contextPath}/ManageFeedUrls">Cancel</a>
		</div>
	</form>
<% } %>
