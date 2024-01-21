package com.example.detyrekursigreisialba.service;

import com.example.detyrekursigreisialba.model.*;

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
                        return new Question(questionId, qQuizId, qIndex, qName, new ArrayList<>());
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
                    int id = resultSet.getInt("id");
                    String value = resultSet.getString("value");
                    boolean isAnswer = resultSet.getBoolean("is_answer");
                    options.add(new Option(id, questionId, value, isAnswer));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return options;
    }

    public int saveResult(Result result) {
        int generatedResultId = -1;
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO results (quiz_id, username) VALUES (?, ?);",
                     Statement.RETURN_GENERATED_KEYS
             )
        ) {
            preparedStatement.setInt(1, result.getQuizId());
            preparedStatement.setString(2, result.getUsername());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                generatedResultId = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedResultId;
    }

    public void saveUserQuizResults(Result result, List<UserAnswer> userAnswers) {
        int resultId = saveResult(result);
        try (Connection connection = DatabaseManager.getConnection()) {
            String sql = "INSERT INTO user_answers (result_id, question_id, option_id) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                for (UserAnswer userAnswer : userAnswers) {
                    statement.setInt(1, resultId);
                    statement.setInt(2, userAnswer.getQuestionId());
                    statement.setInt(3, userAnswer.getOptionId());
                    statement.addBatch();
                }
                statement.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int findCorrectOptionId(List<Option> options) {
        for (Option option : options) {
            if (option.isCorrectAnswer()) {
                return option.getId();
            }
        }
        return -1;
    }
}
