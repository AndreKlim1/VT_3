<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Log In</title>
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
    <link rel="stylesheet" href="login.css">
</head>
<body>
  <h1>Login</h1>
  <form action="${pageContext.request.contextPath}/movie-rate?command=logIn" method="post">
    <input type="email" name="email" placeholder="Email">
    <input type="password" name="password" placeholder="Password" minlength="8">
    <input type="submit" value="Login">
  </form>


  <p class="error">Login error</p>
</body>
</html>