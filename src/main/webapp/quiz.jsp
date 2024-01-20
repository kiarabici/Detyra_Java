<%@ include file="common.jsp" %>
<%@ page import="com.example.detyrekursigreisialba.model.Question" %>
<%@ page import="com.example.detyrekursigreisialba.model.UserAnswer" %>
<%@ page import="com.example.detyrekursigreisialba.service.QuizService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.detyrekursigreisialba.model.Option" %>
<%@ page import="com.example.detyrekursigreisialba.model.Result" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    session = request.getSession();
    String username = (String) session.getAttribute("username");
    if (Objects.isNull(username)) {
        response.sendRedirect("index.jsp");
    }

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

    UserAnswer userAnswer = new UserAnswer();
    userAnswer.setOptionId(correctOption.getId());
    userAnswers.add(userAnswer);
    session.setAttribute("userAnswers", userAnswers);

    int nextQuestion = currentQuestion + 1;
    if (nextQuestion <= questions.size()) {
%>
<div class="container mt-5">
    <form action="quiz.jsp" method="post">
        <input type="hidden" name="quizId" value="<%= quizId %>">
        <input type="hidden" name="username" value="<%= username %>">
        <input type="hidden" name="currentQuestion" value="<%= nextQuestion %>">
        <input type="hidden" name="question<%= nextQuestion %>" value="<%= currentAnswer %>">
        <button type="submit" class="btn btn-primary">Next Question</button>
    </form>
</div>
<%
    } else {
        Result result = new Result(quizId, username, userAnswers);
        quizService.saveResult(result);
    }
%>
