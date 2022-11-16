<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Profile</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <style>
        h1 {
            color: white;
            text-align: center;
            font-size: 100px;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-primary">
    <a class="navbar-brand" style="color: #FFFFFF">MyCart</a>
    <form class="form-inline ml-auto">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" style="color: #FFFFFF" href="adminHome.jsp"><b>Home</b></a>
            </li>
            <li class="nav-item">
                   <a class="nav-link" style="color: #FFFFFF" href="http://localhost:8080/ecommarceApplication/product/view"><b>Add New Product</b></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" style="color: #FFFFFF" href="http://localhost:8080/ecommarceApplication/product/findAll"><b>All Product</b></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" style="color: #FFFFFF" href="http://localhost:8080/ecommarceApplication/product/fetchReceivedOrder"><b>Orders Received</b></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" style="color: #FFFFFF" href=""><b>Order Shipped</b></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" style="color: #FFFFFF" href="http://localhost:8080/ecommarceApplication/user/logout"><b>Logout</b></a>
            </li>
        </ul>
    </form>
</nav>
<div style="background-color: #abb8e7;height: 600px">
<h1 style="padding-top: 200px;">welcome admin!</h1>
</div>
</body>
</html>