package com.mziuri.JDBC;

import com.mziuri.message.Message;
import com.mziuri.user.User;

import java.util.List;

public interface JDBCController {

    void createSchema();

    void createTables();

    void addUser(String username, String password);

    List<User> getUsers();

    List<Message> getMessages(String username);

    void saveMessage(String username, String message);
}
