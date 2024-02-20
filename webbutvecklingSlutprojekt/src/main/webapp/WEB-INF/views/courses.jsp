<%@ page import="servlets.CoursesServlet" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: sours
  Date: 2024-02-20
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Available Courses</title>
    <link rel="stylesheet" href="css/style.css"> <!-- Ensure you have this CSS file -->
</head>
<body>
<h1>Available Courses</h1>
<%
    List<CoursesServlet.Course> courses = (List<CoursesServlet.Course>) request.getAttribute("courses");
    if (courses != null && !courses.isEmpty()) {
        for (CoursesServlet.Course course : courses) {
%>
<div>
    <h2><%= course.getName() %></h2>
    <p><%= course.getDescription() %></p>
</div>
<%
    }
} else {
%>
<p>No courses available.</p>
<%
    }
%>
</body>
</html>
