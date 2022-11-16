<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Home</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <link href="<c:url value="/resources/css/orderReceived.css" />" rel="stylesheet">
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
                <a class="nav-link" style="color: #FFFFFF" href="http://localhost:8080/ecommarceApplication/user/product/view"><b>Add New Product</b></a>
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
<div style="color: white; text-align: center; font-size: 30px;">Orders Received <i class="fas fa-archive"></i></div>
<br>
<table id="customers">
    <tr>
        <th>EmailAddress</th>
        <th>Mobile Number</th>
        <th scope="col">Product Name</th>
        <th scope="col">Quantity</th>
        <th scope="col"><i class="fa fa-inr"></i> Sub Total</th>
        <th>Address</th>
        <th scope="col">Order Date</th>
        <th scope="col">Payment Method</th>
        <th scope="col">Status</th>
        <th scope="col">Cancel order <i class='fas fa-window-close'></i></th>
        <th scope="col">Order Shipped <i class='fas fa-dolly'></i></th>
    </tr>
    <c:forEach items="${orderList}" var="item">
    <tr>
        <td>${item.emailAddress}</td>
        <td>${item.mobileNumber}</td>
        <td>${item.productName}</td>
        <td><i class="fa fa-inr"></i>${item.productQuantity}</td>
        <td>${item.productPrice}</td>
        <td>${item.address}</td>
        <td>${item.orderDate}</td>
        <td>Cash On Delivery</td>
        <td>${item.orderStatus}</td>
        <td><a href="">Cancel <i class='fas fa-window-close'></i></a></td>
        <td><a href="toShipOrder/<c:out value='${item.orderId}'/>/<c:out value='${item.productId}'/>">Shipped <i class='fas fa-dolly'></i></a></td>
    </tr>
    </c:forEach>
</table>
<br>
<br>
<br>
</body>
</html>