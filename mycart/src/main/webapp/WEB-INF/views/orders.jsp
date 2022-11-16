<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Orders</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <style>
        #captcha {
            margin-bottom: 0;
            width: 200px;
            height: 40px;
            font-size: 24px;
            font-weight: bolder;
            text-decoration: line-through;
            display: inline-block;
            background-color: rgb(178, 0, 255);
        }

    </style>

    <script type="text/javascript">
        function showCashPayment() {
            document.getElementById('cardPayment').style.display = 'none';
            document.getElementById('cashPayment').style.display = 'block';
        }

        function showCardPayment() {
            document.getElementById('cardPayment').style.display = 'block';
            document.getElementById('cashPayment').style.display = 'none';
        }

        function generateCaptcha() {
            let alphabet = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
                'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'];
            for (let i = 0; i < 4; i++) {
                var firstAlpha = alphabet[Math.floor(Math.random() * alphabet.length)];
                var secondAlpha = alphabet[Math.floor(Math.random() * alphabet.length)];
                var thirdAlpha = alphabet[Math.floor(Math.random() * alphabet.length)];
                var fourthAlpha = alphabet[Math.floor(Math.random() * alphabet.length)];
            }
            document.getElementById("captcha").value = firstAlpha + '' + secondAlpha + '' + '' + thirdAlpha + '' + fourthAlpha
        }

        function checkValidCaptcha() {
            let captcha = removeSpaces(document.getElementById('captcha').value);
            let captchaValue = removeSpaces(document.getElementById('captchaCode').value);
            if (captcha === captchaValue) {
                <%--let springAttribute = '${orderProduct}';--%>
                window.location.href = '${orderProduct}';
                return true;
            } else {
                alert("Please enter a valid captcha.");
                return false;
            }
        }

        function removeSpaces(string) {
            return string.split(' ').join('');
        }
    </script>

</head>
<body onload="generateCaptcha();">
<nav class="navbar navbar-light bg-primary justify-content-between fixed-top">
    <a class="navbar-brand" style="color: #FFFFFF">MyCart</a>
    <form class="form-inline">
        <p class="mr-4" style="color: #FFFFFF"><b>${user.get().userName}</b></p>
    </form>
</nav>
<br><br><br>
<div class="row mt-5 mx-2">
    <div class="col-md-6">
        <div class="card">
            <div class="card-header">
                <h5 class="card-title"><b>Login</b></h5>
                <p>${user.get().userName}&nbsp;&nbsp;${user.get().mobileNumber}</p>
            </div>
            <div class="card-body">
                <h5 class="card-title"><b>Delivery Address</b></h5>
                <p>${user.get().address}</p>
            </div>
            <div class="card-body">
                <h5 class="card-title"><b>Order Summery</b></h5>
                    <div class="row">
                        <div class="col-md-6">
                            <img class="card-img-top" alt="product"
                                 src="../getProductImage/<c:out value='${cartItems.product.id}'/>"
                                 style="width:90px;height:90px">
                        </div>
                        <div class="col-md-6">
                            <p class="card-text"><b>${cartItems.product.name}</b></p>
                            <p class="card-title">${cartItems.quantity}</p>
                            <p class="card-title"><b>&#8377;${cartItems.price}</b></p>
                        </div>
                    </div>
            </div>
            <div class="card-footer">
                <h5 class="card-title"><b>PAYMENT OPTION</b></h5>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="payment" id="cash"
                           onchange="showCashPayment()" checked>
                    <label class="form-check-label" for="cash">
                        Cash on delivery
                    </label>
                </div>
                <div id="cashPayment" class="justify-content-between mb-4">
                    <div class="row ml-2">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="captcha" class="control-label"></label>
                                <input type="text" id="captcha" class="input-lg form-control" readonly="readonly"/>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="captchaCode" class="control-label"></label>
                                <input id="captchaCode" type="text" class="input-lg form-control"
                                       placeholder="enter the character" required>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <button type="button" class="btn btn-light btn-lg"
                                        style="background-color:#fb641b; color:#FFFFFF; font-size: medium"
                                        onclick="checkValidCaptcha();"><b>CONFIRM ORDER</b></button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="payment" id="creditCard"
                           onchange="showCardPayment()">
                    <label class="form-check-label" for="creditCard">
                        Credit/Debit card
                    </label>
                </div>
                <div id="cardPayment" class="justify-content-between mb-4" style="display: none">
                    <div class="row mt-2 ml-2">
                        <div class="col-md-4 mr-2">
                            <label for="cc-number" class="control-label">CARD NUMBER</label>
                            <input id="cc-number" type="tel" class="input-lg form-control cc-number"
                                   autocomplete="cc-number"
                                   placeholder="&bull;&bull;&bull;&bull; &bull;&bull;&bull;&bull; &bull;&bull;&bull;&bull; &bull;&bull;&bull;&bull;"
                                   required>
                        </div>
                    </div>
                    <div class="row ml-2">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="cc-exp" class="control-label">CARD EXPIRY</label>
                                <input id="cc-exp" type="tel" class="input-lg form-control cc-exp" autocomplete="cc-exp"
                                       placeholder="&bull;&bull; / &bull;&bull;" required>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="cc-cvc" class="control-label">CARD CVC</label>
                                <input id="cc-cvc" type="tel" class="input-lg form-control cc-cvc" autocomplete="off"
                                       placeholder="&bull;&bull;&bull;&bull;" required>
                            </div>
                        </div>
                    </div>
                    <div class="row  ml-2">
                        <div class="col-md-4">
                            <div class="form-group">
                                <button type="button" class="btn btn-light btn-lg"
                                        style="background-color:#fb641b; color:#FFFFFF; font-size: medium">
                                    <b>&#8377;${cartItems.price} PAY</b></button>
                            </div>
                        </div>
                    </div>
                </div>
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
                    <p class="card-title">Price</p>
                    <h5>&#8377; ${cartItems.price}</h5>
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
                    <h5><b>&#8377; ${cartItems.price}</b></h5>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>