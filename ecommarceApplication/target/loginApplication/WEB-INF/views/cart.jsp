<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Profile</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <link href="<c:url value="/resources/css/cart.css" />" rel="stylesheet">
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
<div class="row mt-5 mx-2">
    <div class="col-md-6">
        <div class="card">
            <div class="card-header">
                <h5 class="card-title"><b>My Cart</b></h5>
            </div>
            <c:forEach items="${cartItems }" var="list">
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-6">
                            <img class="card-img-top" src="../product/getProductImage/<c:out value='${list.productId}'/>"
                                 style="width:90px;height:90px">
                        </div>
                        <div class="col-md-6">
                            <p class="card-title"><b>${list.productName}</b></p>
                            <p class="card-title"><b>${list.productId}</b></p>
                            <a href="removeCartItem/<c:out value='${list.id}'/>"><b>REMOVE</b></a>
                        </div>
                    </div>
                </div>
                <div class="card-footer">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="quantity">
                                <a href="decreaseProductQuantity/<c:out value='${list.id}'/>/<c:out value='${list.productId}'/>">
                                    <button class="btn minus-btn" type="button"
                                            onclick="this.parentNode.querySelector('input[type=number]').stepDown()">-
                                    </button>
                                </a>
                                <input type="number" id="quantity" value="${list.productQuantity}" min="1"
                                       style="width: 40px;">
                                <a href="increaseProductQuantity/<c:out value='${list.id}'/>/<c:out value='${list.productId}'/>">
                                    <button class="btn plus-btn" type="button"
                                            onclick="this.parentNode.querySelector('input[type=number]').stepUp()">+
                                    </button>
                                </a>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <a href="#" class="btn btn-outline-info"><b>&#8377;${list.productPrice}</b></a>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <div class="card-footer text-right">
            <a href="../order/checkout">
                <button type="button" class="btn btn-light btn-lg" style="background-color:#fb641b; color:#FFFFFF; font-size: medium"><b>&nbsp;&nbsp;PLACE ORDER &nbsp;&nbsp;  </b></button>
            </a>
            </div>
        </div>
    </div>
    <div class="col-md-5 overflow-hidden">
        <div class="card">
            <div class="card-header">
                <p class="card-title">Price Details</p>
            </div>
            <div class="card-body">
                <div class="d-flex justify-content-between mb-4">
                    <p class="card-title">Price(items)</p>
                    <h5>&#8377;${totalAmount} </h5>
                </div>
                <div class="d-flex justify-content-between mb-4">
                    <p class="card-text">Discount</p>
                    <h5 style="color: green">&#8377; 0.00</h5>
                </div>
                <div class="d-flex justify-content-between mb-4">
                    <p class="card-text">Delivery Charges</p>
                    <h5 style="color: green">&#8377; 0.00</h5>
                </div>
            </div>
            <div class="card-footer text-center">
                <div class="d-flex justify-content-between mb-4">
                    <p class="card-text"><b>TotalAmount</b></p>
                    <h5><b>&#8377;${totalAmount} </b></h5>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-6">
    <div class="card">
        <div class="card-header">
            <p class="card-title"><b>${status}</b></p>
        </div>
        <c:forEach items="${cartItem }" var="list">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-6">
                        <img class="card-img-top" src="../getProductImage/<c:out value='${list.productId}'/>"
                             style="width:90px;height:90px">
                    </div>
                    <div class="col-md-6">
                        <p class="card-title"><b>${list.productName}</b></p>
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
    </div>
    <div class="col-md-6">
    </div>
</div>
</body>
</html>
