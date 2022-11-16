<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="logoutMessage" scope="application" class="java.lang.String"/>
<jsp:useBean id="message" class="java.lang.String" scope="application"/>
<jsp:useBean id="deleteMessage" class="java.lang.String" scope="application"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <link href="<c:url value="/resources/css/editProduct.css" />" rel="stylesheet">
    <script type="text/javascript">
        function isNumber(event) {
            return event.charCode >= 48 && event.charCode <= 57;
        }
    </script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-primary">
    <a class="navbar-brand" style="color: #FFFFFF">MyCart</a>
</nav>
<div class="row">
    <div class="col-sm-4">
    </div>
    <div class="col-sm-4">
        <div class="tab-content" style="text-align: center">
            <form action="/ecommarceApplication/product/update" method="post" class="container"
                  name="registrationForm">
                <div id="logins" class="tab-pane active">
                    <h2>Edit Product</h2><br>
                    <label for="id"></label>
                    <b>Product id:</b><input name="id" id="id" type="text"
                                             style="border:none;color: #445fd5;font-weight: bold;" value="${product.id}"
                                             readonly><br>
                    <label for="price"></label>
                    <input name="price" id="price" placeholder="Price" type="text" onkeypress="return isNumber(event)"
                           value="${product.price}" required><br>
                    <label for="stock"></label>
                    <input name="stock" id="stock" placeholder="Quantity" type="text"
                           onkeypress="return isNumber(event)" value="${product.stock}" required><br>
                    <label for="color"></label>
                    <input name="color" id="color" placeholder="Color" type="text" value="${product.color}"
                           required><br>
                    <label for="description"></label>
                    <input name="description" id="description" placeholder="Description" type="text"
                           value="${product.description}" required><br>
                    <input id="update" value="Update" type="submit"><br><br>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>