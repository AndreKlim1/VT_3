<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Main</title>
    <style>
    .movies-list {
        font-family: Arial, Helvetica, sans-serif;
         margin: 20px;
         }

    .movies-list h1 {
        text-align: center;
        font-size: 36px;

    }

    .movies-list ul {
        list-style: none;
        display: flex;
        flex-wrap: wrap;
        justify-content: space-around;
    }

    .movies-list li {
        width: 300px;
        height: 400px;
        margin: 10px;
        border: 1px solid black;
        box-shadow: 5px 5px 10px grey;
        position: relative;
    }

    .movies-list img {
        width: 100%;
        height: 80%;
        object-fit: cover;
    }

    .movies-list p {
        font-size: 15px;
        font-weight: bold;
        text-align: center;
        margin: 2px;
    }

    .movies-list h2 {
        font-size: 12px;
        font-weight: bold;
        text-align: center;
        margin: 2px;
        color: #166958;
    }

    .movies-list a {
        text-decoration: none;
        color: black;
    }

    .nav-link {
        font-size: 18px;
        display: block;
        text-align: center;
        margin: 5px;
    }

    li:hover {
        transform: scale(1.1);
        transition: 0.5s;
    }
    </style>
    <jsp:include page="fragments/header.jsp"/>
    <link rel="stylesheet" href="style/main.css">
</head>
<body>
<div class="movies-list">

        <h1>Movie list</h1>

        <ul>
            <c:forEach items="${movies}" var="movie">
                <li>
                    <a class="nav-link" href="${pageContext.request.contextPath}/movie-rate?command=goMovieInfo&movieId=${movie.id}">
                        <img src="${movie.image}" alt="${movie.name}">
                    </a>
                    <p>${movie.name}</p>
                    <c:if test="${sessionScope.user != null}">
                        <c:if test="${sessionScope.role.name == 'admin'}">
                            <a class="nav-link" href="${pageContext.request.contextPath}/movie-rate?command=goChangeMovie&movieId=${movie.id}"><h2>Change movie</h2></a>
                            <a class="nav-link" href="${pageContext.request.contextPath}/movie-rate?command=deleteMovie&movieId=${movie.id}"><h2>Delete movie</h2></a>
                        </c:if>
                    </c:if>
                </li>
            </c:forEach>
        </ul>

    </div>
</body>
</html>