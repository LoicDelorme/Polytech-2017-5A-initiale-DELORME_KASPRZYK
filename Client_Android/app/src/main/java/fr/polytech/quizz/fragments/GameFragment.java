package fr.polytech.quizz.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import fr.polytech.quizz.R;
import fr.polytech.quizz.activities.IHome;

public class GameFragment extends AbstractFragment implements View.OnClickListener {

    private static final int[] answerButtons = new int[]{ R.id.answer1_button, R.id.answer2_button, R.id.answer3_button, R.id.answer4_button };

    private IHome home;

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

        for (int button : answerButtons) {
            getActivity().findViewById(button).setOnClickListener(this);
        }
    }

    public void onClick(View view) {
        this.home.notifyAnswerHasBeenSelected(((Button) view).getText());
    }
}