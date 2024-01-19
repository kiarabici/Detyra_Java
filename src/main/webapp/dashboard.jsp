<%@ page import="com.example.detyrekursigreisialba.model.Quiz" %>
<%@ page import="com.example.detyrekursigreisialba.model.Question" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    </style>
</head>
<body>
<header>
    <h2>Welcome to Your Dashboard</h2>
</header>

<div class="container">
    <!-- Display a list of quizzes -->
    <h3>Quizzes</h3>
    <ul>
        <% for (Quiz quiz : quizzes) { %>
        <li><a href="dashboard.jsp?selectedQuizId=<%= quiz.getId() %>"><%= quiz.getName() %></a></li>
        <% } %>
    </ul>

    <!-- Display questions for the selected quiz -->
    <h3>Quiz Questions</h3>
    <%
        for (Question question : questions) {
    %>
    <div>
        <p><%= question.getName() %></p>
        <!-- Add other HTML for displaying options, etc. -->
    </div>
    <%
        }
    %>

    <!-- Include the quiz form here -->
</div>
</body>
</html>
