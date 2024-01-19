package com.example.detyrekursigreisialba.service;

import com.example.detyrekursigreisialba.model.Question;
import com.example.detyrekursigreisialba.model.Quiz;
import com.example.detyrekursigreisialba.model.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizService {

    public QuizService() {
    }

    public List<Quiz> getAllQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();

        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM quizzes")) {

            while (resultSet.next()) {
                Quiz quiz = new Quiz();
                quiz.setId(resultSet.getInt("id"));
                quiz.setName(resultSet.getString("name"));
                quizzes.add(quiz);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quizzes;
    }

    public List<Question> getQuestionsForQuiz(int quizId) {
        List<Question> questions = new ArrayList<>();

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM questions WHERE quiz_id = ?")
        ) {
            preparedStatement.setInt(1, quizId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Question question = new Question();
                    question.setId(resultSet.getInt("id"));
                    question.setQuizId(resultSet.getInt("quiz_id"));
                    question.setIndex(resultSet.getInt("index"));
                    question.setName(resultSet.getString("name"));
                    question.setAnswer(resultSet.getString("answer"));
                    questions.add(question);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }

    public void saveResult(Result result) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO results (quiz_id, username, answers) VALUES (?, ?, ?)"
             )
        ) {
            preparedStatement.setInt(1, result.getQuizId());
            preparedStatement.setString(2, result.getUsername());
            preparedStatement.setObject(3, result.getUserAnswers());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

