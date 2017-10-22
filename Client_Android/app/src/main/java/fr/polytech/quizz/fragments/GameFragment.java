package fr.polytech.quizz.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import fr.polytech.quizz.R;
import fr.polytech.quizz.activities.IHome;
import fr.polytech.quizz.entities.IntentRequest;
import fr.polytech.quizz.entities.Question;
import fr.polytech.quizz.services.GameEngineIntentService;

public class GameFragment extends AbstractFragment implements View.OnClickListener {

    private static final int questionTextViewId = R.id.question_text_view;

    private static final int[] answerButtonsIds = new int[]{R.id.answer1_button, R.id.answer2_button, R.id.answer3_button, R.id.answer4_button};

    private IHome home;

    private final BroadcastReceiver statusBroadcastReceiver = new StatusBroadcastReceiver();

    private final BroadcastReceiver questionBroadcastReceiver = new QuestionBroadcastReceiver();

    @Override
    public int getLayout() {
        return R.layout.fragment_game;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            this.home = (IHome) context;
        } catch (ClassCastException ex) {
            Log.e("GameFragment", "ClassCastException", ex);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        for (int button : answerButtonsIds) {
            getActivity().findViewById(button).setOnClickListener(this);
        }

        final Intent questionIntent = new Intent(getActivity(), GameEngineIntentService.class);
        questionIntent.putExtra(GameEngineIntentService.REQUEST_MESSAGE_KEY, new IntentRequest(GameEngineIntentService.QUESTION_MESSAGE_KEY, null));
        getActivity().startService(questionIntent);
    }

    @Override
    public void onResume() {
        super.onResume();

        final IntentFilter intentStatusFilter = new IntentFilter();
        intentStatusFilter.addCategory(Intent.CATEGORY_DEFAULT);
        intentStatusFilter.addAction(GameEngineIntentService.STATUS_ACTION);

        final IntentFilter intentQuestionFilter = new IntentFilter();
        intentQuestionFilter.addCategory(Intent.CATEGORY_DEFAULT);
        intentQuestionFilter.addAction(GameEngineIntentService.QUESTION_ACTION);

        getActivity().registerReceiver(this.statusBroadcastReceiver, intentStatusFilter);
        getActivity().registerReceiver(this.questionBroadcastReceiver, intentQuestionFilter);

    }

    @Override
    public void onPause() {
        super.onPause();

        getActivity().unregisterReceiver(this.statusBroadcastReceiver);
        getActivity().unregisterReceiver(this.questionBroadcastReceiver);
    }

    public void onClick(View view) {
        this.home.notifyAnswerHasBeenSelected(((Button) view).getText());
    }

    private class StatusBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            final String status = intent.getStringExtra(GameEngineIntentService.STATUS_MESSAGE_KEY);

            final Toast toast = Toast.makeText(getContext(), status, Toast.LENGTH_SHORT);
            toast.show();

            final Intent questionIntent = new Intent(getActivity(), GameEngineIntentService.class);
            questionIntent.putExtra(GameEngineIntentService.REQUEST_MESSAGE_KEY, new IntentRequest(GameEngineIntentService.QUESTION_MESSAGE_KEY, null));
            getActivity().startService(questionIntent);
        }
    }

    private class QuestionBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            final Question question = (Question) intent.getSerializableExtra(GameEngineIntentService.QUESTION_MESSAGE_KEY);

            if (question != null) {
                ((TextView) getActivity().findViewById(questionTextViewId)).setText(question.getQuestion());

                for (int offset = 0; offset < answerButtonsIds.length; offset++) {
                    ((Button) getActivity().findViewById(answerButtonsIds[offset])).setText(question.getAvailableAnswers().get(offset));
                }

                return;
            }

            home.notifyNoMoreQuestions();
        }
    }
}