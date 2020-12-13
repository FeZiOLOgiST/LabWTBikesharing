<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="${request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <title>Add card - Bikesharing</title>
    <c:import url="/jsp/import/navbar.jsp"/>
    <style>
        .date-input {
            width: 100px;
            margin: 10px;
        }
    </style>
</head>
<body>
<div class="card" style="margin: auto; padding: 10px; width: 400px">
    <form name="cardForm" method="POST" action="${request.contextPath}/controller">
        <input type="hidden" name="command" value="add_card" />
        <div class="form-group">
            <label for="input_number">Number</label>
            <input name="number" type="text" class="form-control" id="input_number" placeholder="Enter card number" required pattern="^[0-9]{1,16}$">
        </div>
        <div class="form-group">
            <label for="input_first_name">First name</label>
            <input name="first_name" type="text" class="form-control" id="input_first_name" placeholder="Enter name" required pattern="^[A-Z][a-zA-Z]{2,20}$">
            <label for="input_last_name">Last name</label>
            <input name="last_name" type="text" class="form-control" id="input_last_name" placeholder="Enter surname" required pattern="^[A-Z][a-zA-Z]{3,20}$">
        </div>
        <label>Expiration date</label>
        <div class="form-inline">
            <label for="input_month">Month</label>
            <input name="month" type="text" class="form-control date-input" id="input_month" placeholder="Enter month" required pattern="^[0-9]{1,2}$">
            <label for="input_year">Year</label>
            <input name="year" type="text" class="form-control date-input" id="input_year" placeholder="Enter year" required pattern="^[0-9]{2}$">
        </div>
        <div class="form-group">
            <label for="input_cvv">CVV code</label>
            <input name="cvv" type="password" class="form-control" id="input_cvv" placeholder="Enter CVV code" required pattern="^[0-9]{1,3}$">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
        ${requestScope.message}
    </form>
</div>
</body>
</html>