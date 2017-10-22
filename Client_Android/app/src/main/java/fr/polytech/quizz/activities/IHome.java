package fr.polytech.quizz.activities;

public interface IHome {

    public void notifyModeHasBeenSelected(Mode mode);

    public void notifyAnswerHasBeenSelected(CharSequence answer);
}