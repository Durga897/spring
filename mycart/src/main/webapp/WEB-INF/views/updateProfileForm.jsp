<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="userName" class="java.lang.String" scope="application"/>
<jsp:useBean id="password" class="java.lang.String" scope="application"/>
<jsp:useBean id="emailAddress" class="java.lang.String" scope="application"/>
<jsp:useBean id="mobileNumber" class="java.lang.String" scope="application"/>
<jsp:useBean id="updateMessage" class="java.lang.String" scope="application"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <link href="<c:url value="/resources/css/registration.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/updateProfile.js" />"></script>
</head>
<body>
<form action="update" class="container" method="POST" name="updateForm">
    <div class="center">
        <p style="color:green;font-size:15px">${updateMessage}</p><br>
        <h3>Edit Profile</h3><br>
        <h4>Welcome ${user.get().id} </h4>
        <label for="id"></label>
        <input name="id" id="id" placeholder="user id" type="hidden" value=${user.get().id} readonly  style="border:none"><br>
        <label for="emailAddress"></label>
        <input name="emailAddress" id="emailAddress" placeholder="email" type="text" value=${user.get().emailAddress} readonly
               style="border:none"><br>
        <label for="userName"></label>
        <input name="userName" id="userName" placeholder="username" type="text" value=${user.get().userName}><br>
        <label for="mobileNumber"></label>
        <input id="mobileNumber" maxlength="10" name="mobileNumber" onkeypress="return isNumber(event)"
               placeholder="mobile number" type="text" value=${user.get().mobileNumber}><br>
        <label for="address"></label>
        <input name="address" id="address" placeholder="address" type="text" value=${user.get().address}><br>
        <input id="login" value="Update" type="submit" onclick="return testValidations()"><br><br>
    </div>
</form>
</body>
</html>