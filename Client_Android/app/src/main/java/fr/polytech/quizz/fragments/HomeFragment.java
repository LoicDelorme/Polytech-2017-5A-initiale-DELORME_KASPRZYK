package fr.polytech.quizz.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import fr.polytech.quizz.R;
import fr.polytech.quizz.activities.IHome;

public class HomeFragment extends AbstractFragment implements View.OnClickListener {

    private IHome home;

    @Override
    public int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            this.home = (IHome) context;
        } catch (ClassCastException ex) {
            Log.e("HomeFragment", "ClassCastException", ex);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().findViewById(R.id.question_app_button).setOnClickListener(this);
        getActivity().findViewById(R.id.beer_app_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.question_app_button) {
            this.home.notifyQuestionApplicationHasBeenSelected();
        } else if (view.getId() == R.id.beer_app_button) {
            this.home.notifyBeerApplicationHasBeenSelected();
        }
    }
}