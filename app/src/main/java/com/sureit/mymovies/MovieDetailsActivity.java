package com.sureit.mymovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ImageView posterImageView = (ImageView) findViewById(R.id.posterImageView);
        TextView titleTextView = (TextView) findViewById(R.id.titleTextView);
        TextView description = (TextView) findViewById(R.id.tVdescription);
        TextView releaseTV= (TextView) findViewById(R.id.tVreleaseDate);
        TextView ratingTV= (TextView) findViewById(R.id.tVRatVal);


        Intent intent = getIntent();
        final String titleText = intent.getStringExtra(MovieAdapter.KEY_NAME);
        String image = intent.getStringExtra(MovieAdapter.KEY_IMAGE);
        final String descriptionText = intent.getStringExtra(MovieAdapter.KEY_DESCRIPTION);
        final String ratings =intent.getStringExtra(MovieAdapter.KEY_VOTE_AVERAGE)+" / 10";
        final String releaseDate=intent.getStringExtra(MovieAdapter.KEY_RELEASE_DATE);


        Picasso.with(this)
                .load(image)
                .into(posterImageView);

        titleTextView.setText(titleText);
        ratingTV.setText(ratings);
        releaseTV.setText(releaseDate);
        description.setText(descriptionText);


    }
}
