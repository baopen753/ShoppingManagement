package sample.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import sample.utils.DbUtils;

public class OrderDetailDAO {

    private static final String ADD_ORDER_DETAIL = "INSERT INTO OrderDetails(orderID, productID,price, quantity) VALUES (?,?,?,?)";

    public boolean addDetailDataBase(int orderID, int productID, double productPrice, int productQuantity) throws SQLException {

        boolean check = false;

        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DbUtils.getConnection();

            String orderIDAddd = orderID + "";
            String productIDAdd = productID + "";
            String productPriceAdd = productIDAdd + "";
            String productQuantityAdd = productQuantity + "";

            if (conn != null) {
                ptm = conn.prepareStatement(ADD_ORDER_DETAIL);
                ptm.setString(1, orderIDAddd);
                ptm.setString(2, productIDAdd);
                ptm.setString(3, productPriceAdd);
                ptm.setString(4, productQuantityAdd);

                if (ptm.executeUpdate() > 0) {
                    check = true;
                }
            }

        } catch (Exception e) {
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return check;
    }

}
