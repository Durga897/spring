<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="logoutMessage" scope="application" class="java.lang.String"/>
<jsp:useBean id="message" class="java.lang.String" scope="application"/>
<jsp:useBean id="deleteMessage" class="java.lang.String" scope="application"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <link href="<c:url value="/resources/css/index.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/index.js" />"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-primary">
    <a class="navbar-brand" style="color: #FFFFFF">MyCart</a>
</nav>
<div class="row">
    <div class="col-sm-4">
    </div>
    <div class="col-sm-4">
        <div class="tab-content" style="text-align: center">
            <form class="container" action="user/login" method="post" name="loginForm">
                <div id="logins" class="tab-pane active">
                    <ul class="nav nav-tabs">
                        <li class="nav-item"><a href="index.jsp" style="color: #445fd5" class="nav-link"> User Login</a></li>
                        <li class="nav-item"><a href="adminLogin.jsp" class="nav-link" style="color: #445fd5" >Admin Login</a></li>
                    </ul>
                    <h2>User Login</h2><br>
                    <label for="id"></label>
                    <input name="id" id="id" placeholder="User Id" type="text"><br>
                    <label for="password"></label>
                    <input name="password" id="password" placeholder="password" type="password" ><br>
                    <input id="login" value="Sign In" type="submit" onclick="return testValidations()"><br><br>
                    <br>
                    Dont have an account? <a href="registrationForm.jsp">create an account</a><br><br>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>