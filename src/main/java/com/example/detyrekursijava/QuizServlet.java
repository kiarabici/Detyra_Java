package com.example.detyrekursijava;

import com.example.detyrekursijava.model.Option;
import com.example.detyrekursijava.model.Question;
import com.example.detyrekursijava.model.Result;
import com.example.detyrekursijava.model.UserAnswer;
import com.example.detyrekursijava.service.QuizService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet("/quiz")
public class QuizServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("quiz.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        int selectedQuizId = Integer.parseInt(request.getParameter("selectedQuizId"));
        QuizService quizService = new QuizService();

        List<Question> questions = quizService.getQuestionsWithOptions(selectedQuizId);

        Result result = new Result();
        result.setQuizId(selectedQuizId);
        result.setUsername(username);

        List<UserAnswer> userAnswers = new ArrayList<>();

        for (Question question : questions) {
            int questionIndex = question.getIndex();
            String selectedOptionValue = request.getParameter("question" + questionIndex);

            Option selectedOption = question.getOptions().stream()
                    .filter(option -> Objects.equals(option.getValue(), selectedOptionValue))
                    .findFirst()
                    .orElse(null);

            if (selectedOption != null) {
                UserAnswer userAnswer = new UserAnswer();
                userAnswer.setOptionId(selectedOption.getId());
                userAnswer.setQuestionId(question.getId());
                userAnswers.add(userAnswer);
            }
        }

        if (userAnswers.size() > 0) {
            quizService.saveUserQuizResults(result, userAnswers);
        }
        response.sendRedirect("my-quizzes.jsp");
    }
}
