package fr.polytech.quizz.entities;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {

    private final String question;

    private final List<String> availableAnswers;

    private final String correctAnswer;

    public Question(String question, List<String> availableAnswers, String correctAnswer) {
        this.question = question;
        this.availableAnswers = availableAnswers;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAvailableAnswers() {
        return availableAnswers;
    }

    public boolean isCorrectAnswer(String answer) {
        return this.correctAnswer.equals(answer);
    }
}