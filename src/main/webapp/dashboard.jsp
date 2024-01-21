<%@ include file="common.jsp" %>
<%@ page import="java.util.Objects" %>
<%@ page import="com.example.detyrekursigreisialba.model.Quiz" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.detyrekursigreisialba.service.QuizService" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    session = request.getSession();
    String username = (String) session.getAttribute("username");
    if (Objects.isNull(username)) {
        response.sendRedirect("index.jsp");
    }

    QuizService quizService = new QuizService();
    List<Quiz> quizzes = quizService.getAllQuizzes();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Quiz</title>
</head>
<body>
<div class="container mt-5">
    <h3>Quizzes</h3>
    <div class="row">
        <% for (Quiz quiz : quizzes) { %>
        <div class="card mr-4" style="width: 18rem;">
            <div class="card-body">
                <h5 class="card-title"><%= quiz.getName() %>
                </h5>
                <p class="card-text">Explore this quiz</p>
                <a class="btn btn-primary" href="quiz.jsp?selectedQuizId=<%=quiz.getId()%>"><%= quiz.getName() %>
                </a>
            </div>
        </div>
        <% } %>
    </div>
</div>
</body>
</html>
