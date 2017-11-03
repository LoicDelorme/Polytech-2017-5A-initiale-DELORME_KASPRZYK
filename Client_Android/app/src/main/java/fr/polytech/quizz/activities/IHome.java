package fr.polytech.quizz.activities;

import fr.polytech.quizz.entities.Beer;

public interface IHome {

    public void notifyQuestionApplicationHasBeenSelected();

    public void notifyBeerApplicationHasBeenSelected();

    public void notifyModeHasBeenSelected(Mode mode);

    public void notifyAnswerHasBeenSelected(CharSequence answer);

    public void notifyNoMoreQuestions();

    public void notifyRetrieveBeers();

    public void notifyBeerHasBeenSelected(Beer beer);
}