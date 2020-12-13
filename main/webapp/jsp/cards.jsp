<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="${request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <title>My cards - Bikesharing</title>
    <c:import url="/jsp/import/navbar.jsp"/>
    <style>
        .btn {
            width: 200px;
            margin-top: 5px;
        }
        .card {
            margin-top: 15px;
            width: 200px;
            height: 125px;
            filter: sepia(100%) brightness(85%);
        }
        .container {
            display: grid;
            justify-items: center;
            grid-template-columns: auto auto auto;
            grid-template-rows: auto;
        }
        form[name="cardForm"] {
            margin: 0;
        }
    </style>
</head>
<body>
<div class="container">
    <c:forEach items="${cards}" var="card">
        <div>
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">${card.month}/${card.year}</h5>
                    <p class="card-subtitle">${card.firstName} ${card.lastName}</p>
                    <p class="card-text">${fn:substring(card.number, 0, 4)} **** **** ****</p>
                </div>
            </div>
            <div style="display: block; width: 200px">
                <form name="cardForm" method="POST" action="controller">
                    <input type="hidden" name="command" value="replenish_page" />
                    <input type="hidden" name="card_id" value="${card.id}" />
                    <button type="submit" class="btn btn-primary">${stringManager.get("replenish_balance", sessionScope.locale)}</button>
                </form>
                <div class="btn btn-danger">${stringManager.get("delete_card", sessionScope.locale)}</div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
