<%--
  Created by IntelliJ IDEA.
  User: sours
  Date: 2024-02-20
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
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
    <h1>Login</h1>
</header>
<form action="login" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>
    <input type="submit" value="Login">
</form>
<% if (request.getAttribute("errorMessage") != null) { %>
<p class="error"><%= request.getAttribute("errorMessage") %></p>
<% } %>
<footer>
    <!-- Footer, similar to index.jsp -->
</footer>
</body>
</html>
