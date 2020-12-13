<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <link href="${request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <title>Login - Bikesharing</title>
</head>
<body background="${request.contextPath}/bghd.jpg">
<div class="card" style="margin: auto; padding: 10px; width: 400px">
    <form name="loginForm" method="POST" action="controller">
        <input type="hidden" name="command" value="login" />
        <div class="form-group">
            <label for="input_login">Login</label>
            <input name="login" type="text" class="form-control" id="input_login" placeholder="Enter login" required pattern="^[a-z0-9_-]{4,16}$">
        </div>
        <div class="form-group">
            <label for="input_password">Password</label>
            <input name="password" type="password" class="form-control" id="input_password" aria-describedby="passwordHelp" placeholder="Password" required>
        </div>
        <button type="submit" class="btn btn-primary" style="width: 100%">Log in</button>
        ${errorLoginPassMessage}
        ${wrongAction}
        ${nullPage}
    </form>
    <a href="${request.contextPath}/controller?command=signup">
        <button class="btn btn-secondary" style="width: 100%">Sign up</button>
    </a>
</div>
</body>
</html>