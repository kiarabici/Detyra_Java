package com.example.detyrekursigreisialba.service;

import com.example.detyrekursigreisialba.model.User;
import com.example.detyrekursigreisialba.model.enums.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    public UserService() {
    }

    private User findUserByUsername(String username) {
        User user = new User();
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("" +
                     "SELECT * FROM users where username  = ?")
        ) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String password = resultSet.getString("password");
                    StringBuilder hiddenPassword = new StringBuilder();
                    for (int index = 0; index < password.length(); index++) {
                        hiddenPassword.append(" ");
                    }
                    String email = resultSet.getString("email");
                    user = new User(username, hiddenPassword.toString(), email, UserRole.USER);
                    user.setId(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void saveProfileChanges(User updatedUser) {
        Connection connection = null;
        try {
            connection = DatabaseManager.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String query = "UPDATE users " +
                "SET email = ?, " +
                "    username = ?, " +
                "    password = ?, " +
                "WHERE id = ?";
        String newEmail = updatedUser.getEmail();
        String newUsername = updatedUser.getEmail();
        String newPassword = updatedUser.getPassword();
        int id = updatedUser.getId();

        try {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newEmail);
                preparedStatement.setString(2, newUsername);
                preparedStatement.setString(3, newPassword);
                {
                    int rowsUpdated = preparedStatement.executeUpdate();

                    if (rowsUpdated > 0) {
                        System.out.println("User updated successfully.");
                    } else {
                        System.out.println("User not found or no changes made.");
                    }
                }

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
