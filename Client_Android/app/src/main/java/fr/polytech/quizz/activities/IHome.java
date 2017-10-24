package fr.polytech.quizz.activities;

import fr.polytech.quizz.entities.Beer;

public interface IHome {

    public void notifyRetrieveBeers();

    public void notifyBeerHasBeenSelected(Beer beer);
}