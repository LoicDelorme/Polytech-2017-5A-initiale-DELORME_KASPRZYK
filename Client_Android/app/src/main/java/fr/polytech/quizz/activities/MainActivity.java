package fr.polytech.quizz.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import fr.polytech.quizz.R;
import fr.polytech.quizz.entities.IntentRequest;
import fr.polytech.quizz.fragments.GameFragment;
import fr.polytech.quizz.fragments.HomeFragment;
import fr.polytech.quizz.services.GameEngineIntentService;

public class MainActivity extends AppCompatActivity implements IHome {

    private static final int fragmentContainerId = R.id.fragment_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(fragmentContainerId) != null) {
            if (savedInstanceState == null) {
                final HomeFragment homeFragment = new HomeFragment();
                homeFragment.setArguments(getIntent().getExtras());

                final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(fragmentContainerId, homeFragment);
                transaction.commit();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        logMessage("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        logMessage("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        logMessage("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        logMessage("onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logMessage("onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logMessage("onDestroy");
    }

    private void logMessage(String message) {
        Log.d(getLocalClassName(), message);
    }

    @Override
    public void notifyModeHasBeenSelected(Mode mode) {
        final GameFragment gameFragment = new GameFragment();
        gameFragment.setArguments(getIntent().getExtras());

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(fragmentContainerId, gameFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void notifyAnswerHasBeenSelected(CharSequence answer) {
        final Intent intent = new Intent(this, GameEngineIntentService.class);
        intent.putExtra(GameEngineIntentService.REQUEST_MESSAGE_KEY, new IntentRequest(GameEngineIntentService.ANSWER_MESSAGE_KEY, answer.toString()));
        startService(intent);
    }

    @Override
    public void notifyNoMoreQuestions() {
        final HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(getIntent().getExtras());

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(fragmentContainerId, homeFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        final Toast toast = Toast.makeText(getApplicationContext(), "No more questions!", Toast.LENGTH_SHORT);
        toast.show();
    }
}