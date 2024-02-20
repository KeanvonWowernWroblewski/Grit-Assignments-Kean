<%--
  Created by IntelliJ IDEA.
  User: sours
  Date: 2024-02-20
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Grit Academy Portal</title>
    <link rel="stylesheet" href="css/style.css"> <!-- Ensure you have this CSS file -->
</head>
<body>
<nav>
    <ul>
        <li><a href="index">Home</a></li>
        <li><a href="courses">Courses</a></li>
        <% if (session.getAttribute("userType") == null) { %>
        <li><a href="login">Login</a></li>
        <% } else { %>
        <li><a href="userPage">User Page</a></li>
        <li><a href="logout">Logout</a></li>
        <% } %>
    </ul>
</nav>
<header>
    <h1>Welcome to the Grit Academy Portal</h1>
</header>
<footer>
    <p>Grit Academy Portal &copy; 2024</p>
</footer>
</body>
</html>