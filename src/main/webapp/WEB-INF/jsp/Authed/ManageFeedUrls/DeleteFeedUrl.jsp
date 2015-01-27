<h1>
	Deleting RSS Feed URL
</h1>
<% if(request.getAttribute("returnType") == "success") { %>
<div class='alert alert-success' role='alert'>
	Url deleted.
</div>
<% } else { %>
<div class='alert alert-danger' role='alert'>
	Error deleting.
</div>
<% } %>