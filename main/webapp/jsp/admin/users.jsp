<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users - Bikesharing</title>
    <link href="${request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script src="${request.contextPath}/bootstrap/js/jquery.min.js"></script>
    <script src="${request.contextPath}/bootstrap/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<c:import url="/jsp/import/navbar.jsp"/>
<div id="searchBar" class="container">
    <div class="row">
        <input class="form-control" type="text" placeholder="Login" aria-label="Search by login" style="margin: 8px; width: 200px">
        <input class="form-control" type="number" placeholder="Email" aria-label="Search by mail" style="margin: 8px; width: 200px">
        <input class="form-control" type="text" placeholder="Balance" aria-label="Search balance" style="margin: 8px; width: 200px">
        <div class="form-group form-check" style="margin: 8px">
            <input name="availability" type="checkbox" class="form-check-input" id="availabilitySearch">
            <label class="form-check-label" for="availabilitySearch">Availability</label>
        </div>
        <a href="${request.contextPath}/controller?command=search" class="btn btn-primary" role="button" aria-pressed="true" style="margin: 8px">Search</a>
    </div>
</div>
<div id="table" class="container">
    <div class="row">
        <div class="col">
            <table class="table table-borderless table-hover" id="bikesTable">
                <thead>
                <tr>
                    <th scope="col">${stringManager.get("login", sessionScope.locale)}</th>
                    <th scope="col">${stringManager.get("email", sessionScope.locale)}</th>
                    <th scope="col">${stringManager.get("balance", sessionScope.locale)}</th>
                    <th scope="col">BikeID</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr onclick="location.href='${request.contextPath}/controller?command=profile&profile_name=${user.login}'">
                        <td id="number">${user.login}</td>
                        <td id="manufacturer">${user.email}</td>
                        <td id="balance">$ <fmt:formatNumber value = "${user.balance}" maxFractionDigits = "2"/></td>
                        <td id="spot">${user.bikeId}</td>
                        <td id="id" style="display: none">${user.id}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script>
    function setForm(elmnt) {
        let id = elmnt.querySelector("#id").innerHTML;
        document.getElementById("idInput").value = id;
        document.getElementById("manufacturerInput").value = elmnt.querySelector("#manufacturer").innerHTML;
        document.getElementById("numberInput").value = elmnt.querySelector("#number").innerHTML;
        document.getElementsByName(id).item(0).selected = true;
    }
</script>
</body>
</html>