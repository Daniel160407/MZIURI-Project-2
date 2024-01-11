package com.mziuri.JDBC;

import com.mziuri.user.User;

import java.util.List;

public interface JDBCController {

    void createSchema();

    void createTables();

    void addUser(String username, String password);

    List<User> getUsers();

    void saveMessage(String username, String message);
}
