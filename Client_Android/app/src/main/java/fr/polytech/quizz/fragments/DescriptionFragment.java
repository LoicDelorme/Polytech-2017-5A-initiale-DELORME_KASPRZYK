package fr.polytech.quizz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.polytech.quizz.R;
import fr.polytech.quizz.adapters.BeersArrayAdapter;
import fr.polytech.quizz.entities.Beer;
import fr.polytech.quizz.services.BeersIntentService;

public class DescriptionFragment extends AbstractFragment {

    @Override
    public int getLayout() {
        return R.layout.fragment_description;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final Beer beer = (Beer) getArguments().getSerializable(BeersIntentService.BEERS_MESSAGE_KEY);

        final ImageView beerImageView = (ImageView) getActivity().findViewById(R.id.beer_image_image_view);
        Picasso.with(getContext()).load(beer.getImage_url()).resize(BeersArrayAdapter.BEER_IMAGE_WIDTH, BeersArrayAdapter.BEER_IMAGE_HEIGHT).into(beerImageView);

        final TextView beerNameTextView = (TextView) getActivity().findViewById(R.id.beer_name_text_view);
        beerNameTextView.setText(beer.getName());

        final TextView beerFirstBrewedTextView = (TextView) getActivity().findViewById(R.id.beer_fist_brewed_text_view);
        beerFirstBrewedTextView.setText(beer.getFirst_brewed());

        final TextView beerDescriptionTextView = (TextView) getActivity().findViewById(R.id.beer_description_text_view);
        beerDescriptionTextView.setText(beer.getDescription());

        final TextView beerAbvTextView = (TextView) getActivity().findViewById(R.id.beer_abv_text_view);
        beerAbvTextView.setText(String.valueOf(beer.getAbv()));

        final TextView beerIbuTextView = (TextView) getActivity().findViewById(R.id.beer_ibu_text_view);
        beerIbuTextView.setText(String.valueOf(beer.getIbu()));

        final TextView beerBrewersTipsTextView = (TextView) getActivity().findViewById(R.id.beer_brewers_tips_text_view);
        beerBrewersTipsTextView.setText(beer.getBrewers_tips());
    }
}