<jsp:useBean id="message" class="java.lang.String" scope="application"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <%--    <script src="validation.js"></script>--%>
    <%--    <link rel="stylesheet" href="index.css">--%>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }
        .center {
            margin-top: 30px;
            margin-left: auto;
            margin-right: auto;
            text-align: center;
            max-width: 430px;
            width: 100%;
            padding: 30px;
            border-radius: 6px;
            background: #FFF;
        }

        input {
            margin-top: 20px;
            width: 250px;
            height: 30px;
            border-radius: 5px;
            font-size: 16px;
            border: 1px solid #CACACA;
            padding: 0 15px;
        }

        #login {
            margin-top: 25px;
            width: 200px;
            height: 30px;
            border: none;
            border-radius: 17px;
            padding-left: 7px;
            color: white;
            background-color: cornflowerblue;
            font-size: 15px;

        }
        .container {
            height: 100vh;
            width: 100%;
            display: flex;
            align-items: center;
            justify-content: center;
            background-color: #bec4ee;
            column-gap: 30px;
        }
    </style>
    <script type="text/javascript">
        function isNumber(event) {
            if (event.charCode >= 48 && event.charCode <= 57) {
                return true;
            } else {
                alert("please enter only numbers")
                return false;
            }
        }

        function testValidations() {
            let userNameExpression = /^[A-Za-z]+$/;
            let passwordExpression = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,30}/;
            let emailAddressExpression = /^[A-Za-z0-9_]{3,}@[A-Za-z]{3,}[.]{1}[A-Za-z]{2,6}$/;
            let userName = document.forms["registrationForm"]["userName"].value;
            let password = document.forms["registrationForm"]["password"].value;
            let email = document.forms["registrationForm"]["emailAddress"].value;
            let mobileNumber = document.forms["registrationForm"]["mobileNumber"].value;
            if (userName === '') {
                alert('username is required');
                return false;
            }
            if (!userName.match(userNameExpression)) {
                alert('Invalid username');
                return false;
            }
            if (email === '') {
                alert("email address is required")
                return false;
            }
            if (!email.match(emailAddressExpression)) {
                alert("Invalid email address")
                return false;
            }
            if (mobileNumber === '') {
                alert("phone number is required");
                return false;
            }
            if (isNaN(mobileNumber)) {
                alert("mobile number must contain numbers only");
                return false;
            }
            if ((mobileNumber.indexOf(9) !== 0) && (mobileNumber.indexOf(8) !== 0) && (mobileNumber.indexOf(7) !== 0)) {
                alert('Invalid Mobile Number')
                return false;
            }
            if (mobileNumber.length > 10 || mobileNumber.length < 10) {
                alert("phone number must contain 10 digits ");
                return false;
            }
            if (password === '') {
                alert('Password is required');
                return false;
            }
            if (!password.match(passwordExpression)) {
                alert('Password must contain at least one uppercase,lowercase,number and special character');
                return false;
            } else {
                document.forms["registrationForm"].submit();
            }
        }
    </script>
</head>
<body>
<form action="register" class="container" method="POST" name="registrationForm">
    <div class="center">
        <h2>SignUp</h2><br>
        <label for="userName"></label>
        <input name="userName" id="userName" placeholder="username" type="text"><br>
        <label for="emailAddress"></label>
        <input name="emailAddress" id="emailAddress" placeholder="email" type="text"><br>
        <label for="mobileNumber"></label>
        <input name="mobileNumber" id="mobileNumber" placeholder="mobile number" type="text" maxlength="10"
               onkeypress="return isNumber(event)"><br>
        <label for="password"></label>
        <input name="password" id="password" placeholder="password" type="password" minlength="8"><br>
        <input id="login" value="Sign Up" type="submit" onclick="return testValidations()"><br><br>
    </div>
</form>
</body>
</html>