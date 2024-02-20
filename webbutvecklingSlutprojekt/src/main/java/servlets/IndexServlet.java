package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "IndexServlet", urlPatterns = {"/", "/index"})
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Don't create if it doesn't exist

        // Optionally, add any dynamic attributes you want to be accessible in the JSP.
        if (session != null && session.getAttribute("userType") != null) {
            // This is a confirmed user, either student or teacher
            String userType = (String) session.getAttribute("userType");
            request.setAttribute("userMessage", "Welcome back, " + userType + "!");
        } else {
            // This is an anonymous user
            request.setAttribute("userMessage", "Welcome to Grit Academy Portal!");
        }

        // Forward the request to the index.jsp page
        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
    }
}
