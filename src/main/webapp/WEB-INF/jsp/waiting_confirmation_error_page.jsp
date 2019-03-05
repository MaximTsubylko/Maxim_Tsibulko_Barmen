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
    <title>Error of access</title>
    <meta charset="UTF-8"/>
    <%@include file="/WEB-INF/resources.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/matrix-login.css"/>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="error_ex">
                <h1>405</h1>
                <h3 style="color: #2d6987">Sorry, you haven`t got access to this page, because you not confirm
                    your account!</h3>
                <p style="color: #2d6987"> We send you e-mail with link for confirm account. Please, check your e-mail
                    address</p>
                <a class="btn btn-warning btn-big"
                   href="${pageContext.request.contextPath}/barman?command=show_index_page">Back to Home</a>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>

</body>

</html>