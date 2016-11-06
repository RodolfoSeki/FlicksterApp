package com.rodolfoseki.flickster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rodolfoseki.flickster.R;
import com.rodolfoseki.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by spread on 09/10/2016.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie>  {

    public MovieArrayAdapter(Context context, List<Movie> movies){
        super(context, android.R.layout.simple_list_item_1 , movies);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int orientation = getContext().getResources().getConfiguration().orientation;

        //get the data item for position
        Movie movie = getItem(position);

        //check the existing view being reused
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
        }

        // find image view
        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
        // clear out image from convertView
        ivImage.setImageResource(0);

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);

        // populate data
        tvTitle.setText(movie.getOrigialTitle());
        tvOverview.setText(movie.getOverview());

        // depending on the orientation, show poster or backdrop image
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Picasso.with(getContext()).load(movie.getPosterPath())
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.loading_p) // current placeholder is ugly, find new to match poster
                    .error(R.drawable.error_placeholder_portrait)
                    .into(ivImage);

        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Picasso.with(getContext()).load(movie.getBackdropPath())
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.loading_l) // current placeholder is ugly, find new to match backdrop
                    .error(R.drawable.error_placeholder_landscape) // can also be a drawable
                    .into(ivImage);

        }
        // return the view
        return convertView;
    }
}
