<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<jsp:useBean id="alertMessage" class="java.lang.String" scope="application"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link href="<c:url value="/resources/css/addProduct.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/addProduct.js" />"></script>
</head>
<body>

<form action="/product/save" class="container" method="POST" name="registrationForm" enctype="multipart/form-data">
    <div class="center">
        <p style="color:green;font-size:15px" >${message}</p>
        <p style="color:red;font-size:15px">${alertMessage}</p><br>
        <h2>Product</h2><br>
        <label for="id"></label>
        <input name="id" id="id" placeholder="Id" type="text" required><br>
        <label for="name"></label>
        <input name="name" id="name" placeholder="name" type="text" required><br>
        <label for="color"></label>
        <input name="color" id="color" placeholder="color" type="text"><br>
        <label for="description"></label>
        <input name="description" id="description" placeholder="description" type="text">
        <label for="price"></label>
        <input name="price" id="price" placeholder="price" type="text" onkeypress="return isNumber(event)" required>
        <label for="stock"></label>
        <input name="stock" id="stock" placeholder="stock" type="text" onkeypress="return isNumber(event)" required>
        <input type="file" name="image">
        <input id="login" value="Submit" type="submit"><br><br>
    </div>
</form>
</body>
</html>
