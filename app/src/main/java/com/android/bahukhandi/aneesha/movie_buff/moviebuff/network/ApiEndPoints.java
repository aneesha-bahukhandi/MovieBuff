package com.android.bahukhandi.aneesha.movie_buff.moviebuff.network;

import com.android.bahukhandi.aneesha.movie_buff.moviebuff.models.FullMovie;
import com.android.bahukhandi.aneesha.movie_buff.moviebuff.models.MoviesList;
import com.android.bahukhandi.aneesha.movie_buff.moviebuff.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Aneesha Bahukhandi on 23-09-2016
 */
public interface ApiEndPoints {

    @GET("search/movie")
    Call<MoviesList> searchMoviesByTitle(@Query(Constants.PARAM_QUERY) String title,
                                         @Query(Constants.PARAM_API_KEY) String apiKey,
                                         @Query(Constants.PARAM_PAGE) int page,
                                         @Query(Constants.PARAM_INCLUDE_ADULT_CONTENT) boolean shouldIncludeContent,
                                         @Query(Constants.PARAM_LANGUAGE) String lang);

    @GET("movie/{movie_id}")
    Call<FullMovie> searchMovieById(@Path("movie_id") int movieId,
                                    @Query(Constants.PARAM_API_KEY) String apiKey,
                                    @Query(Constants.PARAM_LANGUAGE) String lang);

}
