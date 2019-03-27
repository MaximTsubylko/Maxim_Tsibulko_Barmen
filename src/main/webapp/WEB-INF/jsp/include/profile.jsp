<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="utf-8"/>

<c:set var="CurentUser" scope="application" value="${sessionScope.get('user')}"/>
<c:set var="ProfileUser" scope="application" value="${requestScope.get('customer')}"/>


<c:choose>
    <c:when test="${not empty requestScope.get('lang')}">
        <fmt:setLocale value="${requestScope.get('lang')}"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${cookie['locale'].value}"/>
    </c:otherwise>
</c:choose><fmt:setBundle basename="language" var="bundle" scope="application"/>

<div id="content-header">
    <div id="breadcrumb"><a href="?command=main" title="<fmt:message key="link.gotohome" bundle="${bundle}"/>"
                            class="tip-bottom"><i class="icon-home"></i>
        <fmt:message key="link.home" bundle="${bundle}"/></a><a href="#" class="current"><fmt:message key="link.profile"
                                                                                                      bundle="${bundle}"/></a>
    </div>
</div>


<div class="container container-fluid">
    <div class="row-fluid">
        <div class="span2">
            <img src="${pageContext.request.contextPath}/static/img/usericon.png">
        </div>

        <div class="span4">
            <div class="widget-content nopadding">
                <h1>
                    <fmt:message key="user.information" bundle="${bundle}"/>
                </h1>
                <hr>
                <p>
                <h3 style="color: black"><c:out value="${customer.login}"/></h3></p>
                <p><h5><fmt:message key="user.firstname" bundle="${bundle}"/> : <c:out
                    value="${customer.first_name}"/></h5></p>
                <p><h5><fmt:message key="user.lastname" bundle="${bundle}"/> : <c:out
                    value="${customer.second_name}"/></h5></p>
                <p><h5>E-mail : <c:out value="${customer.email}"/></h5></p>
                <p><h5><fmt:message key="user.role" bundle="${bundle}"/> :
                <tags:role_writer id="${customer.role_id}"/>
            </h5></p>
                <p><h5><fmt:message key="user.state" bundle="${bundle}"/> :
                <tags:state_write id="${customer.state}"/>
            </h5></p>
            </div>
        </div>


        <div class="span5">
            <div class="widget-content nopadding">
                <h2 class="pagination-centered">
                    <fmt:message key="user.cocktails" bundle="${bundle}"/>
                </h2>
                <hr>
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"> <i class="icon-th"></i> </span>
                        <h5>
                            <fmt:message key="cocktail.cocktails" bundle="${bundle}"/>
                        </h5>
                    </div>
                    <div class="widget-content nopadding">
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th><fmt:message key="cocktail.name" bundle="${bundle}"/></th>
                                <th><fmt:message key="cocktail.descriptoin" bundle="${bundle}"/></th>
                                <th><fmt:message key="cocktail.price" bundle="${bundle}"/></th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach items="${customer.cocktails}" var="cocktail">
                                <tr>
                                    <td>
                                        <a href="?command=show_cocktail_details&id=${cocktail.id}">
                                                <c:out value="${cocktail.name}"/>
                                    </td>
                                    <td><c:out value="${cocktail.description}"/></td>
                                    <td><c:out value="${cocktail.price}"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <div class="row-fluid">
            <div style="margin-left: 60vh;
                        margin-top: 1vh" class="span4 pagination-centered">
                <c:choose>
                    <c:when test="${CurentUser.role_id == 3 || CurentUser.id == ProfileUser.id }">
                        <form action="${pageContext.request.contextPath}/barman" method="post">
                            <a href="?command=delete_customer&id=${ProfileUser.id}"
                               class="btn btn-danger btn btn-block ">
                                <fmt:message key="cocktail.delete" bundle="${bundle}"/>
                            </a>
                        </form>
                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${CurentUser.role_id == 3 && ProfileUser.state == 1 && CurentUser.id != ProfileUser.id}">
                        <form action="${pageContext.request.contextPath}/barman" method="post">
                            <a href="?command=change_state&id=${ProfileUser.id}&state=blocked"
                               class="btn btn-danger btn btn-block ">
                                <fmt:message key="customer.ban" bundle="${bundle}"/>
                            </a>
                        </form>
                    </c:when>
                    <c:when test="${CurentUser.role_id == 3 && ProfileUser.state == 2 && CurentUser.id != ProfileUser.id}">
                        <form action="${pageContext.request.contextPath}/barman" method="post">
                            <a href="?command=change_state&id=${ProfileUser.id}&state=active"
                               class="btn btn-success btn btn-block ">
                                <fmt:message key="customer.unban" bundle="${bundle}"/>
                            </a>
                        </form>
                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${CurentUser.role_id == 3 && ProfileUser.role_id == 1 && CurentUser.id != ProfileUser.id}">
                        <form action="${pageContext.request.contextPath}/barman" method="post">
                            <a href="?command=change_role&id=${ProfileUser.id}&role=2"
                               class="btn btn-success btn btn-block ">
                                <fmt:message key="customer.tobarman" bundle="${bundle}"/>
                            </a>
                        </form>
                    </c:when>
                    <c:when test="${CurentUser.role_id == 3 && ProfileUser.role_id == 2 && CurentUser.id != ProfileUser.id}">
                        <form action="${pageContext.request.contextPath}/barman" method="post">
                            <a href="?command=change_role&id=${ProfileUser.id}&role=1"
                               class="btn btn-danger btn btn-block ">
                                <fmt:message key="customer.touser" bundle="${bundle}"/>
                            </a>
                        </form>
                    </c:when>
                </c:choose>
            </div>
        </div>


        <div class="row-fluid">
            <div class="span6">
                <div class="widget-box">
                    <div class="widget-title bg_ly" data-toggle="collapse" href="#collapseG2"><span class="icon"><i
                            class="icon-chevron-down"></i></span>
                        <h5>
                            <fmt:message key="user.comments" bundle="${bundle}"/>
                        </h5>
                    </div>
                    <div class="widget-content nopadding in collapse" id="collapseG2" style="height: auto;">
                        <ul class="recent-posts">
                            <c:forEach items="${feedback}" var="feedback">
                                <li>
                                    <div class="article-post">
                                        <h5><a href="#"><c:out value="${feedback.title}"/></a></h5>
                                        <p><a href="#"><c:out value="${feedback.comment}"/></a></p>
                                    </div>
                                </li>
                            </c:forEach>

                        </ul>
                    </div>
                </div>
            </div>
            <c:if test="${CurentUser.id != ProfileUser.id}">
                <div class="span5">
                    <div class="widget-box">
                        <div class="widget-title bg_ly" data-toggle="collapse" href="#collapseG3"><span class="icon"><i
                                class="icon-chevron-down"></i></span>
                            <h5>
                                <fmt:message key="user.comments.add" bundle="${bundle}"/>
                            </h5>
                        </div>
                        <div class="widget-content nopadding in collapse" id="collapseG3" style="height: auto;">
                            <form action="${pageContext.request.contextPath}/barman" method="post"
                                  class="form-horizontal">
                                <div class="control-group">
                                    <label class="control-label">
                                        <fmt:message key="user.comments.title" bundle="${bundle}"/>:</label>
                                    <div class="controls">
                                        <input name="title" type="text" class="span11"
                                               placeholder="<fmt:message key="user.comments.title" bundle="${bundle}"/>">
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label">Mark</label>
                                    <div class="controls">
                                        <div data-toggle="buttons-radio" class="btn-group">
                                            <button class="btn btn-info" type="button" onclick="m1()">
                                                1
                                            </button>
                                            <button style="margin-left: 1vh" class="btn btn-info" type="button"
                                                    onclick="m2()">
                                                2
                                            </button>
                                            <button style="margin-left: 1vh" class="btn btn-info" type="button"
                                                    onclick="m3()">
                                                3
                                            </button>
                                            <button style="margin-left: 1vh" class="btn btn-info" type="button"
                                                    onclick="m4()">
                                                4
                                            </button>
                                            <button style="margin-left: 1vh" class="btn btn-info" type="button"
                                                    onclick="m5()">
                                                5
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">
                                        <fmt:message key="user.comments.message" bundle="${bundle}"/>:
                                    </label>
                                    <div class="controls">
                                        <textarea name="comment" class="span11"></textarea>
                                    </div>
                                </div>
                                <div class="form-actions">
                                    <button type="submit" class="btn btn-success" onclick="setMark()">
                                        <input type="hidden" name="from" value="${CurentUser.id}">
                                        <input type="hidden" name="to" value="${ProfileUser.id}">
                                        <input type="hidden" name="id" value="${ProfileUser.id}">
                                        <input type="hidden" id="mark" name="mark" value="">
                                        <input type="hidden" name="command" value="add_user_feedback">
                                        <fmt:message key="edit.savebutton" bundle="${bundle}"/>
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>

<script>
    var mark = 0;

    function m1() {
        mark = 1;
    }

    function m2() {
        mark = 2;
    }

    function m3() {
        mark = 3;
    }

    function m4() {
        mark = 4;
    }

    function m5() {
        mark = 5;
    }

    function setMark() {
        document.getElementById("mark").value = mark;
    }
</script>