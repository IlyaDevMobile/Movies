package com.example.movies;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MoveiResponse {


@SerializedName("docs")
    private List<Movie> movies;

    public MoveiResponse(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "MoveiResponse{" +
                "movies=" + movies +
                '}';
    }
}
