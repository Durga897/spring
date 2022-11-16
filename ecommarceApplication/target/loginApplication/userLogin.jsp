<jsp:useBean id="logoutMessage" scope="application" class="java.lang.String"/>
<jsp:useBean id="message" class="java.lang.String" scope="application"/>
<jsp:useBean id="deleteMessage" class="java.lang.String" scope="application"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <%--    <script src="validation.js"></script>--%>
    <%--    <link rel="stylesheet" href="index.css">--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <style>
        * {
            margin: 0;
            padding: 0;
            font-family: 'Poppins', sans-serif;
        }
        #logins {
            margin-top: 30px;
            margin-left: auto;
            margin-right: auto;
            text-align: center;
            max-width: 430px;
            width: 100%;
            padding: 30px;
            box-shadow: 0 30px 60px 0 rgba(0,0,0,0.3);
            border-radius: 6px;
            background: #ffffff;
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
        h2 {
            text-align: center;
            font-size: 22px;
            font-weight: 600;
            display:inline-block;
            margin: 40px 8px 10px 8px;
            color: #1f1e1e;
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
        /*.container {*/
        /*    height: 100vh;*/
        /*    width: 100%;*/
        /*    display: flex;*/
        /*    align-items: center;*/
        /*    justify-content: center;*/
        /*    background-color: #bec4ee;*/
        /*    column-gap: 30px;*/
        /*}*/
        /*html {*/
        /*    background-color: #56baed;*/
        /*}*/

        /*body {*/
        /*    font-family: "Poppins", sans-serif;*/
        /*    height: 100vh;*/
        /*}*/

        /*a {*/
        /*    color: #92badd;*/
        /*    display:inline-block;*/
        /*    text-decoration: none;*/
        /*    font-weight: 400;*/
        /*}*/

        /*h2 {*/
        /*    text-align: center;*/
        /*    font-size: 16px;*/
        /*    font-weight: 600;*/
        /*    text-transform: uppercase;*/
        /*    display:inline-block;*/
        /*    margin: 40px 8px 10px 8px;*/
        /*    color: #cccccc;*/
        /*}*/
        /*#formContent {*/
        /*    -webkit-border-radius: 10px 10px 10px 10px;*/
        /*    border-radius: 10px 10px 10px 10px;*/
        /*    background: #fff;*/
        /*    padding: 30px;*/
        /*    width: 90%;*/
        /*    max-width: 450px;*/
        /*    position: relative;*/
        /*    padding: 0px;*/
        /*    -webkit-box-shadow: 0 30px 60px 0 rgba(0,0,0,0.3);*/
        /*    box-shadow: 0 30px 60px 0 rgba(0,0,0,0.3);*/
        /*    text-align: center;*/
        /*}*/

        /*#formFooter {*/
        /*    background-color: #f6f6f6;*/
        /*    border-top: 1px solid #dce8f1;*/
        /*    padding: 25px;*/
        /*    text-align: center;*/
        /*    -webkit-border-radius: 0 0 10px 10px;*/
        /*    border-radius: 0 0 10px 10px;*/
        /*}*/
        /*h2.inactive {*/
        /*    color: #cccccc;*/
        /*}*/

        /*h2.active {*/
        /*    color: #0d0d0d;*/
        /*    border-bottom: 2px solid #5fbae9;*/
        /*}*/
        /*input[type=button], input[type=submit], input[type=reset]  {*/
        /*    background-color: #56baed;*/
        /*    border: none;*/
        /*    color: white;*/
        /*    padding: 15px 80px;*/
        /*    text-align: center;*/
        /*    text-decoration: none;*/
        /*    display: inline-block;*/
        /*    text-transform: uppercase;*/
        /*    font-size: 13px;*/
        /*}*/
        /*input[type=button]:hover, input[type=submit]:hover, input[type=reset]:hover  {*/
        /*    background-color: #39ace7;*/
        /*}*/

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
            let passwordExpression = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,30}/;
            let emailAddressExpression = /^[A-Za-z0-9_]{3,}@[A-Za-z]{3,}[.]{1}[A-Za-z]{2,6}$/;
            let password = document.forms["loginForm"]["password"].value;
            let userId = document.forms["loginForm"]["id"].value;

            if (userId === '') {
                alert("userId is required")
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
                document.forms["loginForm"].submit();
            }
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
            <form class="container">
            <div id="logins" class="tab-pane active">
                <ul class="nav nav-tabs">
                    <li class="nav-item"><a href="userLogin.jsp" style="color: #445fd5" class="nav-link"> User Login</a></li>
                    <li class="nav-item"><a href="adminLogin.jsp" class="nav-link" style="color: #445fd5" >Admin Login</a></li>
                </ul>
                    <h2>User Login</h2><br>
                    <label for="id"></label>
                    <input name="id" id="id" placeholder="User Id" type="text"><br>
                    <label for="password"></label>
                    <input name="password" id="password" placeholder="password" type="password" ><br>
                    <input id="login" value="Sign In" type="submit" onclick="return testValidations()"><br><br>
                    <br>
                    Dont have an account? <a href="registrationForm.jsp">create an account</a><br><br>
            </div>
            </form>
            </div>
        </div>
</div>
</body>
</html>