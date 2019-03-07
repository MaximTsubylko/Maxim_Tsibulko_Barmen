<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="utf-8"/>

<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="language" var="bundle" scope="application"/>

<html>
<head>
    <title>Main page</title>
    <%@include file="/WEB-INF/resources.jsp" %>

</head>
<body>

<!--Header-part-->
<div id="header">
    <h1><a href="dashboard.html">Matrix Admin</a></h1>
</div>
<!--close-Header-part-->

<div id="user-nav" class="navbar">
    <ul class="nav" style="width: auto; margin: 0px;">
        <li class="dropdown" id="profile-messages">
            <a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle">
                <i class="icon icon-user"></i> <span class="normal_text">
                <fmt:message key="main.language" bundle="${bundle}"/></span><b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li><a>
                    <form action="${pageContext.request.contextPath}/barman" method="post">
                        <input type="hidden" name="command" value="main">
                        <input type="hidden" name="change_lang" value="ru">
                        <button type="submit" class="btn btn-info btn btn-block">Ru</button>
                    </form>
                </a>
                </li>
                <li class="divider"></li>
                <li><a>
                    <form action="${pageContext.request.contextPath}/barman" method="post">
                        <input type="hidden" name="command" value="main">
                        <input type="hidden" name="change_lang" value="en">
                        <button type="submit" class="btn btn-info btn btn-block ">En</button>
                    </form>
                </a>
                </li>
                <li class="divider"></li>
            </ul>
        </li>

    </ul>
</div>

<!--sidebar-menu-->
<div id="sidebar"><a href="#" class="visible-phone"><i class="icon icon-tint"></i>
    <fmt:message key="main.name" bundle="${bundle}"/></a>
    <ul>
        <li><a href="?command=show_create_cocktail"><i class="icon icon-home"></i> <span>
            <fmt:message key="main.createcocktail" bundle="${bundle}"/></span></a></li>
        <li><a href="?command=cocktail_list"><i class="icon icon-signal"></i> <span>
            <fmt:message key="main.cocktaillist" bundle="${bundle}"/>
        </span></a></li>
        <li class="submenu"><a href="#"><i class="icon icon-file"></i> <span>
                     <fmt:message key="main.usermenu" bundle="${bundle}"/>
        </span></a>
            <ul>
                <li><a href="barman?command=show_profile">
                    <fmt:message key="main.profile" bundle="${bundle}"/>
                </a></li>
                <li><a href="barman?command=show_edit_page">
                    <fmt:message key="main.useredit" bundle="${bundle}"/>
                </a></li>
                <li><a href="barman?command=logout">
                    <fmt:message key="main.logout" bundle="${bundle}"/>
                </a>
                </li>

            </ul>
        </li>

    </ul>
</div>

<!--main-container-part-->
<div id="content">
    <jsp:include page="include/${viewName}.jsp"/>
</div>

</div>
<!--End-main-container-part-->
<!--Footer-part-->
<!--Footer-part-->
<div class="row-fluid">
    <div id="footer" class="span12"> 2019 &copy; Maxim Tsibulko by Epam courses.
    </div>
</div>
<!--end-Footer-part-->
<!--end-Footer-part-->

<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.ui.custom.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/matrix.js"></script>
</body>
</html>
