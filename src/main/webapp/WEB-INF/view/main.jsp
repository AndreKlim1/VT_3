<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Main</title>
    <style>
    body {
        font-family: "Roboto", sans-serif;
    }

    .main-container {
        width: 400px;
        margin: 0 auto;
    }
    .movies-list {
        margin-top: 20px;
    }

    .movies-list h2 {
        margin-bottom: 20px;
    }

    .movies-list ul {
        list-style-type: none;
        padding: 0;
    }

    .movies-list li {
        margin-bottom: 10px;
    }

    .movies-list li img {
        width: 100px;
    }

    .movies-list li p {
        margin-top: 10px;
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
                            <a class="nav-link" href="${pageContext.request.contextPath}/movie-rate?command=goChangeMovie&movieId=${movie.id}">Change movie</a>
                            <a class="nav-link" href="${pageContext.request.contextPath}/movie-rate?command=deleteMovie=${movie.id}">Delete movie</a>
                        </c:if>
                    </c:if>
                </li>
            </c:forEach>
        </ul>

    </div>
</body>
</html>