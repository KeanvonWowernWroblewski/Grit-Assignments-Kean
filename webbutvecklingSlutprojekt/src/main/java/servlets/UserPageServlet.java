package servlets;

import util.DatabaseConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserPageServlet", urlPatterns = {"/userPage"})
public class UserPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // Debugging: Print out session attributes
        System.out.println("Debug - UserType: " + session.getAttribute("userType"));
        System.out.println("Debug - UserId: " + session.getAttribute("userId"));

        String userType = (String) session.getAttribute("userType");
        Object userIdObject = session.getAttribute("userId");

        // Check if user is logged in and is of type "student"
        if (!"student".equals(userType)) {
            // If not a student, respond with an unauthorized status
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized access. This page is for students only.");
            return;
        }

        if (!(userIdObject instanceof Integer)) {
            // Handle the case where userId is not set or not an integer
            request.setAttribute("errorMessage", "User ID is not set or invalid. Please log in again.");
            request.getRequestDispatcher("/WEB-INF/views/errorPage.jsp").forward(request, response);
            return;
        }

        int userId = (Integer) userIdObject; // Safely cast to Integer now

        try (Connection connection = DatabaseConnection.getConnection()) {
            List<Course> studentCourses = fetchStudentCourses(connection, userId);
            request.setAttribute("studentCourses", studentCourses);
            request.getRequestDispatcher("/WEB-INF/views/userPage.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database connection error while fetching data for the user page", e);
        }
    }

    private List<Course> fetchStudentCourses(Connection connection, int studentId) throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT c.coursesId, c.name, t.firstName, t.lastName " +
                "FROM courses c " +
                "JOIN studentscourses sc ON c.coursesId = sc.coursesId " +
                "JOIN teacherscourses tc ON c.coursesId = tc.coursesId " +
                "JOIN teachers t ON tc.teachersId = t.teachersId " +
                "WHERE sc.studentsId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Course course = new Course(rs.getInt("coursesId"), rs.getString("name"),
                        rs.getString("firstName") + " " + rs.getString("lastName"));
                courses.add(course);
            }
        }
        return courses;
    }

    public static class Course {
        private int id;
        private String name;
        private String teacherName;

        public Course(int id, String name, String teacherName) {
            this.id = id;
            this.name = name;
            this.teacherName = teacherName;
        }

        // Getters
        public int getId() { return id; }
        public String getName() { return name; }
        public String getTeacherName() { return teacherName; }
    }
}
