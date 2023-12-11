<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Add movie</title>
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

<h1>Add movie</h1>

<form action="/addMovie" method="post">

    <div>
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" placeholder="Enter the movie name" required>
    </div>

    <div>
        <label for="description">Description:</label>
        <input type="text" id="description" name="description" placeholder="Enter the description" required>
    </div>

    <div>
        <label for="image">Image:</label>
        <input type="text" id="image" name="image" placeholder="Enter the image name" required>
    </div>

    <div>
        <input type="submit" value="Add movie">
    </div>

</form>

</body>
</html>