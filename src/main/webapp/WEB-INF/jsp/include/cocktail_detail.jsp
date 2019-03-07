<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="utf-8"/>

<fmt:setLocale value="${cookie['locale'].value}"/>
<fmt:setBundle basename="language" var="bundle" scope="application"/>

<div id="content-header">
    <div id="breadcrumb"><a href="?command=main" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>
        <fmt:message key="link.home" bundle="${bundle}"/>
    </a><a href="#" class="current"><c:out value="${cocktail.name}"/></a></div>
</div>


<div class="container con container-fluid">
    <div class="row-fluid">
        <div class="span3">
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


        <div class="span4">
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
</div>
