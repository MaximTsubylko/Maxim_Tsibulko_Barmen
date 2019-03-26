<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:directive.attribute name="id" required="true"/>

<c:choose>
    <c:when test="${id == 3}">
        <fmt:message key="user.role.administrator" bundle="${bundle}"/>
    </c:when>
    <c:when test="${id == 2}">
        <fmt:message key="user.role.barman" bundle="${bundle}"/>
    </c:when>
    <c:otherwise>
        <fmt:message key="user.role.customer" bundle="${bundle}"/>
    </c:otherwise>
</c:choose>