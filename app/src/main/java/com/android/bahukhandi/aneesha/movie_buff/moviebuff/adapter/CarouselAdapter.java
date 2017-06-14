package com.android.bahukhandi.aneesha.movie_buff.moviebuff.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.android.bahukhandi.aneesha.movie_buff.moviebuff.fragment.MovieSmall;
import com.android.bahukhandi.aneesha.movie_buff.moviebuff.models.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aneesha Bahukhandi on 23-09-2016
 */
public class CarouselAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private ArrayList<Movie> mMoviesList;

    public CarouselAdapter(Context context, FragmentManager fm, ArrayList<Movie> moviesList) {
        super(fm);
        this.context = context;
        this.mMoviesList = new ArrayList<>();
        if (moviesList != null && !moviesList.isEmpty()){
            mMoviesList.addAll(moviesList);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return MovieSmall.newInstance(context, mMoviesList.get(position));
    }

    @Override
    public int getCount() {
        return mMoviesList.size();
    }

    public void notifyMovieDataChanged(List<Movie> newMovies) {
        mMoviesList.clear();
        mMoviesList.addAll(newMovies);
        notifyDataSetChanged();
    }


}
