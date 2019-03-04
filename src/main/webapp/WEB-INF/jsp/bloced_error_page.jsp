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
                <h3 style="color: #2d6987">Sorry, you haven`t got access to this page, because you have been
                    banned!</h3>
                <p style="color: #2d6987"> For some reason you have been banned..
                    You can send message for us, and we try to help!</p>
                <p> Our e-mail address - barmansupp@gmail.com</p>
                <a class="btn btn-warning btn-big"
                   href="${pageContext.request.contextPath}/barman?command=show_index_page">Back to Home</a>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>

</body>

</html>
