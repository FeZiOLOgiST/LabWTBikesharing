<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Bikes - Bikesharing</title>
    <link href="${request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script src="${request.contextPath}/bootstrap/js/jquery.min.js"></script>
    <script src="${request.contextPath}/bootstrap/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<c:import url="/jsp/import/navbar.jsp"/>
<div id="searchBar" class="container">
    <div class="row">
        <div class="dropdown" style="margin: 8px">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownManufacturer" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Model
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownManufacturer">
                <a class="dropdown-item" href="#">Merida</a>
                <a class="dropdown-item" href="#">Cube</a>
                <a class="dropdown-item" href="#">Giant</a>
            </div>
        </div>
        <input class="form-control" type="text" placeholder="Serial Number" aria-label="Search number" style="margin: 8px; width: 200px">
        <input class="form-control" type="number" placeholder="Cost" aria-label="Search cost" style="margin: 8px; width: 200px">
        <input class="form-control" type="text" placeholder="User" aria-label="Search user" style="margin: 8px; width: 200px">
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
                    <th scope="col">${stringManager.get("serial_number", sessionScope.locale)}</th>
                    <th scope="col">${stringManager.get("model", sessionScope.locale)}</th>
                    <th scope="col">${stringManager.get("cost", sessionScope.locale)}</th>
                    <th scope="col">${stringManager.get("spot", sessionScope.locale)}</th>
                    <th scope="col">${stringManager.get("user", sessionScope.locale)}</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${bikes}" varStatus="loop">
                        <tr onclick="setForm(this)">
                            <td id="number">${bikes[loop.index].serialNumber}</td>
                            <td id="model_name">${bikes[loop.index].model.name}</td>
                            <td id="cost">$<fmt:formatNumber value = "${bikes[loop.index].model.cost}" maxFractionDigits = "2"/>/${stringManager.get("hour", sessionScope.locale)}</td>
                            <td id="spot_name">${spot_names[loop.index]}</td>
                            <c:set var="profile_name" scope="request" value="${user_names[loop.index]}"/>
                            <td id="user_name">
                                <c:choose>
                                    <c:when test="${profile_name == 'NULL'}">
                                        NULL
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${request.contextPath}/controller?command=profile&profile_name=${profile_name}">${profile_name}</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td id="id" style="display: none">${bikes[loop.index].id}</td>
                            <td id="user_id" style="display: none">${bikes[loop.index].userId}</td>
                            <td id="spot_id" style="display: none">${bikes[loop.index].spotId}</td>
                            <td id="model_id" style="display: none">${bikes[loop.index].model.id}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-sm-auto">
            <form name="addForm" method="post" action="${request.contextPath}/controller">
                <input type="hidden" name="command" value="add_bike">
                <div class="form-group" style="display: none">
                    <label for="id_input">ID</label>
                    <input name="bike_id" type="text" class="form-control" id="id_input">
                </div>
                <div class="form-group">
                    <label for="numberInput">${stringManager.get("serial_number", sessionScope.locale)}</label>
                    <input name="serial_number" type="text" class="form-control" id="numberInput">
                </div>
                <div class="form-group">
                    <label>${stringManager.get("model", sessionScope.locale)}</label>
                    <select name="select_model" id="model_select" class="form-control">
                        <c:forEach items="${models}" var="model">
                            <option name="${model.id}">${model.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>${stringManager.get("spot", sessionScope.locale)}</label>
                    <select name="select_spot" id="spot_select" class="form-control">
                        <option>NULL</option>
                        <c:forEach items="${spots}" var="spot">
                            <option>${spot.address}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary btn-form" name="action" value="add">${stringManager.get("add_bike", sessionScope.locale)}</button>
                <button type="submit" class="btn btn-success btn-form" name="action" value="edit">${stringManager.get("edit_spot", sessionScope.locale)}</button>
            </form>
        </div>
    </div>
</div>
<%--<nav class="container">--%>
<%--    <ul class="pagination">--%>
<%--        <li class="page-item"><a class="page-link" href="#">Previous</a></li>--%>
<%--        <li class="page-item"><a class="page-link" href="#">1</a></li>--%>
<%--        <li class="page-item"><a class="page-link" href="#">2</a></li>--%>
<%--        <li class="page-item"><a class="page-link" href="#">3</a></li>--%>
<%--        <li class="page-item"><a class="page-link" href="#">Next</a></li>--%>
<%--    </ul>--%>
<%--</nav>--%>
<script>
    function setForm(elmnt) {
        document.getElementById("id_input").value = elmnt.querySelector("#id").innerHTML;
        document.getElementById("numberInput").value = elmnt.querySelector("#number").innerHTML;
        document.getElementById("spot_select").value = elmnt.querySelector("#spot_name").innerHTML;
        document.getElementById("model_select").value = elmnt.querySelector("#model_name").innerHTML;
    }
</script>
</body>
</html>