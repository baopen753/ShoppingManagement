package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.products.ProductDAO;
import sample.products.ProductDTO;
import sample.shopping.Cart;
import sample.shopping.OrderDAO;
import sample.shopping.OrderDTO;
import sample.shopping.OrderDetailDAO;
import sample.users.UserDAO;
import sample.users.UserDTO;

public class UpdateDataBaseController extends HttpServlet {

    private static final String SUCCESS = "viewCart.jsp";
    private static final String ERROR = "error.jsp";
    private static final String UPDATE_DATABASE_SUCCESS = "checkOutSuccess.jsp";

    private static final String CHECKOUT_PAGE = "CheckOutController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;
        boolean checkUpdateQuantity = false;
        boolean checkAddOrderDatabase = false;
        boolean checkOrderDetailAdd = false;

        // get Cart from Session
        try {
            HttpSession session = request.getSession();
            Cart cartCheckout = (Cart) session.getAttribute("CART");

            ProductDAO productDAO = new ProductDAO();
            // traverse cart to update quantity to database

            for (Map.Entry<Integer, ProductDTO> detailItem : cartCheckout.getCart().entrySet()) {
                int productID = detailItem.getValue().getProductID();
                int quantity = detailItem.getValue().getQuanity();

                //update product quantity
                checkUpdateQuantity = productDAO.updateProductToDatabase(productID, quantity);
                if (!checkUpdateQuantity) {
                    break;
                }
            }

            // insert cart to the Order table
            // get userID
            UserDAO dao = new UserDAO();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            String userName = loginUser.getUserName();
            String passWord = loginUser.getPassWord();

           LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            String orderDateTime = currentDateTime.format(formatter);
            int userID = dao.getUserID(userName);
            double totalPrice = cartCheckout.checkOutTotal();
            int cartID = cartCheckout.getCartID();

            // insert cart to the order history
            OrderDAO orderDAO = new OrderDAO();
            checkAddOrderDatabase = orderDAO.addCartToDataBase(cartID, userID, totalPrice, orderDateTime);

            // insert order detail
            for (Map.Entry<Integer, ProductDTO> detailItem : cartCheckout.getCart().entrySet()) {

                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();

                // get product info to add orderDetail(orderID, productID, productPriceAtTheMoment, productQuantity)
                int orderID = cartID;
                int productID = detailItem.getValue().getProductID();
                double productPrice = detailItem.getValue().getPrice();
                int productQuantity = detailItem.getValue().getQuanity();

                checkOrderDetailAdd = orderDetailDAO.addDetailDataBase(orderID, productID, productPrice, productQuantity);
               
            }
            if (checkUpdateQuantity && checkAddOrderDatabase && checkOrderDetailAdd) {
                url =UPDATE_DATABASE_SUCCESS;
            }

        } catch (Exception e) {
            log("Error at UpdateDataBaseController" + e.toString());
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
