<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Success</title>
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
    </style>
</head>
<body>
<header>
    <h2>Registration Successful</h2>
</header>

<div class="container">
    <p>Your registration was successful! Welcome to our platform.</p>

    <p><a href="dashboard.jsp">Start the quiz!</a></p>
</div>
</body>
</html>
