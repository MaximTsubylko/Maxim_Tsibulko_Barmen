<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <form action="${pageContext.request.contextPath}/demo" method="post">
        <input type="text" id="name" class="text-field" name="name" required="required"
               placeholder="name"/>

        <input type="text" id="description" class="text-field" name="description" required="required"
               placeholder="description"/>

        <input type="text" id="price" class="text-field" name="price" required="required"
               placeholder="price"/>
        <input type="hidden" name="command" value="register_cocktil">
        <input class="button is-danger" type="submit" value="register">
    </form>
</div>