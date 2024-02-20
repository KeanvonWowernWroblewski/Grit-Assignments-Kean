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

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to login page
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String sql = "SELECT 'student' AS userType, NULL AS privilegeType FROM Students WHERE username = ? AND password = ? " +
                "UNION " +
                "SELECT 'teacher' AS userType, privilegeType FROM Teachers WHERE username = ? AND password = ?";


        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, username);
            stmt.setString(4, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String userType = rs.getString("userType");
                String privilegeType = rs.getString("privilegeType"); // This will be null for students, and possibly for some teachers

                HttpSession session = request.getSession();
                session.setAttribute("userType", "user"); // Everyone is a user by default
                if ("teacher".equals(userType) && "admin".equals(privilegeType)) {
                    session.setAttribute("privilegeType", "admin");
                } else {
                    session.setAttribute("privilegeType", "user"); // Explicitly set to "user" for clarity
                }

                session.setAttribute("stateType", "confirmed");
                response.sendRedirect("userPage");
            } else {
                request.setAttribute("errorMessage", "Invalid username or password");
                request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Database connection error during login", e);
        }
    }
}
