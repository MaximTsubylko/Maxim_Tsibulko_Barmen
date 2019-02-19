<%--
  Created by IntelliJ IDEA.
  User: Buddtha
  Date: 19.02.2019
  Time: 2:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>le</title>
</head>
<body>
<div class="column is-one-quarter">
    <jsp:include page="navigation.jsp"/>
</div>
<div class="column is-three-quarters">
    <jsp:include page="include/${viewName}.jsp"/>
</div>
</body>
</html>
