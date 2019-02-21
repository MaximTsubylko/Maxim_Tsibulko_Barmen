<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <div>
        <table>
            <thead>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            </thead>
            <tbody>
            <c:forEach items="${cocktailList}" var="cocktail">
                <tr>
                    <td>
                        <a href="?command=view_cocktail_details&id=${cocktail.id}">
                            <c:out value="${cocktail.id}"/>
                        </a>
                    </td>
                    <td><c:out value="${cocktail.name}"/></td>
                    <td><c:out value="${cocktail.description}"/></td>
                    <td><c:out value="${cocktail.price}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
