<%@ page import="com.example.detyrekursigreisialba.model.Quiz" %>
<%@ page import="com.example.detyrekursigreisialba.model.Question" %>
<%@ page import="com.example.detyrekursigreisialba.model.Option" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.detyrekursigreisialba.service.QuizService" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Dashboard</title>
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

        h2, h3 {
            margin-bottom: 1em;
            color: #555;
        }

        ul {
            list-style-type: none;
            padding: 0;
        }

        li {
            margin-bottom: 0.5em;
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
            max-width: 800px;
            margin: 2em auto;
            background-color: #fff;
            padding: 2em;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .dropdown {
            margin-bottom: 1em;
        }
    </style>
</head>
<body>
<header>
    <h2>Quizzes Online</h2>
</header>

<div class="container">
    <%
        QuizService quizService = new QuizService();
        List<Quiz> quizzes = quizService.getAllQuizzes();
        int selectedQuizId = 0;
        if (request.getParameter("selectedQuizId") != null) {
            selectedQuizId = Integer.parseInt(request.getParameter("selectedQuizId"));
        }
        List<Question> questions = quizService.getQuestionsWithOptions(selectedQuizId);
    %>
    <h3>Quizzes</h3>
    <ul>
        <% for (Quiz quiz : quizzes) { %>
        <li><a href="quiz?selectedQuizId=<%= quiz.getId() %>"><%= quiz.getName() %>
        </a></li>
        <% } %>
    </ul>

    <!-- Display questions for the selected quiz -->
    <form method="post" action="quiz.jsp">
        <input type="hidden" name="quizId" value="<%= selectedQuizId %>"/>
        <h3>Quiz Questions</h3>
        <%
            for (Question question : questions) {
        %>
        <div class="dropdown">
            <p><%= question.getName() %>
            </p>
            <label>
                <select name="question<%= question.getIndex() %>" required>
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
        <button type="submit">Submit Answers</button>
    </form>
</div>
</body>
</html>
