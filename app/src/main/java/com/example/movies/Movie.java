package com.example.movies;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("discription")
    private String discription;
    @SerializedName("year")
    private int year;
    @SerializedName("poster")
    private Poster poster;

    @SerializedName("rating")
    private Rating rating;

    public Movie(int id, String name, String discription, int year, Poster poster, Rating rating) {
        this.id = id;
        this.name = name;
        this.discription = discription;
        this.year = year;
        this.poster = poster;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", discription='" + discription + '\'' +
                ", year=" + year +
                ", poster=" + poster +
                ", rating=" + rating +
                '}';
    }
}
