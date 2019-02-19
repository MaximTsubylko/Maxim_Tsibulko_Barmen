<%--
  Created by IntelliJ IDEA.
  User: Buddtha
  Date: 19.02.2019
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <form action="${pageContext.request.contextPath}/demo" method="post">
        <p><strong>Name: </strong>
            <input type="text" id="name" class="text-field" name="name" required="required"
                   placeholder="name"/></p>

        <p><strong>Description: </strong>
            <input type="text" id="description" class="text-field" name="description" required="required"
                   placeholder="description"/></p>

        <p><strong>Price: </strong>
            <input type="text" id="price" class="text-field" name="price" required="required"
                   placeholder="price"/></p>

        <input type="hidden" name="command" value="update_cocktil">
        <input class="button is-danger" type="submit" value="Apply">
    </form>
</div>