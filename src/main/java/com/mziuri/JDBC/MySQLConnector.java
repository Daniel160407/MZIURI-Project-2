package com.mziuri.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector {
    Connection con;

    public MySQLConnector(String url, String username, String password) {
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public Connection getCon() {
        return con;
    }
}
