package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

public class MovieDetailsActivity extends AppCompatActivity
{
    Movie movie;
    TextView tvTitle;
    TextView tvOverview;
    RatingBar rbVoteAverage;
    ImageView ivPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);
        rbVoteAverage = findViewById(R.id.rbVoteAverage);
        ivPoster = findViewById(R.id.ivPoster);

        // unwrap the movie passed in thru intent; using simple name as key
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for %s", movie.getTitle()));

        // set the title and overview from the movie
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        // set the rb value by dividing movie.getvoteaverage by 2
        float voteStars = movie.getVoteAverage().floatValue() / 2.0f;
        rbVoteAverage.setRating(voteStars);
        // use Glide to load in picture
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            Glide.with(this)
                    .load(movie.getBackdropPath())
                    .placeholder(R.drawable.flicks_backdrop_placeholder)
                    .transform(new RoundedCorners(25))
                    .into(ivPoster);
        }
        else
        {
            Glide.with(this)
                    .load(movie.getPosterPath())
                    .placeholder(R.drawable.flicks_movie_placeholder)
                    .transform(new RoundedCorners(25))
                    .into(ivPoster);
        }

    }
}