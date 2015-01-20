<h1>
	${param.id != null ? 'Edit' : 'Add'}
	 RSS Feed URL
</h1>
<form action="" method="post">
	<div class="form-group">
		<input name="url" type="text" class="form-control" placeholder="Feed URL" />
	</div>
	<div class="text-center">
		<button type="submit" class="btn btn-primary">
			${param.id != null ? 'Save' : 'Add'}
		</button>
		<input name="id" type="hidden" value="${param.id}"/>
		<a class="btn btn-default" href="${pageContext.request.contextPath}/ManageFeeds">Cancel</a>
	</div>
</form>
