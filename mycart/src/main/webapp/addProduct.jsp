<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<jsp:useBean id="alertMessage" class="java.lang.String" scope="application"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="<c:url value="/resources/css/addProduct.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/addProduct.js" />"></script>
</head>
<body>

<form action="saveProduct" class="container" method="POST" name="registrationForm" enctype="multipart/form-data">
    <div class="center">
        <h2>Product</h2><br>
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
        <input type="file" name="photo" id="photo">
        <input id="login" value="Submit" type="submit"><br><br>
    </div>
</form>
</body>
</html>
