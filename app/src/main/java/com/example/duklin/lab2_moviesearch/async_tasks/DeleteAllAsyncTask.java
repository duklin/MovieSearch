package com.example.duklin.lab2_moviesearch.async_tasks;

import android.os.AsyncTask;

import com.example.duklin.lab2_moviesearch.room.MovieDao;

public class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
    private MovieDao movieDao;

    public DeleteAllAsyncTask(MovieDao movieDao){
        this.movieDao = movieDao;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        movieDao.deleteAll();
        return null;
    }
}
