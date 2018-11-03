<%--
  Created by IntelliJ IDEA.
  User: chenlin
  Date: 2018.10.28
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:redirect url="/index">
        <c:param name="action" value="queryAllTypes"/>
    </c:redirect>
</body>
</html>
