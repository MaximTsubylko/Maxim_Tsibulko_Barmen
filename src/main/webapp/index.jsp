<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="utf-8"/>


<c:choose>
    <c:when test="${not empty requestScope.get('lang')}">
        <fmt:setLocale value="${requestScope.get('lang')}"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${cookie['locale'].value}"/>
    </c:otherwise>
</c:choose>

<fmt:setBundle basename="language" var="bundle" scope="application"/>
<html>
<head>

    <a>
        <form action="${pageContext.request.contextPath}/barman" method="post">
            <input type="hidden" name="command" value="show_index_page">
            <input type="hidden" name="change_lang" value="ru">
            <button type="submit" class="btn btn-info btn btn-block">Ru</button>
        </form>
    </a>
    <a>
        <form action="${pageContext.request.contextPath}/barman" method="post">
            <input type="hidden" name="command" value="show_index_page">
            <input type="hidden" name="change_lang" value="en">
            <button type="submit" class="btn btn-info btn btn-block ">En</button>
        </form>
    </a>

    <title><fmt:message key="title.barmanhelper" bundle="${bundle}"/></title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css"/>
    <noscript>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/noscript.css"/>
    </noscript>
</head>
<body class="is-preload">


<!-- Header -->
<section id="header">
    <header>
        <h1><fmt:message key="index.barman" bundle="${bundle}"/></h1>
        <p><fmt:message key="index.helper" bundle="${bundle}"/></p>
    </header>
    <%--<footer>--%>
        <%--<a href="#banner" class="button style2 scrolly-middle"><fmt:message key="index.join" bundle="${bundle}"/></a>--%>
    <%--</footer>--%>
</section>

<!-- Banner -->
<section id="banner">
    <header>
        <h2><fmt:message key="title.barmanhelper" bundle="${bundle}"/></h2>
    </header>
    <p><fmt:message key="index.info1" bundle="${bundle}"/><br/>
        <fmt:message key="index.info2" bundle="${bundle}"/><br/>
        <fmt:message key="index.info3" bundle="${bundle}"/><br/>
        <fmt:message key="index.info4" bundle="${bundle}"/>
    </p>
    <footer>
        <form id="loginform" class="form-vertical" action="${pageContext.request.contextPath}/barman" method="post">
        <input type="hidden" name="command" value="show_login_page">
        <button type="submit" style=""><fmt:message key="index.startbutton" bundle="${bundle}"/></button>
        </form>
    </footer>
</section>


<!-- Scripts -->
<script src="${pageContext.request.contextPath}/static/indexjs/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/indexjs/jquery.scrolly.min.js"></script>
<script src="${pageContext.request.contextPath}/static/indexjs/jquery.poptrox.min.js"></script>
<script src="${pageContext.request.contextPath}/static/indexjs/browser.min.js"></script>
<script src="${pageContext.request.contextPath}/static/indexjs/breakpoints.min.js"></script>
<script src="${pageContext.request.contextPath}/static/indexjs/util.js"></script>
<script src="${pageContext.request.contextPath}/static/indexjs/main.js"></script>


</body>
</html>