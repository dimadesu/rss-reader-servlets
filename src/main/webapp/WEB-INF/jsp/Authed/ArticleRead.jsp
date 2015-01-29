<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>Read Status Update</h1>
<% if(request.getAttribute("returnType") == "success") { %>
<div class='alert alert-success' role='alert'>
	Changed to: <b>${requestScope.isRead ? 'read' : 'not read'}</b>.
</div>
<% } else if(request.getAttribute("returnType") == "warning") { %>
<div class='alert alert-warning' role='alert'>
	Did not update.
</div>
<% } %>
