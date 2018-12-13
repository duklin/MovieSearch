package com.example.duklin.lab2_moviesearch.async_tasks;

import android.os.AsyncTask;

import com.example.duklin.lab2_moviesearch.model.Movie;
import com.example.duklin.lab2_moviesearch.room.MovieDao;

public class InsertAsyncTask extends AsyncTask<Movie, Void, Void> {

    private MovieDao movieDao;

    public InsertAsyncTask(MovieDao movieDao){
        this.movieDao = movieDao;
    }

    @Override
    protected Void doInBackground(Movie... movies) {
        movieDao.insert(movies[0]);
        return null;
    }
}
