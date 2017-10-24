package fr.polytech.quizz.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

import fr.polytech.quizz.entities.Beer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BeersIntentService extends IntentService implements Callback<ArrayList<Beer>> {

    public static final String INTENT_SERVICE_NAME = "BeersIntentService";

    public static final String BEERS_MESSAGE_KEY = "beers";

    public static String BEERS_ACTION = "beers_action";

    public BeersIntentService() {
        super(INTENT_SERVICE_NAME);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.punkapi.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final BeerService beerService = retrofit.create(BeerService.class);

        final Call<ArrayList<Beer>> beers = beerService.listBeers();
        beers.enqueue(this);
    }

    @Override
    public void onResponse(Call<ArrayList<Beer>> call, Response<ArrayList<Beer>> response) {
        if (response.isSuccessful()) {
            final ArrayList<Beer> beers = response.body();

            final Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(BEERS_ACTION);
            broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
            broadcastIntent.putExtra(BEERS_MESSAGE_KEY, beers);
            sendBroadcast(broadcastIntent);
        } else {
            Log.e("BeersIntentService", response.errorBody().toString());
        }
    }

    @Override
    public void onFailure(Call<ArrayList<Beer>> call, Throwable t) {
        Log.e("BeersIntentService", t.getMessage());
        t.printStackTrace();
    }
}