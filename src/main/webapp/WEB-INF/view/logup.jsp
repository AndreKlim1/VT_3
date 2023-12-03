<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Sign Up</title>
    <style>
        body {
          font-family: sans-serif;
          background-color: #fff;
        }

        h1 {
          text-align: center;
        }

        form {
          width: 300px;
          margin: 0 auto;
        }

        input {
          width: 100%;
          padding: 10px;
          border: 1px solid #ccc;
        }

        input[type="submit"] {
          background-color: #000;
          color: #fff;
          cursor: pointer;
        }

        .error {
          color: red;
          font-size: 16px;
          margin-top: 10px;
        }
        </style>
    <jsp:include page="fragments/headerLinks.jsp"/>
    <link rel="stylesheet" href="style/logup.css">
</head>
<body>

<h1>Registration</h1>

<form action="${pageContext.request.contextPath}/movie-rate?command=logUp" method="post">

    <div>
        <label for="nickname">Nickname:</label>
        <input type="text" id="nickname" name="nickname" required>
    </div>

    <div>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
    </div>

    <div>
        <label for="password-first">Password:</label>
        <input type="password" id="password-first" name="password-first" required>
    </div>

    <div>
        <label for="password-second">Repeat password:</label>
        <input type="password" id="password-second" name="password-second" required>
    </div>

    <div>
        <input type="submit" value="Register">
    </div>

</form>

</body>
</html>