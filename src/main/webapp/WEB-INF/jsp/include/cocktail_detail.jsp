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
</c:choose>
<fmt:setBundle basename="language" var="bundle" scope="application"/>

<div id="content-header">
    <div id="breadcrumb"><a href="?command=main" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>
        <fmt:message key="link.home" bundle="${bundle}"/>
    </a><a href="#" class="current"><c:out value="${cocktail.name}"/></a></div>
</div>


<div class="container container-fluid">
    <div class="row-fluid">
        <div class="span2">
            <img src="${pageContext.request.contextPath}/static/img/cocktailIcon.jpg">
            <div class="pagination-centered">
                <h3><c:out value="${cocktail.name}"/></h3>
            </div>
        </div>

        <div class="span4">
            <div class="widget-content nopadding">
                <h2 class="pagination-centered">
                    <fmt:message key="cocktail.information" bundle="${bundle}"/>
                </h2>
                <hr>
                <p><h5><fmt:message key="cocktail.descriptoin" bundle="${bundle}"/>
                : <c:out value="${cocktail.description}"/></h5></p>
                <p><h5>
                <fmt:message key="cocktail.price" bundle="${bundle}"/>
                : <c:out value="${cocktail.price}"/> $</h5></p>
            </div>
        </div>


        <div class="span5">
            <div class="widget-content nopadding">
                <h2 class="pagination-centered">
                    <fmt:message key="cocktail.ingredient" bundle="${bundle}"/>
                </h2>
                <hr>
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"> <i class="icon-th"></i> </span>
                        <h5><fmt:message key="ingredient.ingredients" bundle="${bundle}"/>
                        </h5>
                    </div>
                    <div class="widget-content nopadding">
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th>
                                    <fmt:message key="cocktail.name" bundle="${bundle}"/>
                                </th>
                                <th>
                                    <fmt:message key="cocktail.descriptoin" bundle="${bundle}"/>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${ingredients}" var="ingredients">
                                <tr>
                                    <td><c:out value="${ingredients.name}"/></td>
                                    <td><c:out value="${ingredients.description}"/></td>

                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row-fluid">
        <div class="span3 pagination-centered">
            <c:choose>
                <c:when test="${CurentUser.role_id == 3 || CurentUser.id == ProfileUser.id }">
                        <form action="${pageContext.request.contextPath}/barman" method="post">
                            <a href="?command=show_update_cocktail_page&id=${cocktail.id}"
                               class="btn btn-info btn btn-block ">
                                <fmt:message key="cocktail.edit" bundle="${bundle}"/>
                            </a>
                        </form>

                        <form action="${pageContext.request.contextPath}/barman" method="post">
                            <a href="?command=delete_cocktail&cocktailId=${cocktail.id}"
                               class="btn btn-danger btn btn-block ">
                                <fmt:message key="cocktail.delete" bundle="${bundle}"/>
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

        <div class="span5">
            <div class="widget-box">
                <div class="widget-title bg_ly" data-toggle="collapse" href="#collapseG3"><span class="icon"><i
                        class="icon-chevron-down"></i></span>
                    <h5>
                        <fmt:message key="user.comments.add" bundle="${bundle}"/>
                    </h5>
                </div>
                <div class="widget-content nopadding in collapse" id="collapseG3" style="height: auto;">
                    <form action="${pageContext.request.contextPath}/barman" method="post" class="form-horizontal">
                        <div class="control-group">
                            <label class="control-label">
                                <fmt:message key="user.comments.title" bundle="${bundle}"/>:</label>
                            <div class="controls">
                                <input type="text" class="span11" name="title"
                                       placeholder="<fmt:message key="user.comments.title" bundle="${bundle}"/>">
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">Mark</label>
                            <div class="controls">
                                <div data-toggle="buttons-radio" class="btn-group">
                                    <button class="btn btn-info" type="button">
                                        <input type="hidden" name="mark" value="1">
                                        1</button>
                                    <button style="margin-left: 1vh" class="btn btn-info" type="button">
                                        <input type="hidden" name="mark" value="2">
                                        2</button>
                                    <button style="margin-left: 1vh" class="btn btn-info" type="button">
                                        <input type="hidden" name="mark" value="3">
                                        3</button>
                                    <button style="margin-left: 1vh" class="btn btn-info" type="button">
                                        <input type="hidden" name="mark" value="4">
                                        4</button>
                                    <button style="margin-left: 1vh" class="btn btn-info" type="button">
                                        <input type="hidden" name="mark" value="5">
                                        5</button>
                                </div>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">
                                <fmt:message key="user.comments.message" bundle="${bundle}"/>:
                            </label>
                            <div class="controls">
                                <textarea class="span11" name="comment"></textarea>
                            </div>
                        </div>
                        <div class="form-actions">
                            <button type="submit" class="btn btn-success">
                                <input type="hidden" name="from" value="${CurentUser.id}">
                                <input type="hidden" name="to" value="${cocktail.id}">
                                <input type="hidden" name="id" value="${cocktail.id}">
                                <input type="hidden" name="command" value="add_cocktail_feedback">
                                <fmt:message key="edit.savebutton" bundle="${bundle}"/>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
