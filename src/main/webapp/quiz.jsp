<%@ include file="common.jsp" %>
<%@ page import="com.example.detyrekursijava.model.Question" %>
<%@ page import="com.example.detyrekursijava.service.QuizService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Objects" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    session = request.getSession();
    String username = (String) session.getAttribute("username");
    if (Objects.isNull(username)) {
        response.sendRedirect("index.jsp");
    }
    QuizService quizService = new QuizService();
    int selectedQuizId = -1;
    if (request.getParameter("selectedQuizId") != null) {
        selectedQuizId = Integer.parseInt(request.getParameter("selectedQuizId"));
    }
    List<Question> questions = quizService.getQuestionsWithOptions(selectedQuizId);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Quiz</title>
</head>
<style>
    .question {
        font-size: 1.2em;
        margin-bottom: 15px;
    }
    .options label {
        display: block;
        margin-bottom: 5px;
    }
    .btn {
        display: inline-block;
        padding: 10px 20px;
        margin-top: 20px;
        border: none;
        border-radius: 4px;
        background-color: #007bff;
        color: white;
        cursor: pointer;
    }
    .btn-secondary {
        background-color: #6c757d;
    }
</style>
<body>
<div class="container mt-5" >
    <p><a href="dashboard.jsp" class="btn btn-secondary">Back</a></p>
    <form action="quiz" method="post" style="background-color: #F8F9FA; padding: 20px">
        <input type="hidden" name="selectedQuizId" value="<%=selectedQuizId%>">
        <input type="hidden" name="username" value="<%=username%>">
        <c:forEach var="question" items="<%=questions%>">
            <input type="hidden" name="quizId" value="${question.quizId}">
            <input type="hidden" name="currentQuestion" value="${question.index}">
            <p style="font-size:18pt">${question.name}</p>
            <c:forEach var="option" items="${question.options}">
                <label class="px-3">
                    <input type="radio" name="question${question.index}" value="${option.value}" required>
                        ${option.value}
                </label>
            </c:forEach>
            <br><br>
        </c:forEach>
        <div>
            <button type="submit" class="btn btn-secondary">Finish Quiz</button>
        </div>
    </form>
</div>
</body>
</html>
