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
		<a href="${pageContext.request.contextPath}/SignIn">Sign In</a>
		or
		<a href="${pageContext.request.contextPath}/SignUp">Sign Up</a>
	</p>
<% 
} else {
%>
	<p>
		Hi, ${sessionScope.USERNAME}!
		<a href="${pageContext.request.contextPath}/SignOut">Sign Out</a>
	</p>
	<p>
		<a href="${pageContext.request.contextPath}/Feed">View Feed</a>
		|
		<a href="${pageContext.request.contextPath}/ManageFeedUrls">Manage Feeds</a>
	</p>
<% } %>
</div>
