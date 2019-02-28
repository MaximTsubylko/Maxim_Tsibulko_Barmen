
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

<!--sidebar-menu-->
<div id="sidebar"><a href="#" class="visible-phone"><i class="icon icon-tint"></i>Barmen helper</a>
    <ul>
        <li><a href="?command=show_create_cocktail"><i class="icon icon-home"></i> <span>Create cocktail</span></a></li>
        <li><a href="?command=cocktail_list"><i class="icon icon-signal"></i> <span>Cocktail list</span></a></li>
        <li class="submenu"> <a href="#"><i class="icon icon-file"></i> <span>User menu</span></a>
        <ul>
            <li><a href="barman?command=show_profile">My profile</a></li>
            <li><a href="#">Edit</a></li>
            <li><a href="barman?command=logout">Log out</a>
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

<script src="static/js/jquery.min.js"></script>
<script src="static/js/jquery.ui.custom.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/matrix.js"></script>
</body>
</html>
