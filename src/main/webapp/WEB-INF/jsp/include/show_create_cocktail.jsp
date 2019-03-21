<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>

<c:choose>
    <c:when test="${not empty requestScope.get('lang')}">
        <fmt:setLocale value="${requestScope.get('lang')}"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${cookie['locale'].value}"/>
    </c:otherwise>
</c:choose><fmt:setBundle basename="language" var="bundle" scope="application"/>

<head>
    <%@include file="/WEB-INF/resources.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/colorpicker.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/datepicker.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/uniform.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/select2.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/matrix-media.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/js/create_cocktai_validation.js" />



</head>
<fmt:requestEncoding value="utf-8"/>

<div id="content-header">
    <div id="breadcrumb"><a href="?command=main" title="<fmt:message key="link.gotohome" bundle="${bundle}"/>" class="tip-bottom"><i class="icon-home"></i>
        <fmt:message key="link.home" bundle="${bundle}"/></a><a href="#" class="current"><fmt:message
            key="link.createcocktil" bundle="${bundle}"/></a></div>
    <h1>
        <fmt:message key="cocktail.createnew" bundle="${bundle}"/>
    </h1>
</div>
<div class="container-fluid">
    <div class="row-fluid">
        <div style="margin-left: 45vh" class="span6">


            <div class="widget-content nopadding">
                <div class="widget-box">


                    <div class="widget-title"><span class="icon"> <i class="icon-align-justify"></i> </span>
                        <h5><fmt:message key="fill" bundle="${bundle}"/></h5>
                    </div>
                    <form action="${pageContext.request.contextPath}/barman" method="post" class="form-horizontal"
                          accept-charset="UTF-8">
                        <div class="control-group">
                            <label class="control-label"><fmt:message key="cocktail.name" bundle="${bundle}"/> :</label>
                            <div class="controls">
                                <input type="text" class="span11"
                                       placeholder="<fmt:message key="cocktail.name" bundle="${bundle}"/>"
                                       name="name">
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label"><fmt:message key="cocktail.descriptoin" bundle="${bundle}"/>
                                :</label>
                            <div class="controls">
                                <input type="text" class="span11"
                                       placeholder="<fmt:message key="cocktail.descriptoin" bundle="${bundle}"/>"
                                       id="description"
                                       name="description">
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label"><fmt:message key="cocktail.price" bundle="${bundle}"/>
                                :</label>
                            <div class="controls">
                                <input type="text" class="span11"
                                       placeholder="<fmt:message key="cocktail.price" bundle="${bundle}"/>"
                                       id="price"
                                       name="price">
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label"><fmt:message key="ingredient.ingredients" bundle="${bundle}"/></label>
                            <div class="controls">
                                <select name="ingredients" multiple="" required="required" style="display: none;">
                                    <c:forEach items="${ingredientList}" var="ingredient">
                                        <option>
                                            <c:out value="${ingredient.name}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <input type="hidden" name="command" value="register_cocktail" >
                        <button type="submit" class="btn btn-success btn btn-large btn-block" onclick="validateCocktailData()"><fmt:message
                                key="button.create" bundle="${bundle}"/></button>
                    </form>
                </div>

            </div>
        </div>
    </div>

</div>




