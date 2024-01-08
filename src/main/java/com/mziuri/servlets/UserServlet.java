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
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        List<User> users = jdbcController.getUsers();
        boolean passed = true;

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                passed = false;
                break;
            }
        }

        if (passed) {
            jdbcController.addUser(username, password);
        } else {
            response.setStatus(403);
        }
    }
}
