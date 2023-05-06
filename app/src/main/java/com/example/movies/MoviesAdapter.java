package com.example.movies;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private List<Movie> movies = new ArrayList<>();
    private OnReachEndListenner onReachEndListennerl;
    private onMovieClickListenner onMovieClickListenner;

    public void setOnReachEndListennerl(OnReachEndListenner onReachEndListennerl) {
        this.onReachEndListennerl = onReachEndListennerl;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public void setOnMovieClickListenner(MoviesAdapter.onMovieClickListenner onMovieClickListenner) {
        this.onMovieClickListenner = onMovieClickListenner;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.movie_item,
                parent,
                false
        );

        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        Glide.with(holder.itemView)
                .load(movie.getPoster().getUrl())
                .into(holder.image_cardViewPoster);


        double rating = movie.getRating().getKp();
        int backgroudId;
        if (rating > 7) {
            backgroudId = R.drawable.circle_green;
        } else if (rating > 5) {
            backgroudId = R.drawable.circle_orange;
        } else {
            backgroudId = R.drawable.circle_red;
        }
        Drawable backgroud = ContextCompat.getDrawable(holder.itemView.getContext(), backgroudId);
        holder.textViewRating.setBackground(backgroud);
        holder.textViewRating.setText(String.format("%.1f", rating));

        if (position >= movies.size() - 10 && onReachEndListennerl != null) {
            onReachEndListennerl.onReachEnd();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onMovieClickListenner != null) {
                    onMovieClickListenner.onMovieClick(movie);
                }
            }
        });

    }

    interface onMovieClickListenner {
        void onMovieClick(Movie movie);
    }


    @Override
    public int getItemCount() {
        return movies.size();
    }


    interface OnReachEndListenner {
        void onReachEnd();
    }


    static class MovieViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image_cardViewPoster;
        private final TextView textViewRating;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            image_cardViewPoster = itemView.findViewById(R.id.image_cardViewPoster);
            textViewRating = itemView.findViewById(R.id.textViewRating);

        }
    }
}