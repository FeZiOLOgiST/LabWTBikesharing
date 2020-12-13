<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="${request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <title>Verification - Bikesharing</title>
</head>
<body background="${request.contextPath}/bghd.jpg">
<div class="card" style="margin: auto; padding: 10px; width: 400px">
    <form name="verificationForm" method="POST" action="controller">
        <input type="hidden" name="command" value="verify" />
        <div class="form-group">
            <label for="codeInput">Verification code has been sent to your email.</label>
            <input name="code" type="text" class="form-control" id="codeInput" placeholder="Enter code" required pattern="[0-9]{6}">
        </div>
        <button type="submit" class="btn btn-primary">Verify</button>
    </form>
</div>
</body>
</html>
