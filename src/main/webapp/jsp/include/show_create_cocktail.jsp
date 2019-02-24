<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <%@include file="/static/resources.jsp" %>

</head>
<div id="content-header">
    <div id="breadcrumb"><a href="?command=main" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>
        Home</a><a href="#" class="current">Create cocktail</a></div>
    <h1>Create new cocktail</h1>
</div>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span6">


            <div class="widget-content nopadding">
                <div class="widget-box">


                    <div class="widget-title"><span class="icon"> <i class="icon-align-justify"></i> </span>
                        <h5>Fill the form</h5>
                    </div>
                    <form action="${pageContext.request.contextPath}/barman" method="post" class="form-horizontal">
                        <div class="control-group">
                            <label class="control-label">Name :</label>
                            <div class="controls">
                                <input type="text" class="span11" placeholder="Name" name="name">
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">Description :</label>
                            <div class="controls">
                                <input type="text" class="span11" placeholder="Description" name="description">
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">Price :</label>
                            <div class="controls">
                                <input type="text" class="span11" placeholder="Price" name="price">
                            </div>
                        </div>
                        <input type="hidden" name="command" value="register_cocktail">
                        <button type="submit" class="btn btn-success btn btn-large btn-block">Create</button>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>


