package com.android.bahukhandi.aneesha.movie_buff.moviebuff.interfaces;

import com.android.bahukhandi.aneesha.movie_buff.moviebuff.models.FullMovie;

/**
 * Created by Aneesha Bahukhandi on 27-09-2016.
 */
public interface OnMovieDetailsReceived {

    void onMovieDetailsReceived(FullMovie movieDetails);
    void onFailure(String message);
}
