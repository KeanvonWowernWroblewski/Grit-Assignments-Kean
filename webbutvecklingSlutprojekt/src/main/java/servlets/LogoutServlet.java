package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalidate the session to log out
        HttpSession session = request.getSession(false); // false means do not create a new session
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("index"); // Redirect to home page
    }
}
