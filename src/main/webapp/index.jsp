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
</head>
<body>
<div id="loginbox">
    <form id="loginform" class="form-vertical" action="${pageContext.request.contextPath}/barman" method="post" >
        <div class="control-group normal_text"><h3><img src="static/img/logo.png" alt="Logo"/></h3></div>
        <div class="control-group">
            <div class="controls">
                <div class="main_input_box">
                    <span class="add-on bg_lg"><i class="icon-user"> </i></span><input type="text" name="login"
                                                                                       placeholder="Username"/>
                </div>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <div class="main_input_box">
                    <span class="add-on bg_ly"><i class="icon-lock"></i></span><input type="password" name="password"
                                                                                      placeholder="Password"/>
                </div>
            </div>
        </div>
        <div class="form-actions">
            <input type="hidden" name="command" value="loginmain">
            <button type="submit" class="btn btn-success btn btn-large btn-block">Log in</button>
        </div>
    </form>


    <form id="recoverform" action="#" class="form-vertical">
        <p class="normal_text">Enter your e-mail address below and we will send you instructions how to recover a
            password.</p>

        <div class="controls">
            <div class="main_input_box">
                <span class="add-on bg_lo"><i class="icon-envelope"></i></span><input type="text"
                                                                                      placeholder="E-mail address"/>
            </div>
        </div>

        <div class="form-actions">
            <span class="pull-left"><a href="#" class="flip-link btn btn-success"
                                       id="to-login">&laquo; Back to login</a></span>
            <span class="pull-right"><a class="btn btn-info">Reecover</a></span>
        </div>
    </form>
</div>

<script src="static/js/jquery.min.js"></script>
<script src="static/js/matrix.login.js"></script>
</body>

</html>
