package fr.polytech.quizz.holders;

import android.widget.ImageView;
import android.widget.TextView;

import fr.polytech.quizz.entities.Beer;

public class BeerViewHolder {

    private Beer beer;

    private ImageView beerImageView;

    private TextView beerNameTextView;

    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    public ImageView getBeerImageView() {
        return beerImageView;
    }

    public void setBeerImageView(ImageView beerImageView) {
        this.beerImageView = beerImageView;
    }

    public TextView getBeerNameTextView() {
        return beerNameTextView;
    }

    public void setBeerNameTextView(TextView beerNameTextView) {
        this.beerNameTextView = beerNameTextView;
    }
}