package com.mziuri.JDBC;

import com.mziuri.MySQLConnector.MySQLConnector;
import com.mziuri.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCController implements MySQLController {

    @Override
    public void createSchema() {
        MySQLConnector mySQLConnector = new MySQLConnector("jdbc:mysql://localhost:3306", "root", "17042007");

        Connection con = mySQLConnector.getCon();

        try {
            Statement statement = con.createStatement();
            statement.execute("create schema if not exists Messenger");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void createTable() {
        MySQLConnector mySQLConnector = new MySQLConnector("jdbc:mysql://localhost:3306/Messenger", "root", "17042007");

        Connection con = mySQLConnector.getCon();

        try {
            Statement statement = con.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS`work`.`users` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `username` VARCHAR(45) NOT NULL,\n" +
                    "  `new_tablecol1` VARCHAR(45) NOT NULL,\n" +
                    "  PRIMARY KEY (`id`));\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(String username, String password) {
        MySQLConnector mySQLConnector = new MySQLConnector("jdbc:mysql://localhost:3306/Messenger", "root", "17042007");

        Connection con = mySQLConnector.getCon();

        try {
            PreparedStatement preparedStatement = con.prepareStatement("insert into users (username,password) values (?,?);");

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getUsers() {
        MySQLConnector mySQLConnector = new MySQLConnector("jdbc:mysql://localhost:3306/Messenger", "root", "17042007");

        Connection con = mySQLConnector.getCon();

        List<User> users = new ArrayList<>();
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");

            while (resultSet.next()) {
                users.add(new User(resultSet.getString(1), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
