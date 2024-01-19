<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.detyrekursigreisialba.model.Question" %>
<%@ page import="com.example.detyrekursigreisialba.model.UserAnswer" %>
<%@ page import="com.example.detyrekursigreisialba.model.Result" %>
<%@ page import="com.example.detyrekursigreisialba.service.QuizService" %>

<%
    QuizService quizService = new QuizService();

    int quizId = Integer.parseInt(request.getParameter("quizId"));
    String username = request.getParameter("username");
    int currentQuestion = Integer.parseInt(request.getParameter("currentQuestion"));

    Map<Integer, String> userAnswers = new HashMap<>();

    List<Question> questions = quizService.getQuestionsForQuiz(quizId);
    for (Question question : questions) {
        String answer = request.getParameter("question" + question.getId());
        userAnswers.put(question.getId(), answer);
    }

    String currentAnswer = request.getParameter("question" + currentQuestion);
    userAnswers.put(currentQuestion, currentAnswer);

    // For simplicity, check correctness based on the example's dummy data
    boolean isCurrentAnswerCorrect = checkAnswerCquizServiceorrectness(currentQuestion, currentAnswer);

    List<UserAnswer> userAnswerList = (List<UserAnswer>) session.getAttribute("userAnswers");
    if (userAnswerList == null) {
        userAnswerList = new ArrayList<>();
    }

    UserAnswer userAnswer = new UserAnswer(quizId, username, isCurrentAnswerCorrect);
    userAnswerList.add(userAnswer);

    session.setAttribute("userAnswers", userAnswerList);

    int nextQuestion = currentQuestion + 1;
    if (nextQuestion <= questions.size()) {
        response.sendRedirect("dashboard.jsp?currentQuestion=" + nextQuestion);
    } else {
        Result result = new Result();
        result.setQuizId(quizId);
        result.setUsername(username);
        quizService.saveResult(result);

        response.sendRedirect("finish.jsp");
    }

    // Method to check correctness
    private boolean checkAnswerCorrectness (int questionId, String userAnswer) {
    // Dummy correctness check based on the example's dummy data
    Question question = DummyData.getQuestionById(questionId);
    return userAnswer.equals(question.getRightAnswer());
    }
%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quiz Completion</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
        }

        header {
            background-color: #333;
            color: #fff;
            text-align: center;
            padding: 1em 0;
        }

        h2 {
            margin-bottom: 1em;
        }

        p {
            font-size: 1.1em;
            line-height: 1.5;
            color: #555;
        }

        a {
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
        }

        .container {
            max-width: 400px;
            margin: 2em auto;
            background-color: #fff;
            padding: 2em;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .correct {
            color: green;
            font-weight: bold;
        }

        .incorrect {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
<header>
    <h2>Quiz Completion</h2>
</header>

<div class="container">
    <c:if test="${isCurrentAnswerCorrect}">
        <p class="correct">Correct! Well done.</p>
    </c:if>
    <c:if test="${not isCurrentAnswerCorrect}">
        <p class="incorrect">Incorrect! Keep trying.</p>
    </c:if>

    <p><a href="dashboard.jsp">Next Question</a></p>
</div>
</body>
</html>
