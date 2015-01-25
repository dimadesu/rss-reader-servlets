<h1>Sign In</h1>
<% if(session.getAttribute("USERID") != null && request.getAttribute("returnType") == null){ %>
	<div class="alert alert-warning">You're already signed in</div>
<% } else { %>
	<% if(request.getAttribute("returnType") != null) { %>
		<div class='alert
			${requestScope.returnType == "success" ? "alert-success" : "alert-danger"}'
			role='alert'>
			${requestScope.returnMessage}
		</div>
	<% } %>
	<form action="" method="post">
		Username <input type="text" name="username" value="${requestScope.username}" />
		Password <input type="password" name="password" />
		<input type="submit" value="Sign In" />
	</form>
<% } %>
