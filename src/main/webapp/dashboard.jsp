<%@ include file="common.jsp" %>
<%@ page import="java.util.Objects" %>
<%@ page import="com.example.detyrekursigreisialba.model.Quiz" %>
<%@ page import="com.example.detyrekursigreisialba.model.Question" %>
<%@ page import="com.example.detyrekursigreisialba.model.Option" %>
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
    int selectedQuizId = 0;
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
<body>
<div class="container mt-5">
    <form method="post" action="quiz.jsp">
        <input type="hidden" name="quizId" value="<%= selectedQuizId %>"/>
        <h3>Quizzes</h3>
        <div class="row">
            <% for (Quiz quiz : quizzes) { %>
            <div class="card mr-4" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title"><%= quiz.getName() %>
                    </h5>
                    <p class="card-text">Explore this quiz</p>
                    <a class="btn btn-primary" href="quiz?selectedQuizId=<%= quiz.getId() %>"><%= quiz.getName() %>
                    </a>
                </div>
            </div>
            <% } %>
        </div>
        <h3 class="mt-4">Quiz Questions</h3>
        <%
            for (Question question : questions) {
        %>
        <div class="mb-3">
            <p><%= question.getName() %>
            </p>
            <label class="form-label">Select an option:
                <select class="form-select mb-3" name="question<%= question.getIndex() %>" required>
                    <option value="" selected disabled>Select an option</option>
                    <% for (Option option : question.getOptions()) { %>
                    <option value="<%= option.getValue() %>"><%= option.getValue() %>
                    </option>
                    <% } %>
                </select>
            </label>
        </div>
        <%
            }
        %>
        <button type="submit" class="btn btn-primary">Submit Answers</button>
    </form>
</div>
</body>
</html>
