package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.user.UserDAO;
import sample.user.UserDTO;

@WebServlet(name = "SearchServlet", urlPatterns = {"/searchservlet"})

public class SearchServlet extends HttpServlet {
    
    private static final String SUCCESS = "searchresultservlet";
    private static final String ERROR = "search.html";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");   
        String url = ERROR;
        
        try {
            String search = request.getParameter("search");
            UserDAO dao = new UserDAO();
            List<UserDTO> listUser = dao.getListUser(search);
            if (listUser.size() > 0) {
                request.setAttribute("LIST_USER", listUser);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at SearchServlet: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
    
}
