package com.example.movies;



import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("movie?token=DR3VZ12-QWY4DWX-N5FZEKV-QHSK1CW&field=rating.kp*search=4-8&sortField=votes.kp&sortType=-1&limit=50")
    Single<MoveiResponse> loadMovies(@Query("page") int page);
}
