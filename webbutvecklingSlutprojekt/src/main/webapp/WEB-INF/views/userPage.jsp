<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="servlets.UserPageServlet.Course" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Your Courses</title>
</head>
<body>
<h1>Your Enrolled Courses</h1>

<c:choose>
    <c:when test="${not empty studentCourses}">
        <table border="1">
            <tr>
                <th>Course ID</th>
                <th>Course Name</th>
                <th>Teacher</th>
            </tr>
            <c:forEach var="course" items="${studentCourses}">
                <tr>
                    <td>${course.id}</td>
                    <td>${course.name}</td>
                    <td>${course.teacherName}</td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <p>You are not currently enrolled in any courses.</p>
    </c:otherwise>
</c:choose>

</body>
</html>
