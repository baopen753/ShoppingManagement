package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.shopping.Cart;
import sample.products.ProductDTO;

public class EditController extends HttpServlet {
    
    private static String ERROR = "viewCart.jsp";
    private static String SUCCESS = "viewCart.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String url = ERROR;
        try {
            
            int productID = Integer.parseInt(request.getParameter("productID"));
            int quantity_edit = Integer.parseInt(request.getParameter("quantity"));
            
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");

            // checking if session exists, keep continue...
            if (cart != null) {
                // checking if cart exists, keep continue...
                if (cart.getCart().containsKey(productID)) {
                    ProductDTO product = cart.getCart().get(productID);
                    product.setQuanity(quantity_edit);
                    boolean check = cart.edit(productID, product);
                    if (check) {
                        url = SUCCESS;
                        // update into Cart in Session
                        session.setAttribute("CART", cart);
                    }
                }
            }
        } catch (Exception e) {
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
