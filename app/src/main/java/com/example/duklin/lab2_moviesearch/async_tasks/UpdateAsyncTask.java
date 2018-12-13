package com.example.duklin.lab2_moviesearch.async_tasks;

import android.os.AsyncTask;

import com.example.duklin.lab2_moviesearch.model.Movie;
import com.example.duklin.lab2_moviesearch.room.MovieDao;

public class UpdateAsyncTask extends AsyncTask<Movie, Void, Void> {

    private MovieDao movieDao;

    public UpdateAsyncTask(MovieDao movieDao){
        this.movieDao = movieDao;
    }

    @Override
    protected Void doInBackground(Movie... movies) {
        movieDao.update(movies[0]);
        return null;
    }
}
