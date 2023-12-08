<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <style>
    header {
      background-color: #008000;
      color: #ffffff;
      display: flex;
      justify-content: flex-start;
      align-items: center;
      height: 50px;
    }

    h1 {
      font-size: 2em;
      font-family: 'Roboto', sans-serif;
      margin-left: 30px;
    }

    a {
      color: #ffffff;
      text-decoration: none;
      font-size: 1.5em;
      font-family: 'Roboto', sans-serif;
      margin-left: 50px;
      margin-right: 50px;

      /* Наведение */
      &:hover {
        text-decoration: none;
      }
    }
    </style>
    <jsp:include page="headerLinks.jsp"/>
    <link href="header.css" rel="stylesheet" />
</head>

<header>
  <h1>Cinema Site</h1>

  <a class="nav-link" href="${pageContext.request.contextPath}/movie-rate?command=goMain">Main</a>
  <c:if test="${sessionScope.user == null}">
    <a class="nav-link" href="${pageContext.request.contextPath}/movie-rate?command=goLogIn">LogIn</a>
    <a class="nav-link" href="${pageContext.request.contextPath}/movie-rate?command=goLogUp">SighUp</a>
  </c:if>

  <c:if test="${sessionScope.user != null}">
    <a class="nav-link" href="${pageContext.request.contextPath}/movie-rate?command=goProfile">Profile</a>

    <c:if test="${sessionScope.role.name == 'admin'}">
      <a class="nav-link" href="${pageContext.request.contextPath}/movie-rate?command=goAddMovie">Add movie</a>
    </c:if>

  </c:if>
</header>