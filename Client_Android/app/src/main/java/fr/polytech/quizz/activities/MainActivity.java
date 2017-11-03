package fr.polytech.quizz.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import fr.polytech.quizz.R;
import fr.polytech.quizz.adapters.BeersArrayAdapter;
import fr.polytech.quizz.entities.Beer;
import fr.polytech.quizz.fragments.DescriptionFragment;
import fr.polytech.quizz.fragments.HomeFragment;
import fr.polytech.quizz.services.BeersIntentService;

public class MainActivity extends AppCompatActivity implements IHome {

    private static final int fragmentContainerId = R.id.fragment_container;

    private final BroadcastReceiver statusBroadcastReceiver = new BeerBroadcastReceiver();

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

        final IntentFilter intentBeersFilter = new IntentFilter();
        intentBeersFilter.addCategory(Intent.CATEGORY_DEFAULT);
        intentBeersFilter.addAction(BeersIntentService.BEERS_ACTION);

        registerReceiver(this.statusBroadcastReceiver, intentBeersFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        logMessage("onPause");

        unregisterReceiver(this.statusBroadcastReceiver);
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
    public void notifyRetrieveBeers() {
        startService(new Intent(this, BeersIntentService.class));
    }

    @Override
    public void notifyBeerHasBeenSelected(Beer beer) {
        final Bundle extras = getIntent().getExtras() == null ? new Bundle() : getIntent().getExtras();
        extras.putParcelable(BeersIntentService.BEERS_MESSAGE_KEY, beer);

        final DescriptionFragment descriptionFragment = new DescriptionFragment();
        descriptionFragment.setArguments(extras);

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(fragmentContainerId, descriptionFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private class BeerBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            final ArrayList<Beer> beers = (ArrayList<Beer>) intent.getSerializableExtra(BeersIntentService.BEERS_MESSAGE_KEY);
            final BeersArrayAdapter beersArrayAdapter = new BeersArrayAdapter(getApplicationContext(), R.layout.adapter_beers, beers, MainActivity.this);

            final ListView beersListView = (ListView) findViewById(R.id.beers_list_view);
            beersListView.setAdapter(beersArrayAdapter);
        }
    }
}