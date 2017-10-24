package fr.polytech.quizz.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import fr.polytech.quizz.R;
import fr.polytech.quizz.activities.IHome;

public class HomeFragment extends AbstractFragment implements View.OnClickListener {

    private static final int beersButtonId = R.id.beers_button;

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

        getActivity().findViewById(beersButtonId).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        this.home.notifyRetrieveBeers();
    }
}