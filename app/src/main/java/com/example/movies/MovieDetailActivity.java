package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailActivity extends AppCompatActivity {

    private static final  String TAG = "MovieDetailActivity";
    private static final String EXTRA_MOVIE = "movie";

    private MovieDetailViewModel viewModel;
    private ImageView imageViewPoster;
    private TextView textViewTitle;
    private TextView textViewYear;
    private TextView textViewDes;
    private RecyclerView recyclerViewTrailer;

    private ReviewAdapter reviewAdapter;

    private RecyclerView recyclerViewReviews;
    private TrailersAdapter trailersAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        viewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);
        initViews();
        trailersAdapter = new TrailersAdapter();
        reviewAdapter = new ReviewAdapter();
        recyclerViewTrailer.setAdapter(trailersAdapter);
        recyclerViewReviews.setAdapter(reviewAdapter);
        Movie movie =(Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);

        Glide.with(this)
                .load(movie.getPoster().getUrl())
                .into(imageViewPoster);
        textViewTitle.setText(movie.getName());
        textViewYear.setText(String.valueOf(movie.getYear()));
        textViewDes.setText(String.valueOf(movie.getDescription()));

        viewModel.loadTrailers(movie.getId());
        viewModel.getTrailers().observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(List<Trailer> trailers) {
                trailersAdapter.setTrailers(trailers);
            }
        });
        trailersAdapter.setOnTrailerClickListener(new TrailersAdapter.OnTrailerClickListener() {
            @Override
            public void onTrailerClick(Trailer trailer) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(trailer.getUrl()));
                startActivity(intent);

            }
        });
        viewModel.getReviews().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviewList) {
                reviewAdapter.setReviews(reviewList);
            }
        });
        viewModel.loadReviews(movie.getId());



    }

    private void initViews(){
        imageViewPoster = findViewById(R.id.imageViewPoster);
        recyclerViewReviews = findViewById(R.id.recyclerViewReviews);
        textViewTitle = findViewById(R.id.textViewTitle);
         textViewYear = findViewById(R.id.textViewYear);
         textViewDes = findViewById(R.id.textViewDes);
        recyclerViewTrailer = findViewById(R.id.recyclerViewTrailer);
    }

    public static Intent newIntent(Context context, Movie movie){
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }
}