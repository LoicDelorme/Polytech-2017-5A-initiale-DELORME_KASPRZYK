package fr.polytech.quizz.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import fr.polytech.quizz.R;
import fr.polytech.quizz.activities.IHome;
import fr.polytech.quizz.activities.Mode;

public class GameHomeFragment extends AbstractFragment implements View.OnClickListener {

    private static final int[] modeButtonsIds = new int[]{ R.id.offline_mode_button_1vsAI, R.id.offline_mode_button_1vs1, R.id.offline_mode_button_teamvsteam, R.id.online_mode_button_1vs1 };

    private IHome home;

    @Override
    public int getLayout() {
        return R.layout.fragment_game_home;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            this.home = (IHome) context;
        } catch (ClassCastException ex) {
            Log.e("GameHomeFragment", "ClassCastException", ex);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        for (int button : modeButtonsIds) {
            getActivity().findViewById(button).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        Mode selectedMode = null;
        switch (view.getId()) {
            case R.id.offline_mode_button_1vs1:
                selectedMode = Mode.OFFLINE_MODE_1vs1;
                break;
            case R.id.offline_mode_button_1vsAI:
                selectedMode = Mode.OFFLINE_MODE_1vsAI;
                break;
            case R.id.offline_mode_button_teamvsteam:
                selectedMode = Mode.OFFLINE_MODE_TEAMvsTEAM;
                break;
            case R.id.online_mode_button_1vs1:
                selectedMode = Mode.ONLINE_MODE_1vs1;
                break;
            default:
                break;
        }

        this.home.notifyModeHasBeenSelected(selectedMode);
    }
}