package sample.products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import sample.utils.DbUtils;

public class ProductDAO {

    private static final String UPDATE_PRODUCT = "UPDATE Products SET quantity = quantity - ? WHERE productID = ? ";

    public boolean updateProductToDatabase(int productID, int quantity) throws SQLException {

        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {

            String producIDUpdate = productID + "";
            String quantityUpdate = quantity + "";

            conn = DbUtils.getConnection();

            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_PRODUCT);
                ptm.setString(1, quantityUpdate);
                ptm.setString(2, producIDUpdate);

                if (ptm.executeUpdate() > 0) {  // debug ow day vi khong phan biet dc execute() vs executeUpdate()
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
