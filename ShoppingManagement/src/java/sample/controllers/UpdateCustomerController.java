package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import sample.users.UserDAO;
import sample.users.UserDTO;
import sample.users.UserError;

public class UpdateCustomerController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String SUCCESS = "login.jsp";

    private static final String CUSTOMER_PAGE = "updateUser.jsp";
    private static final String MANAGER_PAGE = "updateManager.jsp";

    private static final String CUS = "KH";
    private static final String MAN = "QL";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserDTO userLogin = (UserDTO) session.getAttribute("LOGIN_USER");
        String url = "";
        if (CUS.equals(userLogin.getRoleID())) {
            url = CUSTOMER_PAGE;
        } else if (MAN.equals(userLogin.getRoleID())) {
            url = MANAGER_PAGE;
        }

        boolean checkValidation = true;
        UserDAO dao = new UserDAO();
        UserError userError = new UserError();

        try {

            // lay user dang login
            String roleID = userLogin.getRoleID();
            String userName = userLogin.getUserName();

            // get data update
            String name = request.getParameter("name");
            String passWord = request.getParameter("passWord");
            String confirm = request.getParameter("confirm");

            // tam thoi dung cach chen password  --> password vulnerability
            UserDTO userUpdate = new UserDTO("", userName, passWord, name, roleID);

            // clean datda
            if (name.length() > 20 || name.length() < 5) {
                checkValidation = false;
                userError.setNameError("Name must be in [2,20]");
            }
            
            if (!passWord.equals(confirm)) {
                checkValidation = false;
                userError.setConfirmError("Confirm password doesn't match password");
            }

            if (checkValidation) {

                boolean check = dao.update(userUpdate);

                if (CUS.equals(roleID)) {
                    if (check) {
                        url = SUCCESS;
                        if (userLogin.getUserName().equals(userName)) {
                            session.setAttribute("LOGIN_USER", userUpdate);
                        }
                    }
                } else if (MAN.equals(roleID)) {
                    if (check) {
                        url = SUCCESS;
                        if (userLogin.getUserName().equals(userName)) {
                            session.setAttribute("LOGIN_USER", userUpdate);
                        }
                    }
                }
            } else {
                request.setAttribute("USER_ERROR", userError);
            }

        } catch (Exception e) {
            log("Error at UpdateCOntroller" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
