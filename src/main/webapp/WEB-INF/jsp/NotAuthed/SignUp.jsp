<h1>Sign Up</h1>
<% if(session.getAttribute("USERID") != null){ %>
	<% if(request.getAttribute("returnType") == null) { %>
		<div class="alert alert-warning">You're already signed up.</div>
	<% } else { %>
		<%-- Success only --%>
		<div class='alert alert-success' role='alert'>
			${requestScope.returnMessage}
		</div>
	<% } %>
<% } else { %>
	<% if(request.getAttribute("returnType") != null) { %>
		<%-- Errors only --%>
		<div class='alert alert-danger' role='alert'>
			${requestScope.returnMessage}
		</div>
	<% } %>
	<form action="" method="post">
		<div class="form-group">
			<label for="username">Username</label>
			<input class="form-control" type="text" id="username" name="username" placeholder="Username" value="${requestScope.username}" />
		</div>
		<div class="form-group">
			<label for="password">Password</label>
			<input class="form-control" type="password" id="password" name="password" placeholder="Password" />
		</div>
		<div class="text-center">
			<input class="btn btn-primary" type="submit" value="Sign Up" />
		</div>
	</form>
<% } %>
