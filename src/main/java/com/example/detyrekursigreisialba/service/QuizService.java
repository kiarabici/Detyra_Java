package com.example.detyrekursigreisialba.service;

import com.example.detyrekursigreisialba.model.Option;
import com.example.detyrekursigreisialba.model.Question;
import com.example.detyrekursigreisialba.model.Quiz;
import com.example.detyrekursigreisialba.model.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Question> getQuestionsWithOptions(int quizId) {
        Map<Integer, Question> questionMap = new HashMap<>();

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM questions WHERE quiz_id = ?")
        ) {
            preparedStatement.setInt(1, quizId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int questionId = resultSet.getInt("id");
                    Question question = questionMap.computeIfAbsent(questionId, id -> {
                        int qQuizId = 0;
                        int qIndex = 0;
                        String qName = null;
                        try {
                            qQuizId = resultSet.getInt("quiz_id");
                            qIndex = resultSet.getInt("index");
                            qName = resultSet.getString("name");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        return new Question(qQuizId, qIndex, qName, new ArrayList<>());
                    });
                    List<Option> options = getOptionsForQuestion(questionId);
                    question.getOptions().addAll(options);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>(questionMap.values());
    }

    private List<Option> getOptionsForQuestion(int questionId) {
        List<Option> options = new ArrayList<>();

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM options WHERE question_id = ?")
        ) {
            preparedStatement.setInt(1, questionId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String value = resultSet.getString("value");
                    boolean isAnswer = resultSet.getBoolean("is_answer");
                    options.add(new Option(questionId, value, isAnswer));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return options;
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
