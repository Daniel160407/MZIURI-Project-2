package com.mziuri.servlets;

import com.mziuri.JDBC.JDBCController;
import com.mziuri.user.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    JDBCController jdbcController = new JDBCController();

    public UserServlet() {
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("registerUsername");
        String password = request.getParameter("registerPassword");

        List<User> users = jdbcController.getUsers();
        boolean passed = true;

        for (User user : users) {
            System.out.println(user);
            if (user == null || user.getUsername().equals(username)) {
                passed = false;
                break;
            }
        }
        System.out.println("got");
        System.out.println(username);
        System.out.println(password);
        if (passed) {
            jdbcController.addUser(username, password);
            System.out.println("passed");
            response.setStatus(200);
        } else {
            response.setStatus(403);
        }
    }
}
