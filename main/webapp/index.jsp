<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
<c:if test = "${sessionScope.user.role == 'admin'}">
    <jsp:forward page="${requestContext}/controller?command=main_page"/>
</c:if>
<c:if test = "${sessionScope.user.role == 'user'}">
    <jsp:forward page="${requestContext}/controller?command=main_page"/>
</c:if>
<jsp:forward page="/jsp/login.jsp"/>
</body>
</html>