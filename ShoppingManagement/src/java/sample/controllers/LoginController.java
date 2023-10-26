package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.shopping.Cart;
import sample.users.UserDAO;
import sample.users.UserDTO;

public class LoginController extends HttpServlet {

    private static final String ERROR = "login.jsp";

    private static final String MANAGER_PAGE = "manager.jsp";
    private static final String CUSTOMER_PAGE = "customer.jsp";

    private static final String CHECK_OUT_PAGE = "viewCart.jsp";

    private static final String MANAGER = "QL";
    private static final String CUSTOMER = "KH";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;

        try {

            // get parameters from form request
            String userName = request.getParameter("userName");
            String passWord = request.getParameter("passWord");

            // create an data user object to store current User login
            UserDAO dao = new UserDAO();
            UserDTO loginUser = dao.checkLogin(userName, passWord);

            //checking cart available
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");

          
            // checking authentication
            if (loginUser != null) {

                session.setAttribute("LOGIN_USER", loginUser);   // doan nay mat 2 ngay debug - no khien toi ngao ngan

                if (cart == null) {   // truong hop login binh thuong
                    String roleID = loginUser.getRoleID();
                    // phan quyen o day
                    if (MANAGER.equals(roleID)) {
                        url = MANAGER_PAGE;
                    } else if (CUSTOMER.equals(roleID)) {
                        url = CUSTOMER_PAGE;
                    } else {
                        request.setAttribute("ERROR", "Your role is not supported");
                    }

                } else {   // truong hop shopping roi login:  sau khi login xong thi dieu huong ra duong dan nay
                    url = CHECK_OUT_PAGE;
                }

            } else {
                request.setAttribute("ERROR", "Incorrect username or password");
            }

        } catch (Exception e) {
            log("Error at LoginOController" + e.toString());
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
