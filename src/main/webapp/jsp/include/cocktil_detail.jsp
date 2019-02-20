<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <div>
        <div>
            <div>
                <div>
                    <div>
                        <p><c:out value="${cocktil.name}"/></p>
                    </div>
                    <div class="content">
                        <p><strong>Description: </strong><c:out value="${cocktil.description}"/></p>
                        <p><strong>Price: </strong><c:out value="${cocktil.price}"/></p>
                    </div>
                    <%--<form action="${pageContext.request.contextPath}/demo" method="post">--%>
                        <%--<input type="hidden" name="command" value="update_cocktil">--%>
                        <%--<input type="hidden" name="cocktilId" value="${cocktil.id}">--%>
                        <%--<input class="button is-light" type="submit" value="Update cocktil">--%>
                    <%--</form>--%>
                    <form action="${pageContext.request.contextPath}/main" method="post">
                        <input type="hidden" name="command" value="delete_cocktil">
                        <input type="hidden" name="cocktilId" value="${cocktil.id}">
                        <input class="button is-danger" type="submit" value="Delete">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
