<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
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
            <fmt:message key="main.userlist" bundle="${bundle}"/>
        </a>
    </div>
    <h1 class="pagination-centered">
        <fmt:message key="main.userlist" bundle="${bundle}"/>
    </h1>

</div>
<div class="container-fluid">
    <hr>
    <div class="row-fluid">
        <div class="span12">


            <div class="widget-content pagination-centered ">

                <div class="widget-box">
                    <div class="widget-title"><span class="icon"> <i class="icon-th"></i> </span>
                        <h5><fmt:message key="user.users" bundle="${bundle}"/></h5>
                    </div>
                    <div class="widget-content nopadding">
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th>
                                    <fmt:message key="user.login" bundle="${bundle}"/>
                                </th>
                                <th>
                                    <c:out value="E-mail"/>
                                </th>
                                <th>
                                    <fmt:message key="user.role" bundle="${bundle}"/>
                                </th>
                                <th>
                                    <fmt:message key="user.state" bundle="${bundle}"/>
                                </th>
                                <th>
                                    <fmt:message key="cocktail.avermarck" bundle="${bundle}"/>
                                </th>
                                <th>
                                    <fmt:message key="date" bundle="${bundle}"/>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${customerlist}" var="customer">
                                <tr>
                                    <td>
                                        <a href="?command=show_profile&id=${customer.id}">
                                                <c:out value="${customer.login}"/>
                                    </td>
                                    <td><c:out value="${customer.email}"/></td>
                                    <td>
                                        <tags:role_writer id="${customer.role_id}"/>
                                    </td>
                                    <td>
                                        <tags:state_write id="${customer.state}"/>
                                    </td>
                                    <td><c:out value="${customer.averageMark}"/></td>
                                    <td><c:out value="${customer.registr_date}"/></td>
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
