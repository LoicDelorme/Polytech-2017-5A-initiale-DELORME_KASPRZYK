package fr.polytech.quizz.activities;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public abstract class AbstractActivity extends AppCompatActivity {

    private final int layout;

    public AbstractActivity(@LayoutRes int layout) {
        this.layout = layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(this.layout);
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
        Log.d(getClass().getSimpleName(), message);
    }
}