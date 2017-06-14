package com.android.bahukhandi.aneesha.movie_buff.moviebuff.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


/**
 * Created by Aneesha Bahukhandi on 23-09-2016.
 */
public class Movie implements Parcelable {

    /**
     * "vote_count": 3,
     "id": 235027,
     "video": false,
     "vote_average": 8,
     "title": "Tweety and the Beanstalk",
     "popularity": 1.014832,
     "poster_path": "/dQg0yf9mH0Sw6MA9nSraBByBJN2.jpg",
     "original_language": "en",
     "original_title": "Tweety and the Beanstalk",
     "genre_ids": [
     16,
     35
     ],
     "backdrop_path": null,
     "adult": false,
     "overview": "Jack's mother throws Jack's magic beans outside under Sylvester Cat's sleeping box, and the cat is whisked to the world above, where he finds a huge Tweety Bird in the castle of the legendary Giant.",
     "release_date": "1957-05-15"
     */

    public int id;
    public String title;
    @SerializedName("original_title")
    public String originalTitle;
    @SerializedName("poster_path")
    public String posterSrc;
    @SerializedName("release_date")
    public String releaseDate;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(originalTitle);
        dest.writeString(posterSrc);
        dest.writeString(releaseDate);
    }

    private Movie(Parcel in){
        id = in.readInt();
        title = in.readString();
        originalTitle = in.readString();
        posterSrc = in.readString();
        releaseDate = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
