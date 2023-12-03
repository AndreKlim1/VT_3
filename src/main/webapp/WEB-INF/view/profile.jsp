<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Profile</title>
    <jsp:include page="fragments/header.jsp"/>
    <link rel="stylesheet" href="style/profile.css">
</head>
<body>

<div class="main-container">

    <div class="profile-info">

        <h1>Profile</h1>

        <div class="left-info">
            <div class="nickname-container">
                <input type="text" name="nickname" value="${sessionScope.user.nickname}" placeholder="Enter new nickname">
                <button type="submit">Confirm</button>
            </div>

            <div class="email-container">
                <p>Email: <c:out value="${sessionScope.user.email}"/></p>
            </div>

            <div class="logout-container">
                <a href="logout">Log out</a>
            </div>
        </div>

        <div class="right-info">
            <div class="status-container">
                <p>Status: <c:out value="${status.name}"/></p>
            </div>

            <div class="score-container">
                <p>Current score: <c:out value="${sessionScope.user.score}"/></p>
                <p>Points to next status: <c:out value="${status.score}"/></p>
            </div>
        </div>

    </div>

    <div class="movies-list">

        <h2>Movie list</h2>

        <ul>
            <c:forEach items="${movies}" var="movie">
                <li>
                    <a href="${movie.image}">
                        <img src="${movie.image}" alt="${movie.name}">
                    </a>
                    <p><c:out value=${movie.name}/></p>
                </li>
            </c:forEach>
        </ul>

    </div>

</div>

</body>
</html>