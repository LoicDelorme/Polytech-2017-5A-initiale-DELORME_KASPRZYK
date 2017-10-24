package fr.polytech.quizz.services;

import java.util.ArrayList;

import fr.polytech.quizz.entities.Beer;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BeerService {

    @GET("beers")
    public Call<ArrayList<Beer>> listBeers();
}