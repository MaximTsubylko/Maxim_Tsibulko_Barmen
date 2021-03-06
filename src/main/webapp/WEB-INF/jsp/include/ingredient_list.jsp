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
    <div id="breadcrumb"><a href="?command=main" title="<fmt:message key="link.gotohome" bundle="${bundle}"/>"
                            class="tip-bottom"><i class="icon-home"></i>
        <fmt:message key="link.home" bundle="${bundle}"/>
    </a>
        <a href="#" class="current">
            <fmt:message key="main.ingredientlist" bundle="${bundle}"/>
        </a>
    </div>
    <h1 class="pagination-centered">
        <fmt:message key="main.ingredientlist" bundle="${bundle}"/>
    </h1>

</div>
<div class="container-fluid">
    <hr>
    <div class="row-fluid">
        <div class="span12">


            <div class="widget-content pagination-centered ">

                <div class="widget-box">
                    <div class="widget-title"><span class="icon"> <i class="icon-th"></i> </span>
                        <h5><fmt:message key="ingredient.ingredients" bundle="${bundle}"/></h5>
                    </div>
                    <div class="widget-content nopadding">
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th>
                                    <fmt:message key="ingredient.name" bundle="${bundle}"/>
                                </th>
                                <th>
                                    <fmt:message key="ingredient.descriptoin" bundle="${bundle}"/>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${ingredientList}" var="ingredient">
                                <tr>
                                    <td><a href="?command=show_edit_ingredient&id=${ingredient.id}">
                                            <c:out value="${ingredient.name}"/>
                                    </td>
                                    <td><c:out value="${ingredient.description}"/></td>

                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

