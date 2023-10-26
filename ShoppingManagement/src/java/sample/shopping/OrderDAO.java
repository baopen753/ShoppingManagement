package sample.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sample.utils.DbUtils;

public class OrderDAO {

    private static final String ADD_ORDER_TO_DATABASE = "INSERT INTO Orders(orderID,userID,total,dateOrderNew) VALUES (?,?,?,?) ";
    private static final String GET_MAX_ORDER_ID = "SELECT TOP 1 orderID FROM  Orders ORDER BY orderID DESC";

    public boolean addCartToDataBase(int cartID, int userID, double totalPrice, String orderDateTime) throws SQLException {

        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {

            conn = DbUtils.getConnection();

            String cartIDAdd = cartID + "";
            String userIDAdd = userID + "";
            String totalAdd = totalPrice + "";

            if (conn != null) {
                ptm = conn.prepareStatement(ADD_ORDER_TO_DATABASE);
                ptm.setString(1, cartIDAdd);
                ptm.setString(2, userIDAdd);
                ptm.setString(3, totalAdd);
                ptm.setString(4, orderDateTime);

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

    public int autoIncreaseID() throws SQLException {
        int orderAutoIncrease = 0;

        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {

            conn = DbUtils.getConnection();
            ptm = conn.prepareStatement(GET_MAX_ORDER_ID);
            rs = ptm.executeQuery();
            if (rs.next()) {
                orderAutoIncrease = Integer.parseInt(rs.getString("orderID")) + 1;
            } else {
                orderAutoIncrease = 1;
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return orderAutoIncrease;
    }

}
