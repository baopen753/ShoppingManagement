package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainController extends HttpServlet {

    private static final String WELCOME = "index.html";

    private static final String LOGIN_ACTION = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";

    private static final String LOGOUT_ACTION = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogoutController";

    private static final String UPDATE_ACTION = "Update";
    private static final String UPDATE_CONTROLLER = "UpdateCustomerController";

    private static final String SHOPPING_PAGE = "Shopping_Page";
    private static final String SHOPPING_VIEW_PAGE = "ShoppingController";

    private static final String SIGNIN_ACTION = "Signin";
    private static final String SIGNIN_CONTROLLER = "SigninController";

    private static final String ADD_ACTION = "Add to Cart";
    private static final String ADD_CONTROLLER = "AddController";

    private static final String REMOVE_ACTION = "Remove";
    private static final String REMOVE_CONTROLLER = "RemoveController";

    private static final String EDIT_ACTION = "Edit";
    private static final String EDIT_CONTROLLER = "EditController";

    private static final String BUY_NOW_ACTION = "BuyNow";
    private static final String BUY_NOW_CONTROLLER = "BuyNowController";

    private static final String CHECKOUT_ACTION = "Check out";
    private static final String CHECKOUT_CONTROLLER = "CheckOutController";

    private static final String SEARCH_CUSTOMER_ACTION = "SearchCustomer";
    private static final String SEARCH_CUSTOMER_CONTROLLER = "SearchCustomerController";

    private static final String UPDATE_CUSTOMER_FROM_MANAGER_ACTION = "Update Customer";
    private static final String UPDATE_CUSTOMER_FROM_MANAGER_CONTROLLER = "UpdateCustomerFromManagerController";

    private static final String DELETE_CUSTOMER_FROM_MANAGER_ACTION = "DeleteCustomerFromManager";
    private static final String DELETE_CUSTOMER_FROM_MANAGER_CONTROLLER = "DeleteCustomerFromManagerController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = WELCOME;

        try {

            String action = request.getParameter("action");

            if (action == null) {
                url = WELCOME;
            } else if (LOGIN_ACTION.equals(action)) {
                url = LOGIN_CONTROLLER;
            } else if (LOGOUT_ACTION.equals(action)) {
                url = LOGOUT_CONTROLLER;
            } else if (SHOPPING_PAGE.equals(action)) {
                url = SHOPPING_VIEW_PAGE;
            } else if (UPDATE_ACTION.equals(action)) {
                url = UPDATE_CONTROLLER;
            } else if (SIGNIN_ACTION.equals(action)) {
                url = SIGNIN_CONTROLLER;
            } else if (ADD_ACTION.equals(action)) {
                url = ADD_CONTROLLER;
            } else if (REMOVE_ACTION.equals(action)) {
                url = REMOVE_CONTROLLER;
            } else if (EDIT_ACTION.equals(action)) {
                url = EDIT_CONTROLLER;
            } else if (BUY_NOW_ACTION.equals(action)) {
                url = BUY_NOW_CONTROLLER;
            } else if (CHECKOUT_ACTION.equals(action)) {
                url = CHECKOUT_CONTROLLER;
            } else if (SEARCH_CUSTOMER_ACTION.equals(action)) {
                url = SEARCH_CUSTOMER_CONTROLLER;
            } else if (UPDATE_CUSTOMER_FROM_MANAGER_ACTION.equals(action)) {
                url = UPDATE_CUSTOMER_FROM_MANAGER_CONTROLLER;
            } else if (DELETE_CUSTOMER_FROM_MANAGER_ACTION.equals(action)) {
                url = DELETE_CUSTOMER_FROM_MANAGER_CONTROLLER;
            }

        } catch (Exception e) {
            log("Error at LoginController" + e.toString());
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
