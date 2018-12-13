package com.example.duklin.lab2_moviesearch.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.duklin.lab2_moviesearch.async_tasks.UpdateAsyncTask;
import com.example.duklin.lab2_moviesearch.model.Movie;
import com.example.duklin.lab2_moviesearch.room.MovieDao;
import com.example.duklin.lab2_moviesearch.room.MovieDatabase;

public class MovieDetailsViewModel extends AndroidViewModel {

    private MovieDao movieDao;
    private LiveData<Movie> movie;

    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDatabase.getMovieDatabase(application.getApplicationContext()).movieDao();
    }

    public void setMovie(String movieId){
        movie = movieDao.findById(movieId);
    }

    public void update(Movie movie){
        new UpdateAsyncTask(movieDao).execute(movie);
    }

    public LiveData<Movie> getMovie(){
        return movie;
    }

}
