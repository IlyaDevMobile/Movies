package com.example.movies;

import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("review")
    private String review;

    @SerializedName("type")
    private String type;

    @SerializedName("author")
    private String author;

    public Review(String review, String type, String author) {
        this.review = review;
        this.type = type;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Review{" +
                "review='" + review + '\'' +
                ", type='" + type + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public String getReview() {
        return review;
    }

    public String getType() {
        return type;
    }

    public String getAuthor() {
        return author;
    }
}
