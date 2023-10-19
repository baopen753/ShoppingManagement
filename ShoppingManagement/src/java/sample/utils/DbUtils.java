package sample.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {

    private static final String DB_NAME = "StoreManagement";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "12345";

    public static final Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=" + DB_NAME;
        conn = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);

        return conn;
    }

}
