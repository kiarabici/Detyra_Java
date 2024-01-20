package com.example.detyrekursigreisialba;

import com.example.detyrekursigreisialba.model.Question;
import com.example.detyrekursigreisialba.model.Quiz;
import com.example.detyrekursigreisialba.service.QuizService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/quiz")
public class QuizServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QuizService quizService = new QuizService();
        List<Quiz> quizzes = quizService.getAllQuizzes();
        int selectedQuizId = Integer.parseInt(request.getParameter("selectedQuizId"));
        List<Question> questions = quizService.getQuestionsWithOptions(selectedQuizId);

        request.setAttribute("quizzes", quizzes);
        request.setAttribute("questions", questions);

        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
