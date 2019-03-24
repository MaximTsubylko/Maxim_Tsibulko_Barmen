<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="utf-8"/>
<c:set var="CurentUser" scope="application" value="${sessionScope.get('user')}"/>

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
    <title><fmt:message key="main.page" bundle="${bundle}"/></title>
    <%@include file="/WEB-INF/resources.jsp" %>

    <script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery.ui.custom.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery.uniform.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/select2.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/matrix.tables.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/matrix.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap-colorpicker.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap-datepicker.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/masked.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery.peity.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/wysihtml5-0.3.0.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap-wysihtml5.js"></script>
    <script src="https://unpkg.com/sweetalert2@7.8.2/dist/sweetalert2.all.js"></script>

    <script>
        $('.textarea_editor').wysihtml5();
    </script>


</head>
<body>

<c:if test="${not empty requestScope.get('error')}">
    <script>
        show(String(${requestScope.get('error')}));
    </script>
</c:if>

<!--Header-part-->
<div id="header">
    <h1><a href="dashboard.html">Barman helper</a></h1>
</div>
<!--close-Header-part-->

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
                </ul>
            </li>
        </ul>
    </div>
</div>


<!--sidebar-menu-->
<div id="sidebar"><a href="#" class="visible-phone"><i class="icon icon-tint"></i>
    <fmt:message key="main.name" bundle="${bundle}"/></a>
    <ul>
        <li><a href="?command=show_customer_list"><i class="icon icon-list"></i> <span>
            <fmt:message key="main.userlist" bundle="${bundle}"/>
        </span></a></li>
        <li><a href="?command=show_create_cocktail"><i class="icon icon-plus-sign"></i> <span>
            <fmt:message key="main.createcocktail" bundle="${bundle}"/></span></a></li>

        <li><a href="?command=cocktail_list"><i class="icon icon-list"></i> <span>
            <fmt:message key="main.cocktaillist" bundle="${bundle}"/>
        </span></a></li>

        <c:choose>
            <c:when test="${CurentUser.role_id == 3 || CurentUser.id == 2}">
                <li class="submenu"><a href="#"><i class="icon  icon-glass"></i> <span>
                     <fmt:message key="main.barman" bundle="${bundle}"/>
        </span></a>
                    <ul>
                        <li><a href="barman?command=show_ingredient_list">
                            <fmt:message key="main.ingredientlist" bundle="${bundle}"/>
                        </a></li>
                        <li><a href="barman?command=show_create_ingredient">
                            <fmt:message key="main.addingredient" bundle="${bundle}"/>
                        </a></li>

                    </ul>
                </li>
            </c:when>
        </c:choose>

        <li class="submenu"><a href="#"><i class="icon icon-user"></i> <span>
                     <fmt:message key="main.usermenu" bundle="${bundle}"/>
        </span></a>
            <ul>
                <li><a href="barman?command=show_profile&id=${sessionScope.get("user").id}">
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

<!--End-main-container-part-->
<!--Footer-part-->
<!--Footer-part-->
<div class="row-fluid">
    <div id="footer" class="span12"><fmt:message key="footer" bundle="${bundle}"/>
    </div>
</div>
<!--end-Footer-part-->
<!--end-Footer-part-->

</body>
</html>
