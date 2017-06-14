package com.android.bahukhandi.aneesha.movie_buff.moviebuff.interfaces;

import com.android.bahukhandi.aneesha.movie_buff.moviebuff.models.Movie;

import java.util.List;

/**
 * Created by Aneesha Bahukhandi on 23-09-2016
 */
public interface OnMoviesListReceived {
    void onMoviesListReceived(List<Movie> moviesList);
    void onFailure(String message);
}
