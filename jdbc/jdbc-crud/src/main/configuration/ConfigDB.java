package main.configuration;

import java.sql.*;

public class ConfigDB {
    private static Connection connect = null;
    public static Connection getConexao() {
        if(connect == null) {
            String url = "jdbc:mysql://localhost:3306/agencia?useSSL=false";
            String user = "root";
            String password = "56571998";
            try {
                return DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void close(Connection connection) {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Connection connection, Statement stmt){
        close(connection);
        try {
            if (stmt != null)
                stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  static void close(Connection connection, Statement stmt, ResultSet rs){
        close(connection, stmt);
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
