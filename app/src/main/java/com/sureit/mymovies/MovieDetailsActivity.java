package com.sureit.mymovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import am.appwise.components.ni.NoInternetDialog;

public class MovieDetailsActivity extends AppCompatActivity {
    NoInternetDialog noInternetDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        setupUI();

    }

    public void setupUI(){
        noInternetDialog = new NoInternetDialog.Builder(this).build();
        ImageView posterImageView = (ImageView) findViewById(R.id.posterImageView);
        TextView titleTextView = (TextView) findViewById(R.id.titleTextView);
        TextView description = (TextView) findViewById(R.id.tVdescription);
        TextView releaseTV= (TextView) findViewById(R.id.tVreleaseDate);
        TextView ratingTV= (TextView) findViewById(R.id.tVRatVal);

//        Intent intent = getIntent();
//        final String titleText = intent.getStringExtra(MovieAdapter.KEY_NAME);
//        String image = intent.getStringExtra(MovieAdapter.KEY_IMAGE);
//        final String descriptionText = intent.getStringExtra(MovieAdapter.KEY_DESCRIPTION);
//        final String ratings =intent.getStringExtra(MovieAdapter.KEY_VOTE_AVERAGE)+" / 10";
//        final String releaseDate=intent.getStringExtra(MovieAdapter.KEY_RELEASE_DATE);

        Bundle data=getIntent().getExtras();
        assert data != null;
        MovieList movieList=(MovieList) data.getParcelable(Constants.PARCEL_KEY);
        assert movieList != null;
        final String titleText = movieList.getTitle();
        String image = "https://image.tmdb.org/t/p/w185/"+ movieList.getPosterUrl();
        final String descriptionText = movieList.getDescription();
        final String ratings =movieList.getVote_average()+" / 10";
        final String releaseDate= movieList.getReleaseDate();



        Picasso.with(this)
                .load(image)
                .into(posterImageView);

        titleTextView.setText(titleText);
        ratingTV.setText(ratings);
        releaseTV.setText(releaseDate);
        description.setText(descriptionText);


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        noInternetDialog.onDestroy();
    }
}
