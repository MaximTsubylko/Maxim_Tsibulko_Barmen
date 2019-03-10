<%--
  Created by IntelliJ IDEA.
  User: Buddtha
  Date: 01.03.2019
  Time: 0:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="utf-8"/>



<c:choose>
    <c:when test="${not empty requestScope.get('lang')}">
        <fmt:setLocale value="${requestScope.get('lang')}"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${cookie['locale'].value}"/>
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="language" var="bundle" scope="application"/>
<div id="content-header">
    <div id="breadcrumb"><a href="?command=main" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>
        <fmt:message key="link.home" bundle="${bundle}"/>
    </a>
        <a href="#" class="current"> <fmt:message key="link.useredit" bundle="${bundle}"/>
        </a>
    </div>
    <h1 class="pagination-centered"><fmt:message key="edit.edituprofile" bundle="${bundle}"/>

    </h1>
</div>
<div class="container-fluid">
    <hr>
    <div class="row-fluid">
        <div class="span6">
            <div class="widget-box">
                <div class="widget-title"><span class="icon"> <i class="icon-align-justify"></i> </span>
                    <h5>
                        <fmt:message key="edit.personalinfo" bundle="${bundle}"/>
                    </h5>
                </div>
                <div class="widget-content nopadding">
                    <form action="${pageContext.request.contextPath}/barman" method="post" class="form-horizontal">
                        <div class="control-group">
                            <label class="control-label"> <fmt:message key="user.firstname" bundle="${bundle}"/>
                                :</label>
                            <div class="controls">
                                <input type="text" class="span11"
                                       placeholder="<fmt:message key="user.firstname" bundle="${bundle}"/>"
                                       value="<c:out value="${customer.first_name}"/>" name="first_name">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"> <fmt:message key="user.lastname" bundle="${bundle}"/>:</label>
                            <div class="controls">
                                <input type="text" class="span11"
                                       placeholder="<fmt:message key="user.lastname" bundle="${bundle}"/>"
                                       value="<c:out value="${customer.second_name}"/>" name="second_name">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">E-mail</label>
                            <div class="controls">
                                <input type="email" class="span11" placeholder="E-mail"
                                       value="<c:out value="${customer.email}"/>" name="email">
                            </div>
                        </div>
                        <div class="form-actions">
                            <input type="hidden" name="command" value="edit_profile">
                            <button type="submit" class="btn btn-success"><fmt:message key="edit.savebutton"
                                                                                       bundle="${bundle}"/>
                            </button>
                        </div>
                    </form>
                </div>
            </div>

        </div>


        <div class="span6">
            <div class="widget-box">
                <div class="widget-title"><span class="icon"> <i class="icon-align-justify"></i> </span>
                    <h5>
                        <fmt:message key="edit.changepassword" bundle="${bundle}"/>
                    </h5>
                </div>
                <div class="widget-content nopadding">
                    <form action="${pageContext.request.contextPath}/barman" method="post" class="form-horizontal">

                        <div class="control-group">
                            <label class="control-label">
                                <fmt:message key="edit.oldpassword" bundle="${bundle}"/>
                                :</label>
                            <div class="controls">
                                <input type="text" class="span11"
                                       placeholder="<fmt:message key="edit.oldpassword" bundle="${bundle}"/>"
                                       name="old_password">
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label"> <fmt:message key="edit.newpassword" bundle="${bundle}"/>
                                :</label>
                            <div class="controls">
                                <input type="text" class="span11"
                                       placeholder="<fmt:message key="edit.newpassword" bundle="${bundle}"/>"
                                       name="password">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">
                                <fmt:message key="edit.confirmnewpassword" bundle="${bundle}"/>
                                :</label>
                            <div class="controls">
                                <input type="text" class="span11" placeholder="<fmt:message key="edit.confirmnewpassword" bundle="${bundle}"/>"
                                       name="confirm_password">
                            </div>
                        </div>

                        <div class="form-actions">
                            <button type="submit" class="btn btn-success">
                                <fmt:message key="edit.savebutton" bundle="${bundle}"/>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
