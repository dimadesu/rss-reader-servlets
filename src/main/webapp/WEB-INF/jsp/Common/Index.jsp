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
<link href="${pageContext.request.contextPath}/assets/styles/datepicker3.css" rel="stylesheet" />
<title>RSS Reader</title>
</head>
<body>
<div class="container">
<jsp:include page="/WEB-INF/jsp/Common/Header.jsp" />
<div class="well">
<jsp:include page='<%= (String) request.getAttribute("viewId") %>' />
</div>
<jsp:include page="/WEB-INF/jsp/Common/Footer.jsp" />
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/libs/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/libs/bootstrap-datepicker.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/datefilter.js"></script>
</body>
</html>
