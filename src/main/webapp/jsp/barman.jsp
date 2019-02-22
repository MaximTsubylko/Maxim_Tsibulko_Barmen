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
