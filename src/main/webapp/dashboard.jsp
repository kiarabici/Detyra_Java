<%@ include file="common.jsp" %>
<%@ page import="java.util.Objects" %>
<%@ page import="com.example.detyrekursijava.model.Quiz" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.detyrekursijava.service.QuizService" %>
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
<body >
<div class="container mt-5">
    <h1 class="text-center pb-5" style="margin-top: -60px">Quizzes</h1>
    <div class="row" style="display: flex; justify-content: center">
        <% for (Quiz quiz : quizzes) { %>
        <div class="card m-4" style="width: 23rem;height:13rem; ">
            <div class="card-body">
                <h3 class="card-title" ><%= quiz.getName() %>
                </h3>
                <hr>

                <p class=" card-text pb-3">Test  yourself on any field that you're confident in!</p>
                <a class="btn" style="color: white;background-color: #813731;padding: 5px 25px;" href="quiz.jsp?selectedQuizId=<%=quiz.getId()%>"><%= quiz.getName() %>
                </a>
            </div>
        </div>
        <% } %>
    </div>
</div>
</body>
</html>
