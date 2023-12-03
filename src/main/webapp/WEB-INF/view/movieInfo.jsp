<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Movie</title>
  <link href="style/movieInfo.css" rel="stylesheet" />
  <jsp:include page="fragments/header.jsp"/>
</head>
<body>

  <h1>Movie</h1>

  <h2>Title</h2>
  <c:out value="${movie.name}"></c:out>

  <h2>Poster</h2>
  <img src="${movie.poster}"></img>

  <h2>Description</h2>
  <c:out value="${movie.description}"></c:out>

  <h2>Average Rating</h2>
  <c:out value="${movie.averageRating}"></c:out>

  <h2>Your Ratings</h2>

  <div id="rating">
    <div class="star" data-rating="1"></div>
    <div class="star" data-rating="2"></div>
    <div class="star" data-rating="3"></div>
    <div class="star" data-rating="4"></div>
    <div class="star" data-rating="5"></div>
  </div>

  <form action="/add-review" method="post">
      <input type="text" name="author" placeholder="Enter your name">
      <textarea name="text" placeholder="Enter your review"></textarea>
      <input type="number" name="rating" min="1" max="5" placeholder="Rating">
      <input type="submit" value="Submit Review">
    </form>

  <h2>Reviews</h2>

  <c:forEach items="${movie.reviews}" var="review">
    <h3>Review from ${review.author}</h3>
    <c:out value="${review.text}"></c:out>
    <p>Rating: ${review.rating}</p>
  </c:forEach>



</body>
</html>