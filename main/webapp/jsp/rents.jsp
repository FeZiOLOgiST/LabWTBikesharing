<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="${request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <title>Rents - Bikesharing</title>
    <c:import url="/jsp/import/navbar.jsp"/>
</head>
<body>
<div id="table" class="container">
    <div class="row">
        <div class="col">
            <table class="table table-borderless table-hover" id="bikesTable">
                <thead>
                <tr>
                    <th scope="col">${stringManager.get("start", sessionScope.locale)}</th>
                    <th scope="col">${stringManager.get("end", sessionScope.locale)}</th>
                    <th scope="col">${stringManager.get("price", sessionScope.locale)}</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${rents}" var="rent">
                    <tr>
                        <td id="start">${rent.start}</td>
                        <td id="end">${rent.end}</td>
                        <td id="cost">$ <fmt:formatNumber value = "${rent.cost}" maxFractionDigits = "2"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>