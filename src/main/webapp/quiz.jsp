<%@ page import="com.example.detyrekursigreisialba.model.Question" %>
<%@ page import="com.example.detyrekursigreisialba.model.UserAnswer" %>
<%@ page import="com.example.detyrekursigreisialba.service.QuizService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.detyrekursigreisialba.model.Option" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    QuizService quizService = new QuizService();

    int quizId = Integer.parseInt(request.getParameter("quizId"));
    int currentQuestion = (request.getParameter("currentQuestion") != null)
            ? Integer.parseInt(request.getParameter("currentQuestion"))
            : 1;

    List<UserAnswer> userAnswers = (List<UserAnswer>) session.getAttribute("userAnswers");
    if (userAnswers == null) {
        userAnswers = new ArrayList<>();
    }

    String currentAnswer = request.getParameter("question" + currentQuestion);
    List<Question> questions = quizService.getQuestionsWithOptions(quizId);
    Option correctOption = null;

    for (Option option : questions.get(currentQuestion - 1).getOptions()) {
        if (option.getAnswer()) {
            correctOption = option;
            break;
        }
    }
    assert correctOption != null;
    boolean isCurrentAnswerCorrect = currentAnswer.equals(correctOption.getValue());

    request.setAttribute("isCurrentAnswerCorrect", isCurrentAnswerCorrect);

    UserAnswer userAnswer = new UserAnswer(quizId, currentAnswer, isCurrentAnswerCorrect);
    userAnswers.add(userAnswer);

    session.setAttribute("userAnswers", userAnswers);

    int nextQuestion = currentQuestion + 1;
    if (nextQuestion <= questions.size()) {
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quiz - Question <%= nextQuestion %></title>
</head>
<body>
<!-- Your HTML body content here -->
</body>
</html>
<%
    } else {
        // Redirect to finish.jsp or another page
        // response.sendRedirect("finish.jsp");
    }
%>
