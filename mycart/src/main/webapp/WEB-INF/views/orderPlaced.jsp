<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <style>
        .center {
            margin-top: 30px;
            margin-left: auto;
            margin-right: auto;
            text-align: center;
            max-width: 430px;
            width: 100%;
            padding: 30px;
            border-radius: 6px;
            background: #FFF;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-light bg-primary justify-content-between fixed-top">
    <a class="navbar-brand" style="color: #FFFFFF">MyCart</a>
    <form class="form-inline">
        <p class="mr-4" style="color: #FFFFFF"><b>${user.userName}</b></p>
        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
</nav>
<br><br><br>
<form method="get" action="/mycart_war_exploded/findAllProducts">
    <div class="center">
        <img src="https://static-assets-web.flixcart.com/www/linchpin/fk-cp-zion/img/emptyOrders_f13d28.png"
             alt="empty">
        <%--    <p><b>${message}</b></p>--%>
        <p><b>Order Placed</b></p>
        <input type="submit" value="shop continue" class="btn-primary">
    </div>
</form>
</body>
</html>

