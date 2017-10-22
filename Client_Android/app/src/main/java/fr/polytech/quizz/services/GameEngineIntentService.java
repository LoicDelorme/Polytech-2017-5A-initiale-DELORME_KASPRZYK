package fr.polytech.quizz.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import fr.polytech.quizz.entities.Question;
import fr.polytech.quizz.entities.IntentRequest;
import fr.polytech.quizz.entities.builders.QuestionBuilder;

public class GameEngineIntentService extends IntentService {

    public static final String INTENT_SERVICE_NAME = "GameEngineIntentService";

    public static final String REQUEST_MESSAGE_KEY = "request";

    public static final String QUESTION_MESSAGE_KEY = "question";

    public static final String ANSWER_MESSAGE_KEY = "answer";

    public static final String STATUS_MESSAGE_KEY = "status";

    public static String STATUS_ACTION = "status_action";

    public static String QUESTION_ACTION = "question_action";

    private static final List<Question> questions = new ArrayList<Question>();

    private static final AtomicInteger counter = new AtomicInteger(0);

    {
        questions.add(new QuestionBuilder().addQuestion("D'où venez-vous ?").addAvailableAnswer("France").addAvailableAnswer("Allemagne").addAvailableAnswer("Italie").addAvailableAnswer("Suisse").addCorrectAnswer("France").build());
        questions.add(new QuestionBuilder().addQuestion("Où se situe Polytech Lyon ?").addAvailableAnswer("Paris").addAvailableAnswer("Marseille").addAvailableAnswer("Lyon").addAvailableAnswer("Toulouse").addCorrectAnswer("Lyon").build());
        Collections.shuffle(questions);
    }

    public GameEngineIntentService() {
        super(INTENT_SERVICE_NAME);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        final IntentRequest userRequest = (IntentRequest) intent.getSerializableExtra(REQUEST_MESSAGE_KEY);
        switch (userRequest.getMessageKey()) {
            case QUESTION_MESSAGE_KEY:
                sendNextQuestion();
                break;
            case ANSWER_MESSAGE_KEY:
                checkSubmittedAnswer(userRequest.getMessage());
                break;
        }
    }

    private void sendNextQuestion() {
        final int nextQuestionOffset = counter.getAndIncrement();
        final Question nextQuestion = (nextQuestionOffset < questions.size() ? questions.get(nextQuestionOffset) : null);

        final Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(QUESTION_ACTION);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        broadcastIntent.putExtra(QUESTION_MESSAGE_KEY, nextQuestion);
        sendBroadcast(broadcastIntent);
    }

    private void checkSubmittedAnswer(String submittedAnswer) {
        final Question currentQuestion = questions.get(counter.get());

        final Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(STATUS_ACTION);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        broadcastIntent.putExtra(STATUS_MESSAGE_KEY, (currentQuestion.isCorrectAnswer(submittedAnswer) ? "Answer correct!" : "Answer incorrect!"));
        sendBroadcast(broadcastIntent);
    }
}