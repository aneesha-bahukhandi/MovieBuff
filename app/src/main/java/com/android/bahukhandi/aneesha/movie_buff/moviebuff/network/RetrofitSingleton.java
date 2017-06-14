package com.android.bahukhandi.aneesha.movie_buff.moviebuff.network;

import android.util.Log;

import com.android.bahukhandi.aneesha.movie_buff.moviebuff.interfaces.OnMovieDetailsReceived;
import com.android.bahukhandi.aneesha.movie_buff.moviebuff.interfaces.OnMoviesListReceived;
import com.android.bahukhandi.aneesha.movie_buff.moviebuff.models.FullMovie;
import com.android.bahukhandi.aneesha.movie_buff.moviebuff.models.Movie;
import com.android.bahukhandi.aneesha.movie_buff.moviebuff.models.MoviesList;
import com.android.bahukhandi.aneesha.movie_buff.moviebuff.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Aneesha Bahukhandi on 23-09-2016
 */
public class RetrofitSingleton {

    private static RetrofitSingleton sRetrofitSingleton;
    private static Retrofit sRetrofit;
    private static ApiEndPoints sDataEndPoint;

    private static final String apiKey = "89d9808ca7ecafff1698c0a8892530f6";
    private static final String lang = "en-US";

    private RetrofitSingleton(){
    }

    public static RetrofitSingleton getInstance(){
        if (sRetrofitSingleton == null){
            sRetrofitSingleton = new RetrofitSingleton();
        }
        return sRetrofitSingleton;
    }


    public void setUpRetrofit(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        String baseUrl = Constants.BASE_URL + "/";
        sRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        sDataEndPoint = sRetrofit.create(ApiEndPoints.class);
    }

    public void makeAsyncCallToGetMovies(final OnMoviesListReceived listener, String title) {

        final Call<MoviesList> moviesList  = sDataEndPoint.searchMoviesByTitle(title, apiKey, 1, false, lang);
        moviesList.enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                Log.e("url :: ", call.request().url().toString());
                List<Movie> movieList = response.body().results;
                if (movieList == null){
                    listener.onFailure("No Results!");
                }else {
                    Log.e("Response", movieList.size() + "");
                    listener.onMoviesListReceived(movieList);
                }
            }

            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {
                Log.e("url :: ", call.request().url().toString());
                Log.e("Response", t.getMessage());
                listener.onFailure(t.getMessage() + "");
            }
        });
    }

    public void makeAsyncCallToGetMovie(final OnMovieDetailsReceived listener, int id){
        final Call<FullMovie> moviesList  = sDataEndPoint.searchMovieById(id, apiKey, lang);
        moviesList.enqueue(new Callback<FullMovie>() {
            @Override
            public void onResponse(Call<FullMovie> call, Response<FullMovie> response) {
                Log.e("url :: ", call.request().url().toString());
                Log.e("Response", response.body() +"");
                listener.onMovieDetailsReceived(response.body());
            }

            @Override
            public void onFailure(Call<FullMovie> call, Throwable t) {
                Log.e("url :: ", call.request().url().toString());
                Log.e("Response", t.getMessage());
                listener.onFailure(t.getMessage() + "");
            }
        });
    }

}