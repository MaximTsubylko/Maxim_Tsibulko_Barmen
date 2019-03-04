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
    <title>Success!</title>
    <meta charset="UTF-8"/>
    <%@include file="/WEB-INF/resources.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/matrix-login.css"/>

</head>
<body>
<div id="loginbox">
    <form id="loginform" class="form-vertical" action="${pageContext.request.contextPath}/barman" method="post">
        <p class="normal_text">Thanks for registration!</p>
        <p class="normal_text"> We send you e-mail with link for confirm account.</p>


        <div class="form-actions">
            <div class="center-pill">
                <div class="row-fluid">
                    <input type="hidden" name="command" value="show_login_page">
                    <button type="submit" class="btn btn-success btn btn-block">&laquo; Back to login</button>
                </div>
            </div>
        </div>
    </form>
</div>


<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/matrix.login.js"></script>

</body>

</html>
