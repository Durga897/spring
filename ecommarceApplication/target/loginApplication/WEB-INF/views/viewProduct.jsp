<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Home</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-primary">
    <a class="navbar-brand" style="color: #FFFFFF">MyCart</a>
    <form class="form-inline ml-auto">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" style="color: #FFFFFF" href="product/getAll"><b>Home</b></a>
            </li>
            <li class="nav-item dropdown" >
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false" style="color: #FFFFFF"><b>${user.userName}</b></a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="user/get">My Profile</a>
                    <a class="dropdown-item" href="order/get">Orders</a>
                    <a class="dropdown-item" href="user/logout">Logout</a>
                </div>
            </li>
            <li>
                <a href="cart/getAll" class="mr-4" style="color: #fb641b;">
                    <svg xmlns="http://www.w3.org/2000/svg" width="27" height="27" fill="currentColor" class="bi bi-cart"
                         viewBox="0 0 16 16">
                        <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"></path>
                    </svg>
                </a>
            </li>
        </ul>
        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
</nav>
<div style="color: white; text-align: center; font-size: 30px;">All Products <i class="fas fa-archive"></i></div>
<br>
<table class="table table-bordered table-hover">
    <thead style="background-color: #ccc;">
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Name</th>
        <th scope="col">Color</th>
        <th scope="col">Stock/Qty</th>
        <th scope="col">Price</th>
        <th scope="col">Description</th>
        <th scope="col">Edit <i class='fas fa-pen-fancy'></i></th>
        <th scope="col">Delete <i class='fas fa-pen-fancy'></i></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${productList }" var="product">
    <tr>
        <td>${product.id}</td>
        <td>${product.name}</td>
        <td>${product.color}</td>
        <td>${product.stock} </td>
        <td>${product.price}</td>
        <td>${product.description}</td>
        <td><a href="findProductById/<c:out value='${product.id}'/>">edit <i class='fas fa-pen-fancy'></i></a></td>
        <td><a href="deleteProduct/${product.id}">delete<i class='fas fa-pen-fancy'></i></a></td>
    </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<br>
<br>
</body>
</html>