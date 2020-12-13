<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bike models - Bikesharing</title>
    <link href="${request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script src="${request.contextPath}/bootstrap/js/jquery.min.js"></script>
    <script src="${request.contextPath}/bootstrap/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<c:import url="/jsp/import/navbar.jsp"/>
<%--<div id="searchBar" class="container">--%>
<%--    <div class="row">--%>
<%--        <div class="dropdown" style="margin: 8px">--%>
<%--            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownManufacturer" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
<%--                Model--%>
<%--            </button>--%>
<%--            <div class="dropdown-menu" aria-labelledby="dropdownManufacturer">--%>
<%--                <a class="dropdown-item" href="#">Merida</a>--%>
<%--                <a class="dropdown-item" href="#">Cube</a>--%>
<%--                <a class="dropdown-item" href="#">Giant</a>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <input class="form-control" type="text" placeholder="Serial Number" aria-label="Search number" style="margin: 8px; width: 200px">--%>
<%--        <input class="form-control" type="number" placeholder="Cost" aria-label="Search cost" style="margin: 8px; width: 200px">--%>
<%--        <input class="form-control" type="text" placeholder="User" aria-label="Search user" style="margin: 8px; width: 200px">--%>
<%--        <div class="form-group form-check" style="margin: 8px">--%>
<%--            <input name="availability" type="checkbox" class="form-check-input" id="availabilitySearch">--%>
<%--            <label class="form-check-label" for="availabilitySearch">Availability</label>--%>
<%--        </div>--%>
<%--        <a href="${request.contextPath}/controller?command=search" class="btn btn-primary" role="button" aria-pressed="true" style="margin: 8px">Search</a>--%>
<%--    </div>--%>
<%--</div>--%>
<div id="table" class="container">
    <div class="row">
        <div class="col">
            <table class="table table-borderless table-hover" id="bikesTable">
                <thead>
                <tr>
                    <th scope="col" style="display: none">ID</th>
                    <th scope="col">${stringManager.get("name", sessionScope.locale)}</th>
                    <th scope="col">${stringManager.get("cost_hr", sessionScope.locale)}</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${models}" var="model">
                    <tr onclick="setForm(this)">
                        <th id="id" scope="row" style="display: none">${model.id}</th>
                        <td id="name">${model.name}</td>
                        <td id="cost"><fmt:formatNumber value = "${model.cost}" maxFractionDigits = "2" /></td>
                        <td id="url" style="display: none">${model.imageUrl}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-sm-auto" style="max-width: 310px">
            <form name="addForm" method="post" action="${request.contextPath}/controller">
                <input type="hidden" name="command" value="add_model">
                <div class="form-group" style="display: none">
                    <label for="idInput">ID</label>
                    <input name="id" type="text" class="form-control" id="idInput">
                </div>
                <div class="form-group">
                    <label for="nameInput">${stringManager.get("name", sessionScope.locale)}</label>
                    <input name="name" type="text" class="form-control" id="nameInput">
                </div>
                <div class="form-group">
                    <label for="costInput">${stringManager.get("cost_hr", sessionScope.locale)}</label>
                    <input name="cost" type="text" class="form-control" id="costInput">
                </div>
                <div class="form-group">
                    <label for="imageInput">${stringManager.get("image_url", sessionScope.locale)}</label>
                    <input name="image" type="text" class="form-control" id="imageInput">
                </div>
                <img id="bike_image" width="300">
                <button type="submit" class="btn btn-primary btn-form" name="action" value="add">${stringManager.get("add_model", sessionScope.locale)}</button>
                <button type="submit" class="btn btn-success btn-form" name="action" value="edit">${stringManager.get("edit_cost", sessionScope.locale)}</button>
            </form>
        </div>
    </div>
</div>
<script>
    function setForm(elmnt) {
        let id = elmnt.querySelector("#id").innerHTML;
        document.getElementById("idInput").value = id;
        document.getElementById("nameInput").value = elmnt.querySelector("#name").innerHTML;
        document.getElementById("costInput").value = elmnt.querySelector("#cost").innerHTML;
        let url = elmnt.querySelector("#url").innerHTML;
        document.getElementById("imageInput").value = url;
        document.getElementById("bike_image").src = url;
    }
</script>
</body>
</html>