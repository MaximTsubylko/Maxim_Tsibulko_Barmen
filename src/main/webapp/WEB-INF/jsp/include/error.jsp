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
</c:choose>
<fmt:setBundle basename="language" var="bundle" scope="application"/>

<div id="content-header">

    <div id="breadcrumb"><a href="?command=main" title="<fmt:message key="link.gotohome" bundle="${bundle}"/>"
                            class="tip-bottom"><i class="icon-home"></i>
        <fmt:message key="link.home" bundle="${bundle}"/>
    </a><a href="#" class="current"><fmt:message key="error" bundle="${bundle}"/></a></div>
    <h1><fmt:message key="error" bundle="${bundle}"/> </h1>
</div>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="widget-box">
                <div class="widget-title"><span class="icon"> <i class="icon-info-sign"></i> </span>
                    <h5><fmt:message key="cocktail.descriptoin" bundle="${bundle}"/>
                    </h5>
                </div>
                <div class="widget-content">
                    <div class="error_ex">
                        <h1 id="title"></h1>

                        <h3><p id="message"></p></h3>
                    </div>
                </div>
            </div>
            <%@include file="/WEB-INF/localizedTags.jsp" %>

        </div>
    </div>
</div>
<script>
    $(document).ready(function f() {
        document.getElementById('title').innerHTML = document.getElementById("e" + String(${code}) + "_title").textContent;
        document.getElementById('message').innerHTML = document.getElementById("e" + String(${code}) + "_message").textContent;

    });
</script>
