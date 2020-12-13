<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="${request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <title>Sign up - Bikesharing</title>
    <c:set var = "passwordPattern" value = "^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$"/>
</head>
<body background="${request.contextPath}/bghd.jpg">
<div class="card" style="margin: auto; padding: 10px; width: 400px">
    <form name="loginForm" method="POST" action="controller">
        <input type="hidden" name="command" value="register" />
        <div class="form-group">
            <label for="inputLogin">Login</label>
            <input name="login" type="text" class="form-control" id="inputLogin" placeholder="Enter login" required pattern="^[a-z0-9_-]{4,16}$">
        </div>
        <div class="form-group">
            <label for="inputEmail">Email</label>
            <input name="email" type="text" class="form-control" id="inputEmail" aria-describedby="emailSmall" placeholder="Enter email">
            <small id="emailSmall" class="form-text text-muted">We'll never share your email or password with anyone else.</small>
        </div>
        <div class="form-group">
            <label for="inputPassword">Password</label>
            <input name="password" type="password" class="form-control" id="inputPassword" aria-describedby="passwordHelp" placeholder="Enter password" required pattern="${passwordPattern}">
        </div>
        <div class="form-group">
            <input name="repeatPassword" type="password" class="form-control" id="inputRepeatPassword" placeholder="Confirm password" required pattern="${passwordPattern}">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
        ${message}
    </form>
</div>
</body>
</html>
