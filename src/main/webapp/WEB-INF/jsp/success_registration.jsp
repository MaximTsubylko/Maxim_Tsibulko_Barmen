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
</c:choose><fmt:setBundle basename="language" var="bundle" scope="application"/>
<html>

<head>
    <title>Success!</title>
    <meta charset="UTF-8"/>
    <%@include file="/WEB-INF/resources.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/matrix-login.css"/>

</head>
<body>
<div id="loginbox">
    <form id="loginform" class="form-vertical" action="${pageContext.request.contextPath}/barman" method="post">
        <p class="normal_text"><fmt:message key="registration.success.thanks" bundle="${bundle}"/></p>
        <p class="normal_text"><fmt:message key="registration.success.send" bundle="${bundle}"/></p>


        <div class="form-actions">
            <div class="center-pill">
                <div class="row-fluid">
                    <input type="hidden" name="command" value="show_login_page">
                    <button type="submit" class="btn btn-success btn btn-block"><fmt:message key="button.backtologin" bundle="${bundle}"/></button>
                </div>
            </div>
        </div>
    </form>
</div>


<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/matrix.login.js"></script>

</body>

</html>
