<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>Feed Update</h1>
<% if(request.getAttribute("returnType") == "success") { %>
<div class='alert alert-success' role='alert'>
	Updated ${requestScope.rowsUpdated} items.
</div>
<% } else if(request.getAttribute("returnType") == "warning") { %>
<div class='alert alert-warning' role='alert'>
	No updates.
</div>
<% } %>
