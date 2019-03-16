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
    <title>Matrix Admin</title>
    <meta charset="UTF-8"/>
    <%@include file="/WEB-INF/resources.jsp" %>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/matrix-login.css"/>

</head>

<body>
<div id="user-nav" class="navbar">
    <div class="btn-group ">
        <ul class="nav" style="width: auto; margin-left: 150vh;">
            <li>
                <button data-toggle="dropdown" class="btn btn-inverse dropdown-toggle"><fmt:message key="main.language"
                                                                                                    bundle="${bundle}"/>
                    <span
                            class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a>
                        <form action="${pageContext.request.contextPath}/barman" method="post">
                            <input type="hidden" name="command" value="show_index_page">
                            <input type="hidden" name="change_lang" value="ru">
                            <button type="submit" class="btn btn-info btn btn-block">Ru</button>
                        </form>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li><a>
                        <form action="${pageContext.request.contextPath}/barman" method="post">
                            <input type="hidden" name="command" value="show_index_page">
                            <input type="hidden" name="change_lang" value="en">
                            <button type="submit" class="btn btn-info btn btn-block ">En</button>
                        </form>
                    </a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</div>


<div id="loginbox">
    <form id="loginform" class="form-vertical" action="${pageContext.request.contextPath}/barman" method="post">
        <p class="normal_text"><fmt:message key="index.welcomemessage" bundle="${bundle}"/></p>
        <input type="hidden" name="command" value="show_login_page">
        <button type="submit" class="btn btn-success btn btn-block"><fmt:message key="index.startbutton"
                                                                                 bundle="${bundle}"/></button>
    </form>
</div>
<ul class="typeahead dropdown-menu"></ul>
<script src="static/js/jquery.min.js"></script>
<script src="static/js/matrix.login.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/matrix.js"></script>
<script src="static/js/jquery.min.js"></script>
<script src="static/js/jquery.ui.custom.js"></script>
</body>

</html>
