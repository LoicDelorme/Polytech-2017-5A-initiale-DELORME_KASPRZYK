package fr.polytech.quizz.entities.builders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.polytech.quizz.entities.Question;

public class QuestionBuilder implements Serializable {

    public static final int AVAILABLE_ANSWERS_REQUIREMENT = 4;

    private String question;

    private final List<String> availableAnswers;

    private String correctAnswer;

    public QuestionBuilder() {
        this.availableAnswers = new ArrayList<String>();
    }

    public QuestionBuilder addQuestion(String question) {
        this.question = question;
        return this;
    }

    public QuestionBuilder addAvailableAnswer(String answer) {
        this.availableAnswers.add(answer);
        return this;
    }

    public QuestionBuilder addCorrectAnswer(String answer) {
        this.correctAnswer = answer;
        return this;
    }

    public Question build() {
        if (this.question == null || this.question.isEmpty()) {
            throw new RuntimeException("Invalid question");
        }

        if (this.availableAnswers.size() != AVAILABLE_ANSWERS_REQUIREMENT) {
            throw new RuntimeException("Invalid number of available answers");
        }

        if (this.correctAnswer == null || this.correctAnswer.isEmpty()) {
            throw new RuntimeException("Invalid correct answer");
        }

        return new Question(this.question, this.availableAnswers, this.correctAnswer);
    }
}