<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>view product</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
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
                <a class="nav-link" style="color: #FFFFFF" href="view"><b>Add New Product</b></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" style="color: #FFFFFF" href="findAllProduct"><b>All Product</b></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" style="color: #FFFFFF" href="fetchReceivedOrder"><b>Orders Received</b></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" style="color: #FFFFFF" href=""><b>Order Shipped</b></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" style="color: #FFFFFF" href="logout"><b>Logout</b></a>
            </li>
        </ul>
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