<%
String username = config.getInitParameter("username");
if(username != null) {
	session.setAttribute("USERNAME", username); 
}
%> 
<h1>Login</h1>
<form action="">
	Username <input type="text" name="username" />
	Password <input type="password" name="password" />
	<input type="submit" value="Sign In" />
</form>
