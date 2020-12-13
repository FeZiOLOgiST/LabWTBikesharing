<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <style>
        .bike-card {
            margin-top: 10px;
            margin-bottom: 5px;
            width: 200px;
            height: 300px;
            display: inline-block;
        }
        form[name="rentForm"] {
            margin: 0;
        }
    </style>
</head>
<body>
<script>
    function initMap() {
        let map = new google.maps.Map(document.getElementById('map'));
        let markers = [
            <c:forEach var="marker" items="${spots}"> [
                '<c:out value="${marker.address}" />',
                <c:out value="${marker.lat}" />,
                <c:out value="${marker.lng}" />,
                <c:out value="${marker.id}" />
            ],
            </c:forEach>
        ];
        let bikes = [
            <c:forEach var="bike" items="${bikes}"> [
                <c:out value="${bike.id}" />,
                <c:out value="${bike.spotId}" />,
                <c:out value="${bike.serialNumber}" />,
                <c:out value="${bike.model.cost}" />,
                '<c:out value="${bike.model.name}" />',
                '<c:out value="${bike.model.imageUrl}" />'
            ],
            </c:forEach>
        ];
        let bounds = new google.maps.LatLngBounds();
        for (let i = 0; i < markers.length; i++) {
            let position = new google.maps.LatLng(markers[i][1], markers[i][2]);
            bounds.extend(position);
            createMarker(markers[i][0], position, markers[i][3]);
        }
        map.fitBounds(bounds);
        let boundsListener = google.maps.event.addListener((map), 'bounds_changed', function() {
            google.maps.event.removeListener(boundsListener);
        });

        let previousInfoWindow = false;
        function createMarker(address, position, spotId) {
            let marker = new google.maps.Marker({
                position: position,
                map: map,
                title: address
            });
            marker['infoWindow'] = new google.maps.InfoWindow();
            google.maps.event.addListener(marker, 'click', function() {
                if (previousInfoWindow) {
                    previousInfoWindow.close();
                }
                previousInfoWindow = this['infoWindow'];
                let spotBikes = bikes.filter(bike => bike[1] === spotId);
                this['infoWindow'].setContent(spotInfoHtml(address, spotBikes.length));
                this['infoWindow'].open(map, this);
                let gallery = document.getElementById('gallery');
                gallery.innerHTML = "";
                spotBikes.forEach(bike => {
                    gallery.innerHTML +=
                        "<div class='card card-body bike-card' style='margin-right: 5px'>" +
                        "<img src='" + bike[5] + "' class='card-img-top' alt='" + bike[4] + "'>" +
                        "<h5 class='card-title' style='text-align: center'>" + bike[4] + "</h5>" +
                        "<p class='card-text' style='text-align: center'><b>" + bike[2] + "</b><br>Serial number</p>" +
                        "<form name='rentForm' method='POST' action='controller'>" +
                            "<input type='hidden' name='command' value='rent' />" +
                            "<input type='hidden' name='bike_id' value='" + bike[0] +"' />" +
                            "<button type='submit' class='btn btn-primary' style='width: 100%'>Rent ($" + bike[3] + "/hr)</button>" +
                        "</form>" +
                        "</div>";
                })
            });
        }
    }

    function spotInfoHtml(address, count) {
        return '<div id="content">'+
            '<div id="siteNotice">'+
            '</div>'+
            '<h4 id="firstHeading" class="firstHeading">' + address + '</h4>'+
            '<div id="bodyContent">'+
            '<h6>' + count.toString() + ' bikes available.</h6>'+
            '</div>'+
            '</div>';
    }
</script>
</body>
<html>