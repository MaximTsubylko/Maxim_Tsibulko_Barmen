<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="utf-8"/>


<fmt:setLocale value="${cookie['locale'].value}"/>
<fmt:setBundle basename="language" var="bundle" scope="application"/>

<div>
    <form action="${pageContext.request.contextPath}/barman" method="post">
        <p><strong><fmt:message key="cocktail.name" bundle="${bundle}"/>: </strong>
            <input type="text" id="name" class="text-field" name="name" required="required"
                   placeholder="name"/></p>

        <p><strong><fmt:message key="cocktail.descriptoin" bundle="${bundle}"/>: </strong>
            <input type="text" id="description" class="text-field" name="description" required="required"
                   placeholder="description"/></p>

        <p><strong><fmt:message key="cocktail.price" bundle="${bundle}"/>: </strong>
            <input type="text" id="price" class="text-field" name="price" required="required"
                   placeholder="price"/></p>

        <input type="hidden" name="command" value="update_cocktail">
        <input class="button is-danger" type="submit" value="Apply">
    </form>
</div>