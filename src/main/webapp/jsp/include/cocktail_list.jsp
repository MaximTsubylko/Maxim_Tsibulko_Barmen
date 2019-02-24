<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/resources.jsp" %>


<div id="content-header">
    <div id="breadcrumb"><a href="?command=main" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>
        Home</a><a href="#" class="current">Cocktail list</a></div>
    <h1>Cocktail list</h1>
</div>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span6">


            <div class="widget-content nopadding">

                <div class="widget-box">
                    <div class="widget-title"><span class="icon"> <i class="icon-th"></i> </span>
                        <h5>Cocktails</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Price</th>
                            </tr>
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
            </div>
        </div>
    </div>
</div>

