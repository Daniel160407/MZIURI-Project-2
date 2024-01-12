package com.mziuri.servlets;

import com.mziuri.JDBC.MySQLController;
import com.mziuri.message.Message;
import com.mziuri.message.MessageValidator;
import com.mziuri.user.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/message")
public class MessageServlet extends HttpServlet {
    MySQLController mySQLController = new MySQLController();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/json");

        String username = request.getParameter("inboxUsername");
        String password = request.getParameter("inboxPassword");

        List<User> users = mySQLController.getUsers();
        boolean isFound = false;

        for (User user : users) {
            if (user.username().equals(username) && user.password().equals(password)) {
                isFound = true;
                break;
            }
        }
        if (isFound) {

            List<Message> messages = mySQLController.getMessages(username);

            PrintWriter printWriter = response.getWriter();
            try {
                printWriter.println(Message.messageListToJson(messages));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            response.setStatus(200);
        }else {
            response.setStatus(403);
        }
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
