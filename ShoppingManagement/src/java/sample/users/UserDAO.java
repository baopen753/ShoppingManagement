package sample.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import sample.products.ProductDTO;
import sample.utils.DbUtils;

public class UserDAO {

    private static final String LOGIN = "SELECT name, roleID from Accounts WHERE userName = ? AND passWord = ?";
    private static final String CHECK_DUPLICATE = "SELECT userName from Accounts WHERE userName = ?";
    private static final String INSERT = "INSERT INTO Accounts(userName,passWord,name,roleID) VALUES(?,?,?,?)";
    private static final String UPDATE = "UPDATE Accounts SET name = ?, passWord = ? WHERE userName = ?";
    private static final String GET_PRODUCT_LIST = "SELECT productID,productName,price,quantity FROM Products";
    private static final String GET_USER_ID = "SELECT userID FROM Accounts WHERE userName = ? ";
    private static final String GET_CUSTOMER_LIST = "SELECT userID,userName,name,roleID FROM Accounts WHERE roleID = 'KH' AND userName LIKE ? "; // chu y syntax        

    public static UserDTO checkLogin(String userName, String passWord) throws SQLException {

        UserDTO loginUser = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {

            // create databse connection
            conn = DbUtils.getConnection();

            if (conn != null) {
                // create preparered statement
                ptm = conn.prepareStatement(LOGIN);

                ptm.setString(1, userName);
                ptm.setString(2, passWord);

                // executing query
                rs = ptm.executeQuery();

                if (rs.next()) {
                    String name = rs.getString("name");
                    String roleID = rs.getString("roleID");

                    loginUser = new UserDTO("", userName, "", name, roleID);
                }
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

        return loginUser;
    }

    public boolean checkDuplicateUsername(String userName) throws SQLException {
        boolean checkDuplicate = false;

        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {

            conn = DbUtils.getConnection();

            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, userName);
                rs = ptm.executeQuery();

                if (rs.next()) {
                    checkDuplicate = true;
                }
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

        return checkDuplicate;
    }

    public boolean checkInsert(UserDTO userCreate) throws SQLException {
        boolean checkInsert = false;

        Connection conn = null;
        PreparedStatement ptm = null;

        try {

            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);

                ptm.setString(1, userCreate.getUserName());
                ptm.setString(2, userCreate.getPassWord());
                ptm.setString(3, userCreate.getName());
                ptm.setString(4, userCreate.getRoleID());

                checkInsert = ptm.executeUpdate() > 0 ? true : false;
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

        return checkInsert;
    }

    public boolean update(UserDTO userUpdate) throws SQLException {
        boolean checkUpdate = false;

        Connection conn = null;
        PreparedStatement ptm = null;

        try {

            conn = DbUtils.getConnection();

            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, userUpdate.getName());
                ptm.setString(2, userUpdate.getPassWord());
                ptm.setString(3, userUpdate.getUserName());

                checkUpdate = ptm.executeUpdate() > 0 ? true : false;
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
        return checkUpdate;
    }

    public HashMap<Integer, ProductDTO> getListProduct() throws SQLException {
        HashMap<Integer, ProductDTO> map = new HashMap<>();

        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {

            conn = DbUtils.getConnection();

            if (conn != null) {
                ptm = conn.prepareStatement(GET_PRODUCT_LIST);

                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");

                    ProductDTO newProduct = new ProductDTO(productID, productName, price, quantity);
                    map.put(productID, newProduct);
                }

            }

        } catch (Exception e) {
            e.getStackTrace();
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
        return map;
    }

    public int getUserID(String userName) throws SQLException {

        int userID = 0;

        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();
            ptm = conn.prepareStatement(GET_USER_ID);
            ptm.setString(1, userName);

            rs = ptm.executeQuery();

            if (rs.next()) {
                userID = Integer.parseInt(rs.getString("userID"));
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

        return userID;
    }

    public List<UserDTO> getListCustomer(String searchPattern) throws SQLException {

        List<UserDTO> listCustomer = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {

            conn = DbUtils.getConnection();
            if (conn != null) {

                ptm = conn.prepareStatement(GET_CUSTOMER_LIST);
                ptm.setString(1, "%" + searchPattern + "%");

                rs = ptm.executeQuery();

                while (rs.next()) {

                    String userName = rs.getString("userName");
                    String userID = getUserID(userName) + "";
                    String name = rs.getString("name");
                    String roleID = rs.getString("roleID");

                    listCustomer.add(new UserDTO(userID, userName, "", name, roleID));
                }
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

        return listCustomer;
    }
}
