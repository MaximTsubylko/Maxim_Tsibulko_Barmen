<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <div id="breadcrumb"><a href="?command=main" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>
        <fmt:message key="link.home" bundle="${bundle}"/></a><a href="#" class="current"><fmt:message key="link.profile"
                                                                                                      bundle="${bundle}"/></a>
    </div>
</div>


<div class="container container-fluid">
    <div class="row-fluid">
        <div class="span2">
            <img src="${pageContext.request.contextPath}/static/img/usericon.png">
            <div class="pagination-centered">
                <h1><c:out value="${customer.login}"/></h1>
            </div>
        </div>

        <div class="span4">
            <div class="widget-content nopadding">
                <h1>
                    <fmt:message key="user.information" bundle="${bundle}"/>
                </h1>
                <p><h5><fmt:message key="user.firstname" bundle="${bundle}"/> : <c:out
                    value="${customer.first_name}"/></h5></p>
                <p><h5><fmt:message key="user.lastname" bundle="${bundle}"/> : <c:out
                    value="${customer.second_name}"/></h5></p>
                <p><h5>E-mail : <c:out value="${customer.email}"/></h5></p>
                <p><h5><fmt:message key="user.role" bundle="${bundle}"/> :
                <c:choose>
                    <c:when test="${customer.role_id == 3}">
                        <fmt:message key="user.role.administrator" bundle="${bundle}"/>
                    </c:when>
                    <c:when test="${customer.role_id == 2}">
                        <fmt:message key="user.role.barman" bundle="${bundle}"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="user.role.customer" bundle="${bundle}"/>
                    </c:otherwise>
                </c:choose>
            </h5></p>
                <p><h5><fmt:message key="user.state" bundle="${bundle}"/> :
                <c:choose>
                    <c:when test="${customer.state == 3}">
                        <fmt:message key="user.state.whait" bundle="${bundle}"/>
                    </c:when>
                    <c:when test="${customer.state == 2}">
                        <fmt:message key="user.state.blocked" bundle="${bundle}"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="user.state.active" bundle="${bundle}"/>
                    </c:otherwise>
                </c:choose>
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

                            <c:forEach items="${ProfileUser.cocktails}" var="cocktail">
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
            </div>
        </div>

        <div class="row-fluid">
            <div class="span6 pagination-centered">
                <div class="widget-box">
                    <div class="widget-title bg_ly" data-toggle="collapse" href="#collapseG2"><span class="icon"><i
                            class="icon-chevron-down"></i></span>
                        <h5>
                            <fmt:message key="user.comments" bundle="${bundle}"/>
                        </h5>
                    </div>
                    <div class="widget-content nopadding in collapse" id="collapseG2" style="height: auto;">
                        <ul class="recent-posts">
                            <li>
                                <div class="article-post"><span class="user-info">
                                    <fmt:message key="user.comments.by" bundle="${bundle}"/>: john Deo</span>
                                    <p><a href="#">This is a much longer one that will go on for a few lines.It has
                                        multiple paragraphs and is full of waffle to pad out the comment.</a></p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="span5">
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"> <i class="icon-align-justify"></i> </span>
                        <h5>
                            <fmt:message key="user.comments.add" bundle="${bundle}"/>
                        </h5>
                    </div>
                    <div class="widget-content nopadding">
                        <form action="#" method="get" class="form-horizontal">
                            <div class="control-group">
                                <label class="control-label">
                                    <fmt:message key="user.comments.title" bundle="${bundle}"/>:</label>
                                <div class="controls">
                                    <input type="text" class="span11" placeholder="<fmt:message key="user.comments.title" bundle="${bundle}"/>">
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label">
                                    <fmt:message key="user.comments.message" bundle="${bundle}"/>:
                                </label>
                                <div class="controls">
                                    <textarea class="span11"></textarea>
                                </div>
                            </div>
                            <div class="form-actions">
                                <button type="submit" class="btn btn-success">
                                    <fmt:message key="edit.savebutton" bundle="${bundle}"/>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
