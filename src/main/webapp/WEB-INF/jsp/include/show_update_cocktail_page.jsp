<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="utf-8"/>



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
        <fmt:message key="link.home" bundle="${bundle}"/>
    </a>
        <a href="#" class="current"> <fmt:message key="link.cocktailedit" bundle="${bundle}"/>
        </a>
    </div>
    <h1 class="pagination-centered"><fmt:message key="cocktail.edit.message" bundle="${bundle}"/>

    </h1>
</div>
<div class="container-fluid">
    <hr>
    <div class="row-fluid">
        <div style="margin-left: 45vh" class="span6">
            <div class="widget-box">
                <div class="widget-title"><span class="icon"> <i class="icon-align-justify"></i> </span>
                    <h5>
                        <fmt:message key="cocktail.information" bundle="${bundle}"/>
                    </h5>
                </div>
                <div class="widget-content nopadding">
                    <form action="${pageContext.request.contextPath}/barman" method="post" class="form-horizontal">
                        <div class="control-group">
                            <label class="control-label"> <fmt:message key="user.firstname" bundle="${bundle}"/>
                                :</label>
                            <div class="controls">
                                <input type="text" class="span11"
                                       placeholder="<fmt:message key="cocktail.name" bundle="${bundle}"/>"
                                       value="<c:out value="${cocktail.name}"/>" name="name">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"> <fmt:message key="cocktail.descriptoin" bundle="${bundle}"/>:</label>
                            <div class="controls">
                                <input type="text" class="span11"
                                       placeholder="<fmt:message key="cocktail.descriptoin" bundle="${bundle}"/>"
                                       value="<c:out value="${cocktail.description}"/>" name="description">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"><fmt:message key="cocktail.price" bundle="${bundle}"/>:</label>
                            <div class="controls">
                                <input type="number" class="span11" placeholder="<fmt:message key="cocktail.price" bundle="${bundle}"/>"
                                       value="<c:out value="${cocktail.price}"/>" name="price">
                            </div>
                        </div>

                        <div class="form-actions">
                            <input type="hidden" name="command" value="edit_cocktail">
                            <input type="hidden" name="id" value="${cocktail.id}">
                            <button type="submit" class="btn btn-success"><fmt:message key="edit.savebutton"
                                                                                       bundle="${bundle}"/>
                            </button>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>
