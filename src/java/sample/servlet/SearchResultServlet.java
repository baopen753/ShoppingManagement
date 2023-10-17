package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.user.UserDTO;

public class SearchResultServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchResultServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchResultServlet at " + request.getContextPath() + "</h1>");

            List<UserDTO> list = (List<UserDTO>) request.getAttribute("LIST_USER");
            out.println("<table boder='1'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>No</th>");
            out.println("<th>User ID</th>");
            out.println("<th>Full Name</th>");
            out.println("<th>Role ID</th>");
            out.println("<th>Password</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            int count = 1;
            for (UserDTO user : list) {
                out.println("<tr>");
                out.println("<td>" + count++ + "</td>");
                out.println("<td>" + user.getUserID() + "</td>");
                out.println("<td>" + user.getFullName() + "</td>");
                out.println("<td>" + user.getRoleID() + "</td>");
                out.println("<td>" + user.getPassword() + "</td>");
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
