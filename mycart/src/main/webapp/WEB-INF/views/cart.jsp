<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Profile</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js"
            integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js"
            integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy"
            crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-primary">
    <a class="navbar-brand" style="color: #FFFFFF">MyCart</a>
    <form class="form-inline ml-auto" method="get" action="/mycart_war_exploded/searchProduct">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" style="color: #FFFFFF" href="findAllProducts"><b>Home</b></a>
            </li>
            <li class="nav-item dropdown" >
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false" style="color: #FFFFFF"><b>${user.get().userName}</b></a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="fetchUser">My Profile</a>
                    <a class="dropdown-item" href="fetchOrders">Orders</a>
                    <a class="dropdown-item" href="logout">Logout</a>
                </div>
            </li>
            <li>
                <a href="fetchShoppingCartItems" class="mr-4" style="color: #fb641b;">
                    <svg xmlns="http://www.w3.org/2000/svg" width="27" height="27" fill="currentColor" class="bi bi-cart"
                         viewBox="0 0 16 16">
                        <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"></path>
                    </svg>
                </a>
            </li>
        </ul>
        <input class="form-control mr-sm-2" type="search" placeholder="Search" name="product" id="product" aria-label="Search">
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
                            <img class="card-img-top" src="getProductImage/<c:out value='${list.product.id}'/>"
                                 style="width:90px;height:90px">
                        </div>
                        <div class="col-md-6">
                            <p class="card-title"><b>${list.product.name}</b></p>
                            <p class="card-title"><b>${list.product.color}</b></p>
                            <a href="removeCartItem/<c:out value='${list.id}'/>"><b>REMOVE</b></a>
                        </div>
                    </div>
                </div>
                <div class="card-footer">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="quantity">
                                <a href="decreaseCartItemQuantity/<c:out value='${list.product.id}'/>">
                                <button class="btn minus-btn" type="button"
                                            onclick="this.parentNode.querySelector('input[type=number]').stepDown()">-
                                    </button>
                                </a>
                                <input type="number" id="quantity" value="${list.quantity}" min="1"
                                       style="width: 40px;">
                                <a href="increaseCartItemQuantity/<c:out value='${list.product.id}'/>">
                                    <button class="btn plus-btn" type="button"
                                            onclick="this.parentNode.querySelector('input[type=number]').stepUp()">+
                                    </button>
                                </a>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <a href="#" class="btn btn-outline-info"><b>&#8377;${list.price}</b></a>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <div class="card-footer text-right">
            <a href="checkout">
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
        </div>
        <div class="col-md-6">
        </div>
</div>
</body>
</html>
