package com.example.duklin.lab2_moviesearch.omdb_api;

import com.example.duklin.lab2_moviesearch.model.Movie;
import com.example.duklin.lab2_moviesearch.model.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OmdbApiCalls {
    @GET("/?apikey=430420dd")
    Call<MovieList> getSearchResults(@Query("s") String searchString);

    @GET("/?apikey=430420dd")
    Call<Movie> getMovieDetails(@Query("i") String movieId);
}
