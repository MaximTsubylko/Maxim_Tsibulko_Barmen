<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="content-header">
    <div id="breadcrumb"><a href="?command=main" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>
        Home</a><a href="#" class="current">User profile</a></div>
</div>


<div class="container con container-fluid">
    <div class="row-fluid">
        <div class="span2">
            <img src="${pageContext.request.contextPath}/static/img/usericon.png">
            <div class="pagination-centered">
                <h1><c:out value="${customer.login}"/></h1>
            </div>
        </div>

        <div class="span4">
            <div class="widget-content nopadding">
                <h1>User information:</h1>
                <p><h5>First name : <c:out value="${customer.first_name}"/></h5></p>
                <p><h5>Second name : <c:out value="${customer.second_name}"/></h5></p>
                <p><h5>E-mail : <c:out value="${customer.email}"/></h5></p>
                <p><h5>Role : <c:out value="${customer.role_id}"/></h5></p>
                <p><h5>State : <c:out value="${customer.state}"/></h5></p>
            </div>
        </div>


        <div class="span5">
            <div class="widget-content nopadding">
                <h2 class="pagination-centered">Your cocktails</h2>
                <hr>
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
