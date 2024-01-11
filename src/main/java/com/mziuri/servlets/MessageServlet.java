package com.mziuri.servlets;

import com.mziuri.JDBC.JDBCController;
import com.mziuri.JDBC.MySQLController;
import com.mziuri.message.MessageValidator;
import com.mziuri.user.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

@WebServlet("/message")
public class MessageServlet extends HttpServlet {
    MySQLController mySQLController = new MySQLController();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String message = request.getParameter("message");


        boolean isValid = false;

        List<User> users = mySQLController.getUsers();
        for (User user : users) {
            if (user.username().equals(username)) {
                isValid = true;
                break;
            }
        }

        if (isValid) {
            MessageValidator validator = MessageValidator.getInstance();
            isValid = validator.validateMessage(message);
        }

        if (isValid) {
            mySQLController.saveMessage(username, message);
            response.setStatus(200);
        } else {
            response.setStatus(403);
        }
    }
}
