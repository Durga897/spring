<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Profile</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-light bg-primary justify-content-between">
    <a class="navbar-brand" style="color: #FFFFFF">MyCart</a>
    <form class="form-inline">
        <p class="mr-4" style="color: #FFFFFF"><b>${user.userName}</b></p>
        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
</nav>
<div class="row mt-3 mx-2">
    <div class="col-md-5">
        <div class="card">
            <img class="card-img-top" src="../getProductImage/<c:out value='${product.id}'/>">
            <div class="card-footer text-center">
                <a href="">
                    <button type="button" class="btn btn-light btn-lg"
                            style="background-color:#ff9f00; color:#FFFFFF; font-size: medium" formaction=""><b>NOTIFY ME</b></button>
                </a>
            </div>
        </div>
    </div>
    <div class="col-md-6">
        <div class="row">
            <div class="card">
                <div class="card-body">
                    <p class="card-title"><b>${product.name}</b></p>
                    <p class="card-text"><b>&#8377;${product.price}</b></p>
                    <p class="card-text">${product.description}</p>
                    <br>
                    <h1>Sold Out</h1>
                    <p style="color: #bec4ee">This item is currently out of stock</p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
