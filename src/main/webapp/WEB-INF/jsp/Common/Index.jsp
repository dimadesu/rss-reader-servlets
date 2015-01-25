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
<div class="container">
<jsp:include page="/WEB-INF/jsp/Common/Header.jsp" />
<div class="well">
<%
String viewId = (String) request.getAttribute("viewId");
if (viewId.equals("HomeServlet")) {
%>    
	<jsp:include page="/WEB-INF/jsp/NotAuthed/Home.jsp" />
<%
//Not authed
} else if (viewId.equals("SignInServlet")) {
%>
	<jsp:include page="/WEB-INF/jsp/NotAuthed/SignIn.jsp" />
<%
} else if (viewId.equals("SignUpServlet")) {
%>
	<jsp:include page="/WEB-INF/jsp/NotAuthed/SignUp.jsp" />
<%
} else if (viewId.equals("SignOutServlet")) {
%>
	<jsp:include page="/WEB-INF/jsp/NotAuthed/SignOut.jsp" />
<%
//Authed
} else if (viewId.equals("FeedViewServlet")) {
%>
	<jsp:include page="/WEB-INF/jsp/Authed/ViewFeed.jsp" />
<%
} else if (viewId.equals("ListFeedUrlsServlet")) {
%>
	<jsp:include page="/WEB-INF/jsp/Authed/ManageFeedUrls/ListFeedUrls.jsp" />
<%
} else if (viewId.equals("AddFeedUrlServlet")) {
%>
	<jsp:include page="/WEB-INF/jsp/Authed/ManageFeedUrls/AddFeedUrl.jsp" />
<%
}
%>
</div>
<jsp:include page="/WEB-INF/jsp/Common/Footer.jsp" />
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
</body>
</html>
