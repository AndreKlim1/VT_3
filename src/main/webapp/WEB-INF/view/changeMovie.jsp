<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Change movie</title>
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
    <jsp:include page="fragments/header.jsp"/>
</head>
<body>

<h1>Change movie</h1>

<form action="${pageContext.request.contextPath}/movie-rate?command=changeMovie&movieId=${movie.id}" method="post">

    <div>
        <label for="name">Name:</label>
        <input type="text" id="name" value="${movie.name}" placeholder="Enter the movie name" name="name" required>
    </div>

    <div>
        <label for="description">Description:</label>
        <input type="text" id="description" value="${movie.description}" placeholder="Enter the description" name="description" required>
    </div>

    <div>
        <label for="image">Image:</label>
        <input type="text" id="image" value="${movie.image}" placeholder="Enter the image name" name="image" required>
    </div>

    <div>
        <input type="submit" value="Change movie">
    </div>

</form>

</body>
</html>