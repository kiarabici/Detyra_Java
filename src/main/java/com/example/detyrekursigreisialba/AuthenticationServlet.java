package com.example.detyrekursigreisialba;

import com.example.detyrekursigreisialba.model.User;
import com.example.detyrekursigreisialba.model.enums.UserRole;
import com.example.detyrekursigreisialba.service.DatabaseManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/auth")
public class AuthenticationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if ("register".equals(action)) {
            registerUser(request, response);
        } else if ("login".equals(action)) {
            loginUser(request, response);
        } else if ("logout".equals(action)) {
            logoutUser(request, response);
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if (username == null || password == null || email == null ||
                username.trim().isEmpty() || password.trim().isEmpty() || email.trim().isEmpty()) {
            response.sendRedirect("register-failure.jsp");
            return;
        }

        if (!isValidUsername(username) || !isValidPassword(password) || !isValidEmail(email)) {
            response.sendRedirect("register-failure.jsp");
            return;
        }

        User newUser = new User(username, password, email, UserRole.USER);

        if (saveUserToDatabase(newUser)) {
            response.sendRedirect("dashboard.jsp");
        } else {
            response.sendRedirect("register-failure.jsp");
        }
    }

    private boolean saveUserToDatabase(User user) {
        try (Connection connection = DatabaseManager.getConnection()) {
            String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getEmail());
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isValidUsername(String username) {
        return username.length() >= 8;
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8 && password.matches(".*[a-zA-Z].*") && password.matches(".*\\d.*");
    }

    private boolean isValidEmail(String email) {
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection connection = DatabaseManager.getConnection()) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // Authentication successful
                        HttpSession session = request.getSession(true);
                        session.setAttribute("username", username);
                        session.setAttribute("role", resultSet.getString("role"));
                        response.sendRedirect("dashboard.jsp");
                    } else {
                        // Authentication failed
                        response.sendRedirect("login.jsp?error=1");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("login-failure.jsp");
        }
    }

    private void logoutUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("index.jsp");
    }
}
