<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Rent a bike - Bikesharing</title>
        <link href="${request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <c:import url="/jsp/import/navbar.jsp"/>
        <style>
            #map {
                height: 500px;
            }
            #balance {
                position: absolute;
                right: 10px;
                bottom: 10px;
                width: 200px;
                height: 185px;
            }
            div.scroll_menu {
                overflow: auto;
                white-space: nowrap;
            }
        </style>
    </head>
    <body>
    <div class="container">
        <div id="map"></div>
        <div class="scroll_menu" id="gallery"></div>
        <div id="balance" class="card card-body">
            <h6 class="card-title" style="text-align: center">${stringManager.get("balance", sessionScope.locale)}</h6>
            <h5 class="card-text" style="text-align: center">$<fmt:formatNumber value = "${sessionScope.user.balance}" maxFractionDigits = "2" /></h5>
            <a class="btn btn-success" href="${request.contextPath}/controller?command=cards_page" style="width: 100%; margin-bottom: 5px">${stringManager.get("replenish", sessionScope.locale)}</a>
            <a class="btn btn-primary" href="${request.contextPath}/jsp/new_card.jsp" style="width: 100%">${stringManager.get("add_card", sessionScope.locale)}</a>
        </div>
    </div>
    <c:import url="/jsp/import/map_init.jsp"/>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDB9qJAMttAGr54_ytGZ9ua1ixd8Jsvp5E&callback=initMap">
    </script>
    </body>
</html>