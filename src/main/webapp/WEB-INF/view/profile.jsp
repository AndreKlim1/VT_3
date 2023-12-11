<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Profile</title>
    <style>
    body {
          background-color: #f2f2f2;
          font-family: 'Open Sans', sans-serif;
          font-size: 16px;
        }

        input[type="text"] {
          border-radius: 10px;
          border: none;
          padding: 10px;
          background-color: #f5f5f5;
        }

        button[type="submit"] {
          border-radius: 10px;
          border: none;
          padding: 10px 20px;
          background-color: #333;
          color: #fff;
        }

        .movies-list li {
          list-style: none;
          margin-bottom: 20px;
          padding: 20px;
          border-radius: 10px;
          box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

    .logout-container {
        margin-top: 20px;
    }

    .logout-container a {
        width: 100%;
        height: 40px;
        border-radius: 5px;
        background-color: red;
        color: white;
        font-size: 16px;
        font-weight: bold;
    }

    .logout-container a:hover {
        background-color: white;
        border: 1px solid red;
        color: red;
    }
    </style>
    <jsp:include page="fragments/header.jsp"/>

</head>
<body>

  <div class="main-container">

      <div class="profile-info">

          <h1>Profile</h1>

          <div class="left-info">
              <form action="/changeNickname" method="post">
                  <input type="text" name="nickname" value="${sessionScope.user.nickname}" placeholder="Enter new nickname">
                  <input type="submit" value="Change nickname">
              </form>


              <div class="email-container">
                  <p>Email: <c:out value="${sessionScope.user.email}"/></p>
              </div>

              <div class="logout-container">
                  <a class="nav-link" href="/logout">Log out</a>
              </div>
          </div>

          <div class="right-info">
              <div class="status-container">
                  <p>Status: <c:out value="${status.name}"/></p>
              </div>

              <div class="score-container">
                  <p>Current score: <c:out value="${sessionScope.user.score}"/></p>
              </div>
          </div>
      </div>

      <div class="movies-list">

          <h2>Movie list</h2>
            <c:forEach items="${movies}" var="movie">
                <li>
                    <a class="nav-link" href="/goMovieInfo&movieId=${movie.id}">
                        <img src="${movie.image}" alt="${movie.name}">
                    </a>
                    <p>${movie.name}</p>
                </li>
            </c:forEach>

      </div>

  </div>

</body>
</html>