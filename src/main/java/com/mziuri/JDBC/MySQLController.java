package com.mziuri.JDBC;

import com.mziuri.user.User;

import java.sql.*;
import java.util.List;

public interface MySQLController {

    void createSchema();

    void createTable();

    void addUser(String username, String password);

    List<User> getUsers();
}
