package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.users.UserDAO;
import sample.users.UserDTO;
import sample.users.UserError;

public class SigninController extends HttpServlet {

    private static final String ERROR = "signin.jsp";
    private static final String SUCCESS = "login.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        UserDAO dao = new UserDAO();
        String url = ERROR;
        UserError userError = new UserError();
        boolean checkValidation = true;

        try {

            String userName = request.getParameter("userName");
            String name = request.getParameter("name");
            String passWord = request.getParameter("passWord");
            String confirm = request.getParameter("confirm");

            // Cleaning data
            // 1. check valid username
            if (userName.length() > 15 || userName.length() < 2) {
                checkValidation = false;
                userError.setUsernameError("Username must be in [2,15]");
            }

            // 2. check username duplicate potentially
            boolean checkDuplicateUsername = dao.checkDuplicateUsername(userName);
            if (checkDuplicateUsername) {
                checkValidation = false;
                userError.setUsernameError("Username hasn't been used");
            }

            // 3. check password, confirmPassword
            if (!passWord.equals(confirm)) {
                checkValidation = false;
                userError.setPasswordError("Wrong confirm password");
            }

            // check validation
            if (checkValidation) {
                String roleID = "KH";
                UserDTO userCreate = new UserDTO("", userName, passWord, name, roleID);
                boolean checkInsert = dao.checkInsert(userCreate);

                if (checkInsert) {
                    url = SUCCESS;
                } else {
                    userError.setError("Unknown error! ");
                }
            } else {
                // to get error in view if neccessary
                request.setAttribute("ERROR", userError);
            }

        } catch (Exception e) {

            log("Error at SigninController" + e.toString());

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
