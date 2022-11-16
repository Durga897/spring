<jsp:useBean id="updateMessage" class="java.lang.String" scope="application"/>
<jsp:useBean id="emailAddress" class="java.lang.String" scope="application"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Profile</title>
    <style>
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
    </style>
</head>
<body>
<form action="fetch" method="post">
    <div class="center">
        <p style="color:green;font-size:15px" >${updateMessage}</p><br>
        <h1>Welcome</h1>
        <h3>${emailAddress}</h3>
        <h3>${userName}</h3>
        <h3>${users.id}</h3>
        <input type="hidden" value="${emailAddress}" name="emailAddress" readonly><br><br>
        <input type="submit" value="update">
        <button type="submit" formaction="delete">delete</button><br><br>
        <button type="submit" formaction="logout" formmethod="get">logout</button>
    </div>
</form>
</body>
</html>
