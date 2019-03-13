<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
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

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="error_ex">
                <h3 style="color: #46a546">Your password was successful changed!</h3>
                <h4 style="color: #46a546">New password is - <c:out value="${new_password}"/></h4>
                <h4>Now, you can change password in your profile!</h4>
                <a class="btn btn-warning btn-big"
                   href="${pageContext.request.contextPath}/barman?command=show_edit_page">Edit profile</a>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>

</body>

</html>
