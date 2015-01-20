<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="${pageContext.request.contextPath}/assets/favicon.ico">
<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/assets/styles/bootstrap.css" rel="stylesheet" />
<title>RSS Reader</title>
</head>
<body>
<jsp:include page="/DBTest" />
<div class="container">
<%@ include file="../Common/Header.jsp" %>
<div class="well">
<%
String pageId = config.getInitParameter("p");
if(pageId == null) { 
%>    
	<%@ include file="../NotAuthed/Home.jsp" %>
<%
// Not authed
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
// Authed
} else if (pageId.equals("Feed")) {
%>
	<jsp:include page="/FeedServlet" />
<%
} else if (pageId.equals("ManageFeeds")) {
%>
	<%@ include file="../Authed/ManageFeedUrls/ListFeedUrls.jsp" %>
<%
} else if (pageId.equals("AddFeed")) {
%>
	<jsp:include page="/WEB-INF/jsp/Authed/ManageFeedUrls/AddFeedUrl.jsp" />
<%
}
%>
</div>
<%@ include file="../Common/Footer.jsp" %>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
</body>
</html>
