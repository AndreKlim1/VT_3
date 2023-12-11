<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <style>
  .container {
    font-family: Arial, sans-serif;
    background-color: #f2f2f2;
    padding: 20px;
  }

  .header {
    background-color: #333;
    color: #fff;
    text-align: center;
    padding: 10px;
  }

  h1 {
    margin: 0;
  }

  .content {
    background-color: #fff;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  }

  h2 {
    margin-top: 30px;
  }

  img {
    max-width: 100%;
    height: auto;
    display: block;
    margin: 0 auto;
  }

  .review {
    margin-top: 30px;
  }

  textarea {
    width: 100%;
    padding: 10px;
    border-radius: 5px;
    border: none;
    margin-bottom: 10px;
  }

  label {
    display: block;
    margin-bottom: 5px;
  }

  input[type="number"] {
    width: 100%;
    padding: 10px;
    border-radius: 5px;
    border: none;
    margin-bottom: 10px;
  }

  input[type="submit"] {
    background-color: #333;
    color: #fff;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
  }

  input[type="submit"]:hover {
    background-color: #555;
  }

  .reviews {
    margin-top: 30px;
  }

  .review-author {
    margin-top: 30px;
  }

  .review-status {
    margin-top: 10px;
    font-style: italic;
    color: #8a206a;
  }

  .review-rating {
    margin-top: 10px;
    color: #369413;
    font-style: bold;
  }

  .review-text {
    margin-top: 10px;
  }
  </style>
  <jsp:include page="fragments/header.jsp"/>
</head>
<body>

  <div class="container">

      <div class="header">
        <h1><c:out value="${movie.name}"></c:out></h1>
      </div>

      <div class="content">

          <h2>Poster</h2>
            <img src="${movie.image}"></img>

          <h2>Description</h2>
            <c:out value="${movie.description}"></c:out>

          <h2>Average Rating</h2>
            <c:out value="${movie.averageRating}"></c:out>

          <hr style="size: 10px; width: 1600px; margin: auto; color: gray;">

          <div class="review">
            <c:if test="${sessionScope.user != null}">
              <c:if test="${sessionScope.user.banned != true}">

                <form <c:if test="${feedback == null}">action="/addFeedback&movieId=${movie.id}"</c:if>
                      <c:if test="${feedback != null}">action="/changeFeedback&movieId=${movie.id}"</c:if>
                      method="post">
                <textarea type="text" name="content" placeholder="Enter the content"><c:out value="${feedback.content}"></c:out></textarea>
                  <div>
                    <label for="rating">Rating:</label>
                    <input type="number" name="rating" value="${feedback.rating}" min="1" max="10" placeholder="Rating">
                  </div>

                  <div>
                    <input type="submit" value="Leave review">
                  </div>
                </form>
                <hr style="size: 10px; width: 1600px; margin: auto; color: gray;">
              </c:if>
            </c:if>
          </div>


        <div class="reviews">
          <h2><c:out value="${movie.feedbackAmount}"></c:out> reviews</h2>

            <c:forEach items="${reviews}" var="review">
              <hr style="size: 10px; width: 800px; margin: auto; color: gray;">
              <div class="review-author">
                <h3>Review from ${review.user.nickname}</h3>
              </div>

              <div class="review-status">
              <div class="dropdown">
                <button class="dropbtn"><c:out value="${review.status.name}"></c:out></button>
                <div class="dropdown-content">
                <c:forEach items="${statuses}" var="status">
                  <a class="nav-link" href="/changeUserStatus&movieId=${movie.id}&statusId=${status.id}&userId=${review.user.id}"><c:out value="${status.name}"></c:out></a>
                </c:forEach>
                </div>
              </div>

              </div>

              <div class="review-rating">
                <p>Rating: ${review.feedback.rating}</p>
              </div>

              <div class="review-text">
                <c:out value="${review.feedback.content}"></c:out>
              </div>

              <div class="ban-container">
                <a class="nav-link" href="/banUser&userId=${review.user.id}">Log out</a>
              </div>

            </c:forEach>
        </div>

      </div>

    </div>
</body>
</html>