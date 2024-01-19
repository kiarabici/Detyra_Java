<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
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

        form {
            max-width: 400px;
            margin: 2em auto;
            background-color: #fff;
            padding: 2em;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 0.5em;
        }

        input {
            width: 100%;
            padding: 0.5em;
            margin-bottom: 1em;
            box-sizing: border-box;
        }

        button {
            background-color: #007bff;
            color: #fff;
            padding: 0.8em 1.5em;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 1em;
        }

        button:hover {
            background-color: #0056b3;
        }

        .error-message {
            color: red;
            margin-top: 1em;
        }
    </style>
</head>
<body>
<header>
    <h2>Login</h2>
</header>

<div>
    <p><a href="index.jsp">Home</a></p>

    <c:if test="${param.error ne null}">
        <p class="error-message">Invalid username or password. Please try again.</p>
    </c:if>

    <form action="auth?action=login" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>
        <button type="submit">Login</button>
    </form>
</div>
</body>
</html>
