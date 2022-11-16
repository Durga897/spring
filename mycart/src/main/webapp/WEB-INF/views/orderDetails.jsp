<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Order Details</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
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
        <input class="form-control mr-sm-2" type="search" placeholder="Search"  name="product" id="product" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
</nav>
<section class="vh-100 gradient-custom-2">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-md-10 col-lg-8 col-xl-8">
                <div class="card card-stepper" style="border-radius: 16px;">
                    <c:forEach items="${orderList}" var="list">
                        <div class="card-header p-4">
                            <div class="d-flex justify-content-between align-items-center">
                                <div>
                                    <p class="text-muted mb-2"> Order ID <span
                                            class="fw-bold text-body">${list.orders.id}</span></p>
                                    <p class="text-muted mb-0"> Place On <span
                                            class="fw-bold text-body">${list.orders.orderedDate}</span></p>
                                </div>
                                <div>
                                    <h6 class="mb-0"><a href="#">View Details</a></h6>
                                </div>
                            </div>
                        </div>
                        <div class="card-body p-4">
                            <div class="d-flex flex-row mb-4 pb-2">
                                <div class="flex-fill">
                                    <h5 class="bold">${list.product.name}</h5>
                                    <p class="text-muted"> Qt: ${list.productQuantity} item</p>
                                    <h5 class="mb-3 text-primary"> &#8377; ${list.productAmount}</h5>
                                </div>
                                <div>
                                    <img class="align-self-center img-fluid" alt="product"
                                         src="getProductImage/<c:out value='${list.product.id}'/>" width="250">
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>