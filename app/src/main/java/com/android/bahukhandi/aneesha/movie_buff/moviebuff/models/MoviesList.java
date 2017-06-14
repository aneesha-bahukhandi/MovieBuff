package com.android.bahukhandi.aneesha.movie_buff.moviebuff.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Aneesha Bahukhandi on 23-09-2016
 */
public class MoviesList implements Parcelable{

    /**
     * "page": 1,
     "total_results": 15,
     "total_pages": 1,
     "results": [
     {Movie}
     ]
     */

    public List<Movie> results;
    @SerializedName("total_results")
    public int totalResults;
    @SerializedName("total_pages")
    public int totalPages;
    public int page;

    public static final Creator<MoviesList> CREATOR = new Creator<MoviesList>() {
        @Override
        public MoviesList createFromParcel(Parcel in) {
            return new MoviesList(in);
        }

        @Override
        public MoviesList[] newArray(int size) {
            return new MoviesList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(results);
        dest.writeInt(totalResults);
        dest.writeInt(totalPages);
        dest.writeInt(page);
    }

    private MoviesList(Parcel in){
        in.readTypedList(results, Movie.CREATOR);
        this.totalResults = in.readInt();
        this.totalPages = in.readInt();
        this.page = in.readInt();
    }


}
