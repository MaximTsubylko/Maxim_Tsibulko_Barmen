<%--
  Created by IntelliJ IDEA.
  User: Buddtha
  Date: 03.02.2019
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="utf-8"/>


<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="language" var="bundle" scope="application"/>
<html>

<head>
    <title>Matrix Admin</title>
    <meta charset="UTF-8"/>
    <%@include file="/WEB-INF/resources.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/matrix-login.css"/>

</head>
<body>
<div id="loginbox">
    <a href="?change_lang=ru" class="flip-link btn btn-danger">ru</a>
    <a href="?change_lang=en" class="flip-link btn btn-danger">en</a>

    <form id="loginform" class="form-vertical" action="${pageContext.request.contextPath}/barman" method="post">
        <p class="normal_text"><fmt:message key="index.welcomemessage" bundle="${bundle}"/></p>
        <input type="hidden" name="command" value="show_login_page">
        <button type="submit" class="btn btn-success btn btn-block"><fmt:message key="index.startbutton" bundle="${bundle}"/></button>
    </form>
</div>

<script src="static/js/jquery.min.js"></script>
<script src="static/js/matrix.login.js"></script>

</body>

</html>
