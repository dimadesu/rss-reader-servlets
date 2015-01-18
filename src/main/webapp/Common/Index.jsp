<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<div class="container">
<%@ include file="../Common/Header.jsp" %>
<div class="well">
<%
String pageId = config.getInitParameter("p");
if(pageId == null) { 
%>    
	<%@ include file="../NotAuthed/Index.jsp" %>
<%
} else if (pageId.equals("SignIn")) {
%>
	<%@ include file="../NotAuthed/SignIn.jsp" %>
<%
} else if (pageId.equals("SignUp")) {
%>
	<%@ include file="../NotAuthed/SignUp.jsp" %>
<%
} else if (pageId.equals("SignOut")) {
%>
	<%@ include file="../NotAuthed/SignOut.jsp" %>
<%
} else if (pageId.equals("Feed")) {
%>
	<jsp:include page="/FeedServlet" />
<%
}
%>
</div>
<%@ include file="../Common/Footer.jsp" %>
</div>
<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/assets/styles/bootstrap.css" rel="stylesheet" />
</body>
</html>
