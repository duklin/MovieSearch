package com.example.duklin.lab2_moviesearch.ui.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.duklin.lab2_moviesearch.R;
import com.example.duklin.lab2_moviesearch.model.Movie;
import com.example.duklin.lab2_moviesearch.model.MovieList;
import com.example.duklin.lab2_moviesearch.omdb_api.OmdbApiCalls;
import com.example.duklin.lab2_moviesearch.omdb_api.OmdbApiClient;
import com.example.duklin.lab2_moviesearch.ui.adapters.MovieAdapter;
import com.example.duklin.lab2_moviesearch.viewmodels.MovieViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private OmdbApiCalls omdbApiCalls;
    private MovieViewModel movieViewModel;
    private RecyclerView movieRecyclerView;
    private SearchView searchView;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MovieAdapter movieAdapter = new MovieAdapter(this);
        movieRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        movieRecyclerView.setAdapter(movieAdapter);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        omdbApiCalls = OmdbApiClient.getRetrofit().create(OmdbApiCalls.class);
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        movieViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                if (movies != null) {
                    movieAdapter.setMovies(movies);
                }
            }
        });

    }

    private void syncData(String searchQuery) {
        omdbApiCalls.getSearchResults(searchQuery).enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                movieViewModel.deleteAll();
                if (response.body().getList() != null) {
                    for (Movie movie : response.body().getList()) {
                        movieViewModel.insert(movie);
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        // Get the SearchView and set the searchable configuration
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                syncData(s.trim());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }
}
