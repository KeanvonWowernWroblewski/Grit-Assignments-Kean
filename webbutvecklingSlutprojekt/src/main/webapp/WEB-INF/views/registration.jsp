<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Assign Teachers and Students to Courses</h1>

<!-- Form to assign a teacher to a course -->
<form action="register" method="post">
    <h2>Assign Teacher to Course</h2>
    <input type="hidden" name="actionType" value="assignTeacher">
    <label for="teachersId">Teacher:</label>
    <select id="teachersId" name="teachersId">
        <% List<String> teachers = (List<String>) request.getAttribute("teachers");
            for(String teacher : teachers) {
                out.println("<option value=\"" + teacher.split(" - ")[0] + "\">" + teacher + "</option>");
            }
        %>
    </select>

    <label for="teacherCourseId">Course:</label>
    <select id="teacherCourseId" name="courseId">
        <% List<String> courses = (List<String>) request.getAttribute("courses");
            for(String course : courses) {
                out.println("<option value=\"" + course.split(" - ")[0] + "\">" + course + "</option>");
            }
        %>
    </select>

    <input type="submit" value="Assign Teacher">
</form>

<!-- Form to assign a student to a course -->
<form action="register" method="post">
    <h2>Assign Student to Course</h2>
    <input type="hidden" name="actionType" value="assignStudent">
    <label for="studentId">Student:</label>
    <select id="studentId" name="studentId">
        <% List<String> students = (List<String>) request.getAttribute("students");
            for(String student : students) {
                out.println("<option value=\"" + student.split(" - ")[0] + "\">" + student + "</option>");
            }
        %>
    </select>

    <label for="studentCourseId">Course:</label>
    <select id="studentCourseId" name="courseId">
        <% // Reusing the courses list populated above
            for(String course : courses) { // 'courses' list already fetched
                out.println("<option value=\"" + course.split(" - ")[0] + "\">" + course + "</option>");
            }
        %>
    </select>

    <input type="submit" value="Assign Student">
</form>

</body>
</html>
