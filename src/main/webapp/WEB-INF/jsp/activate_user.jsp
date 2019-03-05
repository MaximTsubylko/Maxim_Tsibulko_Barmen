<%--
  Created by IntelliJ IDEA.
  User: Buddtha
  Date: 03.02.2019
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">

<head>
    <title>Success!</title>
    <meta charset="UTF-8"/>
    <%@include file="/WEB-INF/resources.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/matrix-login.css"/>
</head>
<body>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="error_ex">
                <h3 style="color: #46a546">Your account was successful activate!</h3>
                <h4>Now, you can join for us!</h4>
                <a class="btn btn-warning btn-big"
                   href="${pageContext.request.contextPath}/barman?command=show_login_page">Log in!</a>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>

</body>

</html>