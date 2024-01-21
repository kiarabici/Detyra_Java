package com.example.detyrekursigreisialba;

import com.example.detyrekursigreisialba.model.User;
import com.example.detyrekursigreisialba.model.enums.UserRole;
import com.example.detyrekursigreisialba.service.DatabaseManager;
import org.postgresql.util.PSQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    boolean userNameExists = false;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        User newUser = new User(username, password, email, UserRole.USER);

        try {
            if (saveProfileChanges(newUser)) {
                response.sendRedirect("dashboard.jsp");
            } else {
                request.setAttribute("registerError", "Username " + username + " already exists");
                RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private boolean saveProfileChanges(User newUser) {
        try (Connection connection = DatabaseManager.getConnection()) {
            String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getEmail());
                statement.executeUpdate();
                return true;

            }
        } catch (PSQLException exception) {
            userNameExists = true;
            return false;
        } catch (SQLException exception) {
            return false;
        }
    }
}
