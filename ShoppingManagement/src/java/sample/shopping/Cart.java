package sample.shopping;

import java.sql.SQLException;
import sample.products.ProductDTO;
import java.util.HashMap;
import java.util.Map;

public class Cart {

    private int cartID;
    private Map<Integer, ProductDTO> cart;

    public Cart() {

        this.cartID = this.cartID + 1; // Automatically generate and increment cartID
        this.cart = new HashMap<>();
    }

    public Cart(Map<Integer, ProductDTO> cart) {
        this.cart = cart;
    }

    public int getCartID() throws SQLException {
        OrderDAO orderDao = new OrderDAO();
        
        int orderID = orderDao.autoIncreaseID();
        
        return orderID;
    }

    public Map<Integer, ProductDTO> getCart() {
        return cart;
    }

    public void setCart(Map<Integer, ProductDTO> cart) {
        this.cart = cart;
    }

    public boolean add(ProductDTO product) {
        boolean check = false;

        try {

            if (this.cart == null) {
                this.cart = new HashMap<>();
            }

            if (this.cart.containsKey(product.getProductID())) {
                int currentQuantity = this.cart.get(product.getProductID()).getQuanity();

                // update quantity
                product.setQuanity(product.getQuanity() + currentQuantity);
            }

            this.cart.put(product.getProductID(), product);

            check = true;

        } catch (Exception e) {
            e.getStackTrace();
        }

        return check;
    }

    public double checkOutTotal() {
        double totalCheckout = 0;

        for (Map.Entry<Integer, ProductDTO> product : this.cart.entrySet()) {
            totalCheckout += product.getValue().getQuanity() * product.getValue().getPrice();
        }

        return totalCheckout;
    }

    public boolean remove(int productID) {
        boolean checkRemove = false;

        try {

            if (this.cart != null) {
                if (this.cart.containsKey(productID)) {
                    this.cart.remove(productID);
                    checkRemove = true;
                }
            }

        } catch (Exception e) {
            e.getStackTrace();
        }

        return checkRemove;
    }

    public boolean edit(int id, ProductDTO productEdit) {
        boolean checkEdit = false;

        try {

            if (this.cart != null) {

                if (this.cart.containsKey(id)) {
                    this.cart.replace(id, productEdit);
                    checkEdit = true;
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        return checkEdit;
    }

    public int size() {
        return this.cart.size();
    }

    public boolean edit(String id, ProductDTO editedProduct) {

        boolean checkEdit = false;

        try {
            if (this.cart != null) {

                if (this.cart.containsKey(id)) {
                    this.cart.remove(id, editedProduct);
                    checkEdit = true;
                }

            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        return checkEdit;

    }

    public boolean remove(String id) {
        boolean check = false;

        try {

            if (this.cart != null) {
                if (this.cart.containsKey(id)) {
                    this.cart.remove(id);
                    check = true;
                }
            }
        } catch (Exception e) {
        }
        return check;
    }

}
