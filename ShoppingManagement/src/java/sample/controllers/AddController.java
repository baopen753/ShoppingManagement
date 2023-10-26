package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.shopping.Cart;
import sample.products.ProductDTO;

public class AddController extends HttpServlet {

    private static final String ERROR = "shopping.jsp";
    private static final String SUCCESS = "shopping.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;

        try {

            int productID = Integer.parseInt(request.getParameter("productID"));
            int quantity = 1;

            HttpSession session = request.getSession();
            if (session != null) {
                Cart cart = (Cart) session.getAttribute("CART");

                if (cart == null) {
                    cart = new Cart();
                }

                // lay product tu warhouse 
                HashMap<Integer, ProductDTO> availableProduct = (HashMap<Integer, ProductDTO>) session.getAttribute("AVAILABLE_PRODUCT");

                //Product model
                ProductDTO product = availableProduct.get(productID);

                
                ProductDTO productAdd = new ProductDTO(productID, product.getProductName(), product.getPrice(), quantity);

                boolean checkAdd = cart.add(productAdd);

                if (checkAdd) {
                    // update cart
                    session.setAttribute("CART", cart);
                    url = SUCCESS;
                    request.setAttribute("ADD_SUCCESS", "Adding " + productAdd.getProductName() + " successfully !");
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
