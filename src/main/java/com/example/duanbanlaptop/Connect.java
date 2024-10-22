package com.example.duanbanlaptop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private String url = "jdbc:mysql://localhost:3306/duanbanhang";
    private String usernameDB = System.getenv("usernameDB");
    private String passwordDB = System.getenv("passwordDB");

    public Connection connect() throws SQLException {
        Connection conn = DriverManager.getConnection(url, usernameDB, passwordDB);
        return conn;
    }
}