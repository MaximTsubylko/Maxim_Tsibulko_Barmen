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

</head>
<fmt:requestEncoding value="utf-8"/>

<div id="content-header">
    <div id="breadcrumb"><a href="?command=main" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>
        <fmt:message key="link.home" bundle="${bundle}"/></a><a href="#" class="current"><fmt:message key="link.createcocktil" bundle="${bundle}"/></a></div>
    <h1>
        <fmt:message key="cocktail.createnew" bundle="${bundle}"/>
    </h1>
</div>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span6">


            <div class="widget-content nopadding">
                <div class="widget-box">


                    <div class="widget-title"><span class="icon"> <i class="icon-align-justify"></i> </span>
                        <h5><fmt:message key="fill" bundle="${bundle}"/></h5>
                    </div>
                    <form action="${pageContext.request.contextPath}/barman" method="post" class="form-horizontal" accept-charset="UTF-8">
                        <div class="control-group">
                            <label class="control-label"><fmt:message key="cocktail.name" bundle="${bundle}"/> :</label>
                            <div class="controls">
                                <input  type="text" class="span11" placeholder="<fmt:message key="cocktail.name" bundle="${bundle}"/>"
                                       name="name">
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label"><fmt:message key="cocktail.descriptoin" bundle="${bundle}"/> :</label>
                            <div class="controls">
                                <input type="text" class="span11" placeholder="<fmt:message key="cocktail.descriptoin" bundle="${bundle}"/>"
                                       name="description">
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label"><fmt:message key="cocktail.price" bundle="${bundle}"/> :</label>
                            <div class="controls">
                                <input type="text" class="span11" placeholder="<fmt:message key="cocktail.price" bundle="${bundle}"/>"
                                       name="price">
                            </div>
                        </div>
                        <input type="hidden" name="command" value="register_cocktail">
                        <button type="submit" class="btn btn-success btn btn-large btn-block"><fmt:message key="button.create" bundle="${bundle}"/></button>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>


