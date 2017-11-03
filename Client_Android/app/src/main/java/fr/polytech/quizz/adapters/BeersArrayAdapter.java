package fr.polytech.quizz.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import fr.polytech.quizz.R;
import fr.polytech.quizz.activities.IHome;
import fr.polytech.quizz.entities.Beer;
import fr.polytech.quizz.holders.BeerViewHolder;

public class BeersArrayAdapter extends ArrayAdapter<Beer> implements View.OnClickListener {

    public static final int BEER_IMAGE_WIDTH = 75;

    public static final int BEER_IMAGE_HEIGHT = 275;

    private final List<Beer> beers;

    private final IHome home;

    private final LayoutInflater layoutInflater;

    private final Context context;

    public BeersArrayAdapter(Context context, int textViewResourceId, List<Beer> beers, IHome home) {
        super(context, textViewResourceId, beers);

        this.beers = beers;
        this.home = home;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.beers.size();
    }

    @Nullable
    @Override
    public Beer getItem(int position) {
        return this.beers.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        BeerViewHolder beerViewHolder = null;

        if (convertView == null) {
            convertView = this.layoutInflater.inflate(R.layout.adapter_beers, null);

            beerViewHolder = new BeerViewHolder();
            beerViewHolder.setBeerImageView((ImageView) convertView.findViewById(R.id.beer_image_image_view));
            beerViewHolder.setBeerNameTextView((TextView) convertView.findViewById(R.id.beer_name_text_view));

            convertView.setTag(beerViewHolder);
            convertView.setOnClickListener(this);
        } else {
            beerViewHolder = (BeerViewHolder) convertView.getTag();
        }

        final Beer beer = getItem(position);
        Picasso.with(this.context).load(beer.getImage_url()).resize(BEER_IMAGE_WIDTH, BEER_IMAGE_HEIGHT).into(beerViewHolder.getBeerImageView());
        beerViewHolder.getBeerNameTextView().setText(beer.getName());
        beerViewHolder.setBeer(beer);

        return convertView;
    }

    @Override
    public void onClick(View view) {
        this.home.notifyBeerHasBeenSelected(((BeerViewHolder) view.getTag()).getBeer());
    }
}