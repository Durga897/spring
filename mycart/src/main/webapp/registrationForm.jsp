<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<jsp:useBean id="alertMessage" class="java.lang.String" scope="application"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link href="<c:url value="/resources/css/registration.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/registration.js" />"></script>
</head>
<body>
<form action="saveUser" class="container" method="POST" name="registrationForm">
    <div class="center">
        <p style="color:green;font-size:15px" >${alertMessage}</p><br>
        <h2>SignUp</h2><br>
        <label for="id"></label>
        <input name="id" id="id" placeholder="user id" type="text"><br>
        <label for="userName"></label>
        <input name="userName" id="userName" placeholder="username" type="text"><br>
        <label for="emailAddress"></label>
        <input name="emailAddress" id="emailAddress" placeholder="email" type="text"><br>
        <label for="mobileNumber"></label>
        <input name="mobileNumber" id="mobileNumber" placeholder="mobile number" type="text" maxlength="10"
               onkeypress="return isNumber(event)"><br>
        <label for="password"></label>
        <input name="password" id="password" placeholder="password" type="password" minlength="8"><br>
        <input name="address" id="address" placeholder="address" type="text"><br>
        <input id="login" value="Sign Up" type="submit" onclick="return testValidations()"><br><br>
    </div>
</form>
</body>
</html>