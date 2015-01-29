<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>Article Deletion</h1>
<% if(request.getAttribute("returnType") == "success") { %>
<div class='alert alert-success' role='alert'>
	Successfully deleted article.
</div>
<% } else if(request.getAttribute("returnType") == "warning") { %>
<div class='alert alert-warning' role='alert'>
	Did not delete article.
</div>
<% } %>
