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
    <form action="${pageContext.request.contextPath}/barman" method="post">
        <input type="hidden" name="command" value="show_login_page">
        <input type="hidden" name="change_lang" value="ru">
        <button type="submit" class="btn btn-success btn btn-block">Ru</button>
    </form>


    <form action="${pageContext.request.contextPath}/barman" method="post">
        <input type="hidden" name="command" value="show_login_page">
        <input type="hidden" name="change_lang" value="en">
        <button type="submit" class="btn btn-success btn btn-block">En</button>
    </form>

    <form id="loginform" class="form-vertical" action="${pageContext.request.contextPath}/barman" method="post">

        <div class="control-group normal_text"><h3><img src="${pageContext.request.contextPath}/static/img/logo.png"
                                                        alt="Logo"/></h3></div>
        <div class="control-group">
            <div class="controls">
                <div class="main_input_box">
                    <span class="add-on bg_lg"><i class="icon-user"> </i></span><input required="required" type="text"
                                                                                       id="loginField"
                                                                                       name="login"
                                                                                       placeholder="Username"/>
                </div>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <div class="main_input_box">
                    <span class="add-on bg_ly"><i class="icon-lock"></i></span><input required="required"
                                                                                      type="password" name="password"
                                                                                      placeholder="Password"/>
                </div>
            </div>
        </div>
        <div class="form-actions">
            <div class="center-pill">
                <div class="row-fluid">
                    <div class="span4">
                        <input type="hidden" name="command" value="try_login">
                        <button type="submit" class="btn btn-success btn btn-block"
                                onclick="return validLogin(document.getElementById('loginform'))">
                            <fmt:message key="index.startbutton" bundle="${bundle}"/>
                        </button>


                    </div>
                    <div class="span4">
                        <a href="#" class="flip-link btn btn-danger" id="to-recover">
                            <fmt:message key="login.forgotpassword" bundle="${bundle}"/></a>
                    </div>

                    <div class="span4">
                        <a href="#" class="flip-link btn btn-warning" id="to-registration">
                            <fmt:message key="login.registrationbutton" bundle="${bundle}"/></a>
                    </div>
                </div>
            </div>
        </div>
    </form>


    <form id="recoverform" action="${pageContext.request.contextPath}/barman" method="post" class="form-vertical">
        <p class="normal_text">Enter your e-mail address below and we will send you instructions how to recover a
            password.</p>

        <div class="controls">
            <div class="main_input_box">
                <span class="add-on bg_lo"><i class="icon-envelope"></i></span><input required="required" type="email"
                                                                                      name="email"
                                                                                      placeholder="E-mail address"/>
            </div>
        </div>

        <div class="form-actions">
            <div class="center-pill">
                <div class="row-fluid">
                    <div class="pull-right span4">
                        <input type="hidden" name="command" value="send_recovery_message">
                        <button type="submit" class="btn btn-info btn btn-block">Recovery</button>
                    </div>
                    <div class="pull-left span4">
                        <a href="#" class="flip-link btn btn-success"
                           id="to-login">&laquo; Back to login</a>
                    </div>
                </div>
            </div>
        </div>
    </form>


    <form id="registrationform" action="${pageContext.request.contextPath}/barman" method="post" class="form-vertical">

        <p class="normal_text">Fil this form and let`s start!</p>
        <div class="control-group">
            <div class="controls">
                <div class="main_input_box">
                    <span class="add-on bg_lg"><i class="icon-user"> </i></span><input required="required" type="text"
                                                                                       id="login"
                                                                                       name="login"
                                                                                       placeholder="Username"/>
                </div>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <div class="main_input_box">
                    <span class="add-on bg_db"><i class="icon-envelope-alt"> </i></span><input required="required"
                                                                                               id="email"
                                                                                               type="email" name="email"
                                                                                               placeholder="E-mail"/>
                </div>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <div class="main_input_box">
                    <span class="add-on bg_ly"><i class="icon-key"></i></span><input required="required" type="password"
                                                                                     id="password"
                                                                                     name="password"
                                                                                     placeholder="Password"/>
                </div>
            </div>
        </div>

        <div class="control-group">
            <div class="controls">
                <div class="main_input_box">
                    <span class="add-on bg_lr"><i class="icon-key"></i></span><input required="required" type="password"
                                                                                     name="confirm_password"
                                                                                     placeholder="Confirm password"/>
                </div>
            </div>
        </div>

        <div class="form-actions">
            <div class="center-pill">
                <div class="row-fluid">
                    <div class="pull-right span4">
                        <input type="hidden" name="command" value="registration">
                        <button type="submit" class="btn btn-success btn btn-block"
                                onclick="return validRegistration(document.getElementById('registrationform'))">Register
                        </button>
                    </div>
                    <div class="pull-left span4">
                        <a href="#" class="flip-link btn btn-success"
                           id="registrationform-login">&laquo; Back to login</a></div>
                </div>
            </div>
        </div>
    </form>


</div>

<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/matrix.login.js"></script>
<script src="${pageContext.request.contextPath}/static/js/login_and_reg_validation.js"></script>
<script src="https://unpkg.com/sweetalert2@7.8.2/dist/sweetalert2.all.js"></script>

</body>

</html>
