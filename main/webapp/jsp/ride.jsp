<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Current ride - Bikesharing</title>
    <link href="${request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <c:import url="/jsp/import/navbar.jsp"/>
    <style>
        .btn-circle-xl {
            font-weight: lighter;
            width: 300px;
            height: 300px;
            border-radius: 150px;
            font-size: 36pt;
            margin: auto;
        }
        p {
            font-size: 30pt;
            font-weight: lighter;
        }
    </style>
</head>
<body>
<div style="text-align: center">
    <br><br>
    <p>Ride started<br><b class="time">${requestScope.start}<b><p>
    <br>
    <a href="${request.contextPath}/controller?command=finish_rent">
        <button type="button" class="btn btn-outline-success btn-circle-xl">Finish<br>ride</button>
    </a>
</div>
</body>
</html>