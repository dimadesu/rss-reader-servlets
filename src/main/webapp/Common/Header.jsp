<div class="page-header">
	<h1>
		<a href="${pageContext.request.contextPath}/">
			RSS Reader <small>Using Servlets</small>
		</a>
	</h1>
<%
if(session.getAttribute("USERNAME") == null) {
%>
	<p>
		Please
		<a href="${pageContext.request.contextPath}/signin">Sign In</a>
		or
		<a href="${pageContext.request.contextPath}/signup">Sign Up</a>
	</p>
<% 
} else {
%>
	<p>
		Welcome, ${sessionScope.USERNAME}!
		<a href="${pageContext.request.contextPath}/signout">Sign Out</a>
	</p>
	<p>
		<a href="${pageContext.request.contextPath}/">Home</a>
		|
		<a href="${pageContext.request.contextPath}/Feed">View Feed</a>
	</p>
<% } %>
</div>
