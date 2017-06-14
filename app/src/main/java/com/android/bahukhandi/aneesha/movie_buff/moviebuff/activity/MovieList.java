package com.android.bahukhandi.aneesha.movie_buff.moviebuff.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.bahukhandi.aneesha.movie_buff.moviebuff.R;
import com.android.bahukhandi.aneesha.movie_buff.moviebuff.adapter.CarouselAdapter;
import com.android.bahukhandi.aneesha.movie_buff.moviebuff.interfaces.OnMoviesListReceived;
import com.android.bahukhandi.aneesha.movie_buff.moviebuff.models.Movie;
import com.android.bahukhandi.aneesha.movie_buff.moviebuff.network.RetrofitSingleton;
import com.android.bahukhandi.aneesha.movie_buff.moviebuff.page_transformer.CustomPageTransformer;

import java.util.List;

public class MovieList extends AppCompatActivity implements View.OnClickListener, OnMoviesListReceived {

    private EditText mMovieTitle;
    private CarouselAdapter mAdapter;
    private ViewPager mMovieCarousel;
    private ProgressBar mProgressBar;
    private Button mSearchBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        mMovieTitle = (EditText)findViewById(R.id.movie_title);
        mMovieTitle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    mMovieCarousel.setVisibility(View.GONE);
                }
                return false;
            }
        });

        mSearchBtn = (Button)(findViewById(R.id.search_movies));
        mSearchBtn.setOnClickListener(this);

        mMovieCarousel = (ViewPager)findViewById(R.id.movie_carousel);
        mMovieCarousel.setPageTransformer(false, new CustomPageTransformer(this));
        mAdapter = new CarouselAdapter(this, this.getSupportFragmentManager(), null);
        mMovieCarousel.setAdapter(mAdapter);

        mProgressBar = (ProgressBar)findViewById(R.id.progress_bar);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.search_movies) {
            String title = mMovieTitle.getText().toString();
            if (title.isEmpty() || title.equals(getString(R.string.enter_movie_genre))) {
                Snackbar.make(findViewById(R.id.coordinator), getString(R.string.no_genre), Snackbar.LENGTH_LONG)
                        .show();
            } else {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if(imm.isAcceptingText()) {
                    if (getCurrentFocus() != null)
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    (findViewById(R.id.movie_title)).clearFocus();
                }
                makeSearchCallForMovies(title);
                showProgressBar();
            }
        }
    }

    @Override
    public void onMoviesListReceived(List<Movie> moviesList) {
        //notify adapter that data has come in
        mAdapter.notifyMovieDataChanged(moviesList);
        mMovieCarousel.setVisibility(View.VISIBLE);
        dismissProgressBar();
    }

    @Override
    public void onFailure(String message) {
        Snackbar.make(findViewById(R.id.coordinator), message, Snackbar.LENGTH_LONG)
                .show();
        dismissProgressBar();
    }

    private void makeSearchCallForMovies(String genre){
        RetrofitSingleton singleton = RetrofitSingleton.getInstance();
        singleton.setUpRetrofit();
        singleton.makeAsyncCallToGetMovies(this, genre);
    }

    private void showProgressBar(){
        mProgressBar.setVisibility(View.VISIBLE);
        mMovieTitle.setEnabled(false);
        mSearchBtn.setEnabled(false);
    }

    private void dismissProgressBar(){
        mProgressBar.setVisibility(View.GONE);
        mMovieTitle.setEnabled(true);
        mSearchBtn.setEnabled(true);
    }

}
