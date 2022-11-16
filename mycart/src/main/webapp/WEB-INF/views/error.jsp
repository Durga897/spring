<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>error</title>
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
    <form class="form-inline" method="get" action="/mycart_war_exploded/searchProduct">
        <p class="mr-4" style="color: #FFFFFF"><b>${user.get().userName}</b></p>
        <input class="form-control mr-sm-2" type="search" placeholder="Search" name="product" id="product" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
</nav>
<br><br><br>
<div class="center">
    <img src="https://static-assets-web.flixcart.com/www/linchpin/fk-cp-zion/img/emptyOrders_f13d28.png" alt="empty">
    <p><b>${message}</b></p>
    <button class="btn-primary">
        <a href="findAllProducts" style="color: #FFFFFF">${shopContinue}</a></button>
</div>
<div class="card">
    <div class="card-header">
        <p class="card-title"><b>${status}</b></p>
    </div>
<c:forEach items="${cartItem }" var="list">
    <div class="card-body">
        <div class="row">
            <div class="col-md-6">
                <img class="card-img-top" src="getProductImage/<c:out value='${list.product.id}'/>"
                     style="width:90px;height:90px">
            </div>
            <div class="col-md-6">
                <p class="card-title"><b>${list.product.name}</b></p>
                <p class="card-title" style="color:red;"><b>Out Of Stock</b></p>
                <a href="removeCartItem/<c:out value='${list.id}'/>"><b>REMOVE</b></a>
            </div>
        </div>
    </div>
    <div class="card-footer">
        <div class="row">
        </div>
    </div>
</c:forEach>
</div>
</body>
</html>
