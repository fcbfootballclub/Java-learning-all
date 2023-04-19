package org.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DBConnection {
    public static Connection getConnection() {
        Connection  connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thread", "root", "123456");
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(DBConnection.class.getName());
        }

        return connection;
    }
}
