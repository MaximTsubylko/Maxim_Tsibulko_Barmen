<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 17.02.2019
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
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
            <c:forEach items="${cocktilList}" var="cocktil">
                <tr>
                    <td>
                        <a href="?command=view_cocktil_details&id=${cocktil.id}">
                            <c:out value="${cocktil.id}"/>
                        </a>
                    </td>
                    <td><c:out value="${cocktil.name}"/></td>
                    <td><c:out value="${cocktil.description}"/></td>
                    <td><c:out value="${cocktil.price}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
