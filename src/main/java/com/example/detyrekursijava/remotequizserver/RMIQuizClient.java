package com.example.detyrekursijava.remotequizserver;

import com.example.detyrekursijava.model.Quiz;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class RMIQuizClient {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1098);
            com.example.detyrekursijava.rmi.RemoteQuizService remoteQuizService = (com.example.detyrekursijava.rmi.RemoteQuizService) registry.lookup("RemoteQuizService");

            List<Quiz> quizzes = remoteQuizService.getAllQuizzes();

            for (Quiz quiz : quizzes) {
                System.out.println(quiz.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

