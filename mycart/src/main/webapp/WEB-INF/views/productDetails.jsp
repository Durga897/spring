<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Product Details</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-primary">
    <a class="navbar-brand" style="color: #FFFFFF">MyCart</a>
    <form class="form-inline ml-auto" method="get" action="/mycart_war_exploded/searchProduct">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" style="color: #FFFFFF" href="../findAllProducts"><b>Home</b></a>
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
                <a href="../fetchShoppingCartItems" class="mr-4" style="color: #fb641b;">
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
<div class="row mt-3 mx-2">
    <div class="col-md-5">
        <div class="card">
            <img class="card-img-top" src="../getProductImage/<c:out value='${product.id}'/>">
            <div class="card-footer text-center">
                <a href="../addToCart/<c:out value='${product.id}'/>">
                    <button type="button" class="btn btn-light btn-lg"
                            style="background-color:#ff9f00; color:#FFFFFF; font-size: medium" formaction=""><b>ADD TO CART</b></button>
                </a>
                <a href="../checkout/<c:out value='${product.id}'/>">
                    <button type="button" class="btn btn-light btn-lg" style="background-color:#fb641b; color:#FFFFFF; font-size: medium"><b>BUY NOW</b></button>
                </a>
            </div>
        </div>
    </div>
    <div class="col-md-6">
        <div class="row">
            <div class="card">
                <div class="card-body">
                    <p class="card-title"><b>${product.name}</b></p>
                    <p class="card-text">${product.color}</p>
                    <p class="card-text"><b>&#8377;${product.price}</b></p>
                    <p class="card-text">${product.description}</p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
