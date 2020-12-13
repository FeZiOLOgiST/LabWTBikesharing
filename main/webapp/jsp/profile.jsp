<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="${request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <title>Profile - Bikesharing</title>
    <c:import url="/jsp/import/navbar.jsp"/>
</head>
<style>
    .upload-btn-wrapper {
        position: relative;
        overflow: hidden;
    }
    input[type=file] {
        font-size: 100px;
        position: absolute;
        left: 0;
        top: 0;
        opacity: 0;
    }
    .language-icon {
        height: 24px;
    }
</style>
<c:set var = "passwordPattern" value = "^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$"/>
<body>
<div class="container">
    <br>
    <div class="row">
        <div class="col-md-3">
            <div class="text-center">
                <div class="form-group">
                    <c:choose>
                        <c:when test="${empty requestScope.profile_user.image}">
                            <img id="profile_image" src="${request.contextPath}/profile.jpg" class="card-img" alt="avatar">
                        </c:when>
                        <c:otherwise>
                            <img id="profile_image" src="${requestScope.profile_user.image}" class="card-img" alt="avatar">
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="form-group upload-btn-wrapper">
                    <button class="btn btn-outline-primary btn-block">${stringManager.get("upload_photo", sessionScope.locale)}</button>
                    <input type="file" name="photo_file" onchange="previewFile()"/>
                </div>
                <form name="addForm" method="post" action="${request.contextPath}/controller">
                    <input type="hidden" name="command" value="rents_page" />
                    <input type="hidden" name="id" value="${requestScope.get("profile_user").id}" />
                    <input type="submit" class="btn btn-outline-primary btn-block" value="${stringManager.get("rents", sessionScope.locale)}">
                </form>
                <c:if test = "${sessionScope.user.id == requestScope.profile_user.id}">
                    <form name="addForm" method="post" action="${request.contextPath}/controller">
                        <input type="hidden" name="command" value="cards_page" />
                        <input type="submit" class="btn btn-outline-primary btn-block" value="${stringManager.get("cards", sessionScope.locale)}">
                    </form>
                </c:if>
            </div>
        </div>
        <div class="col-md-9 personal-info">
            <c:if test = "${not empty requestScope.message}">
                <div class="alert alert-danger alert-dismissable">${requestScope.message}</div>
            </c:if>
            <h3>${stringManager.get("profile_info", sessionScope.locale)}</h3>
            <form name="profileForm" class="form-horizontal" role="form" method="POST" action="controller" autocomplete="off">
                <input type="hidden" name="command" value="save_profile" />
                <input type="hidden" name="image" value="" />
                <div class="form-group">
                    <label class="col-lg-3 control-label">Language:</label>
                    <a class="nav-link language-icon" href="${request.contextPath}/controller?command=localization&locale=us">
                        <img class="language-icon" src="${request.contextPath}/united-kingdom.svg" alt="English">
                    </a>
                    <a class="nav-link language-icon" href="${request.contextPath}/controller?command=localization&locale=ru">
                        <img class="language-icon" src="${request.contextPath}/russia.svg" alt="Русский">
                    </a>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label" for="login">${stringManager.get("login", sessionScope.locale)}:</label>
                    <div class="col-lg-8">
                        <input name="login" id="login" class="form-control" type="text" value="${requestScope.profile_user.login}" pattern="^[a-z0-9_-]{4,16}$">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label" for="email">${stringManager.get("email", sessionScope.locale)}:</label>
                    <div class="col-lg-8">
                        <input name="email" id="email" class="form-control" type="text" value="${requestScope.profile_user.email}" autocomplete="new-password">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label" for="old_password">${stringManager.get("current_password", sessionScope.locale)}:</label>
                    <div class="col-md-8">
                        <input class="form-control" id="old_password" name="old_password" type="password" placeholder="Enter current password" autocomplete="new-password" pattern="${passwordPattern}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label" for="new_password">${stringManager.get("new_password", sessionScope.locale)}:</label>
                    <div class="col-md-8">
                        <input name="new_password" class="form-control" type="password" placeholder="Enter new password" id="new_password" pattern="${passwordPattern}">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-8">
                        <input name="repeat_password" class="form-control" type="password" placeholder="Confirm password" pattern="${passwordPattern}">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-8">
                        <input type="hidden" name="update_image" value="false" />
                        <input type="submit" class="btn btn-primary" value="${stringManager.get("save", sessionScope.locale)}">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    function previewFile() {
        let preview = document.getElementById('profile_image');
        let path = document.querySelector('input[name=image]');
        let file = document.querySelector('input[type=file]').files[0];
        let reader = new FileReader();
        reader.addEventListener("load", function () {
            preview.src = reader.result;
            path.value = reader.result;
        }, false);
        if (file) {
            reader.readAsDataURL(file);
        }
        document.querySelector('input[name=update_image]').value = "true";
    }
</script>
</body>
</html>