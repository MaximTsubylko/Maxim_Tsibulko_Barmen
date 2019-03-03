<%--
  Created by IntelliJ IDEA.
  User: Buddtha
  Date: 03.02.2019
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <title>Matrix Admin</title>
    <meta charset="UTF-8"/>
    <%@include file="/WEB-INF/resources.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/matrix-login.css"/>

</head>
<body>
<div id="loginbox">
    <form id="loginform" class="form-vertical" action="${pageContext.request.contextPath}/barman" method="post">
        <p class="normal_text">Welcome! It`s very demo version, push button!</p>
        <input type="hidden" name="command" value="show_login_page">
        <button type="submit" class="btn btn-success btn btn-block">Start</button>
    </form>
</div>

<script src="static/js/jquery.min.js"></script>
<script src="static/js/matrix.login.js"></script>

</body>

</html>
