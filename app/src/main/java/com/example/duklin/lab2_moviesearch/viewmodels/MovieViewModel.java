package com.example.duklin.lab2_moviesearch.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.duklin.lab2_moviesearch.async_tasks.DeleteAllAsyncTask;
import com.example.duklin.lab2_moviesearch.async_tasks.InsertAsyncTask;
import com.example.duklin.lab2_moviesearch.model.Movie;
import com.example.duklin.lab2_moviesearch.room.MovieDao;
import com.example.duklin.lab2_moviesearch.room.MovieDatabase;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {

    private MovieDao movieDao;
    private LiveData<List<Movie>> movies;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDatabase.getMovieDatabase(application.getApplicationContext()).movieDao();
        movies = movieDao.findAll();
    }

    public void insert(Movie movie) {
        new InsertAsyncTask(movieDao).execute(movie);
    }

    public void deleteAll() {
        new DeleteAllAsyncTask(movieDao).execute();
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

}
