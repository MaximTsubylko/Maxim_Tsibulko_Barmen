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
    <title><fmt:message key="login.login" bundle="${bundle}"/></title>
    <%@include file="/WEB-INF/resources.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/matrix-login.css"/>
    <script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/matrix.login.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/registration_validation.js"></script>
    <ul class="typeahead dropdown-menu"></ul>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/matrix.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery.ui.custom.js"></script>

    <script src="https://unpkg.com/sweetalert2@7.8.2/dist/sweetalert2.all.js"></script>
    <%@include file="/WEB-INF/localizedTags.jsp" %>


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
                            <input type="hidden" name="command" value="show_login_page">
                            <input type="hidden" name="change_lang" value="ru">
                            <button type="submit" class="btn btn-info btn btn-block">Ru</button>
                        </form>
                    </a>
                    </li>
                    <li class="divider"></li>
                    <li><a>
                        <form action="${pageContext.request.contextPath}/barman" method="post">
                            <input type="hidden" name="command" value="show_login_page">
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

        <div class="control-group normal_text"><h3><img src="${pageContext.request.contextPath}/static/img/PIVO.svg"
                                                        alt="Logo"/></h3></div>
        <div class="control-group">
            <div class="controls">
                <div class="main_input_box">
                    <span style="padding-bottom:8px" class="add-on bg_lg"><i class="icon-user"> </i></span><input
                        required="required" type="text"
                        id="loginField"
                        name="login"
                        placeholder="<fmt:message key="login.login" bundle="${bundle}"/>"/>
                </div>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <div class="main_input_box">
                    <span style="padding-bottom:8px" class="add-on bg_ly"><i class="icon-lock"></i></span><input
                        required="required"
                        type="password" name="password"
                        placeholder="<fmt:message key="user.password" bundle="${bundle}"/>"/>
                </div>
            </div>
        </div>
        <div class="form-actions">
            <div class="center-pill">
                <div class="row-fluid">
                    <div class="span4">
                        <input type="hidden" name="command" value="try_login">
                        <button type="submit" class="btn btn-success btn btn-block">
                            <fmt:message key="index.startbutton" bundle="${bundle}"/>
                        </button>


                    </div>
                    <div class="span4">
                        <a href="#" class="flip-link btn btn-danger" id="to-recover">
                            <fmt:message key="login.forgotpassword" bundle="${bundle}"/></a>
                    </div>

                    <div class="span4">
                        <a href="#" class="flip-link btn btn-warning" id="to-registration">
                            <fmt:message key="button.registration" bundle="${bundle}"/></a>
                    </div>
                </div>
            </div>
        </div>
    </form>


    <form id="recoverform" action="${pageContext.request.contextPath}/barman" method="post" class="form-vertical">
        <p class="normal_text"><fmt:message key="message.recovery" bundle="${bundle}"/></p>

        <div class="controls">
            <div class="main_input_box">
                <span style="padding-bottom:8px" class="add-on bg_lo"><i class="icon-envelope"></i></span><input
                    required="required" type="email"
                    name="email"
                    placeholder="E-mail"/>
            </div>
        </div>

        <div class="form-actions">
            <div class="center-pill">
                <div class="row-fluid">
                    <div class="pull-right span4">
                        <input type="hidden" name="command" value="send_recovery_message">
                        <button type="submit" class="btn btn-info btn btn-block"><fmt:message key="button.recovery"
                                                                                              bundle="${bundle}"/></button>
                    </div>
                    <div class="pull-left span4">
                        <a href="#" class="flip-link btn btn-success"
                           id="to-login"><fmt:message key="button.backtologin" bundle="${bundle}"/></a>
                    </div>
                </div>
            </div>
        </div>
    </form>


    <form id="registrationform" action="${pageContext.request.contextPath}/barman" method="post" class="form-vertical">

        <p class="normal_text"><fmt:message key="message.registration" bundle="${bundle}"/></p>
        <div class="control-group">
            <div class="controls">
                <div class="main_input_box">
                    <span style="padding-bottom:8px" class="add-on bg_lg"><i class="icon-user"> </i></span><input
                        required="required" type="text"
                        id="login"
                        name="login"
                        placeholder="<fmt:message key="login.login" bundle="${bundle}"/>"/>
                </div>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <div class="main_input_box">
                    <span style="padding-bottom:8px" class="add-on bg_db"><i
                            class="icon-envelope-alt"> </i></span><input required="required"
                                                                         id="email"
                                                                         type="email" name="email"
                                                                         placeholder="E-mail"/>
                </div>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <div class="main_input_box">
                    <span style="padding-bottom:8px" class="add-on bg_ly"><i class="icon-key"></i></span><input
                        required="required" type="password"
                        id="password"
                        name="password"
                        placeholder="<fmt:message key="user.password" bundle="${bundle}"/>"/>
                </div>
            </div>
        </div>

        <div class="control-group">
            <div class="controls">
                <div class="main_input_box">
                    <span style="padding-bottom:8px" class="add-on bg_lr"><i class="icon-key"></i></span><input
                        required="required" type="password"
                        name="confirm_password" id="confirm_password"
                        placeholder="<fmt:message key="user.confirmpassword" bundle="${bundle}"/>"/>
                </div>
            </div>
        </div>

        <div class="form-actions">
            <div class="center-pill">
                <div class="row-fluid">
                    <div class="pull-right span4">
                        <input type="hidden" name="command" value="registration">
                        <button type="submit" class="btn btn-success btn btn-block"
                                onclick="return validRegistration()">
                            <fmt:message key="button.registration" bundle="${bundle}"/>
                        </button>
                    </div>
                    <div class="pull-left span4">
                        <a href="#" class="flip-link btn btn-success"
                           id="registrationform-login"><fmt:message key="button.backtologin" bundle="${bundle}"/></a>
                    </div>
                </div>
            </div>
        </div>
    </form>

</div>

</body>

</html>
