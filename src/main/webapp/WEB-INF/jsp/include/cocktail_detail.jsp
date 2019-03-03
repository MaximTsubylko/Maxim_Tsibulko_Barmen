<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@include file="/WEB-INF/resources.jsp" %>

</head>
<div>
    <div>
        <div>
            <div>
                <div>
                    <div>
                        <p><c:out value="${cocktail.name}"/></p>
                    </div>
                    <div class="content">
                        <p><strong>Description: </strong><c:out value="${cocktail.description}"/></p>
                        <p><strong>Price: </strong><c:out value="${cocktail.price}"/></p>
                    </div>
                    <%--<form action="${pageContext.request.contextPath}/demo" method="post">--%>
                        <%--<input type="hidden" name="command" value="update_cocktil">--%>
                        <%--<input type="hidden" name="cocktilId" value="${cocktil.id}">--%>
                        <%--<input class="button is-light" type="submit" value="Update cocktil">--%>
                    <%--</form>--%>
                    <form action="${pageContext.request.contextPath}/barman" method="post">
                        <input type="hidden" name="command" value="delete_cocktail">
                        <input type="hidden" name="cocktailId" value="${cocktail.id}">
                        <input class="button is-danger" type="submit" value="Delete">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
