package com.android.bahukhandi.aneesha.movie_buff.moviebuff.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Aneesha Bahukhandi on 27-09-2016.
 */
public class FullMovie  implements Parcelable{
    /**
     *
     "adult": false,
     "backdrop_path": null,
     "belongs_to_collection": null,
     "budget": 0,
     "genres": [
     {
     "id": 16,
     "name": "Animation"
     },
     {
     "id": 35,
     "name": "Comedy"
     }
     ],
     "homepage": "",
     "id": 235027,
     "imdb_id": "tt0051119",
     "original_language": "en",
     "original_title": "Tweety and the Beanstalk",
     "overview": "Jack's mother throws Jack's magic beans outside under Sylvester Cat's sleeping box, and the cat is whisked to the world above, where he finds a huge Tweety Bird in the castle of the legendary Giant.",
     "popularity": 0.014832,
     "poster_path": "/dQg0yf9mH0Sw6MA9nSraBByBJN2.jpg",
     "production_companies": [],
     "production_countries": [],
     "release_date": "1957-05-15",
     "revenue": 0,
     "runtime": 7,
     "spoken_languages": [],
     "status": "Released",
     "tagline": "",
     "title": "Tweety and the Beanstalk",
     "video": false,
     "vote_average": 8,
     "vote_count": 3
     */

    public int id;
    public float popularity;
    @SerializedName("imdb_id")
    public String imdbId;
    @SerializedName("original_title")
    public String originalTitle;
    public String title;
    public String tagline;
    public String status;
    @SerializedName("release_date")
    public String releaseDate;
    public String overview;
    @SerializedName("poster_path")
    public String posterSrc;
    public List<Genre> genres;

    public static final Creator<FullMovie> CREATOR = new Creator<FullMovie>() {
        @Override
        public FullMovie createFromParcel(Parcel in) {
            return new FullMovie(in);
        }

        @Override
        public FullMovie[] newArray(int size) {
            return new FullMovie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeFloat(popularity);
        dest.writeString(imdbId);
        dest.writeString(originalTitle);
        dest.writeString(title);
        dest.writeString(tagline);
        dest.writeString(status);
        dest.writeString(releaseDate);
        dest.writeString(overview);
        dest.writeString(posterSrc);
        dest.writeTypedList(genres);
    }

    private FullMovie(Parcel in){
        id = in.readInt();
        popularity = in.readFloat();
        imdbId = in.readString();
        originalTitle = in.readString();
        title = in.readString();
        tagline = in.readString();
        status = in.readString();
        releaseDate = in.readString();
        overview = in.readString();
        posterSrc = in.readString();
        genres = in.createTypedArrayList(Genre.CREATOR);
    }


    public static class Genre implements Parcelable{
        /**
         * "genres": [
         {
         "id": 16,
         "name": "Animation"
         },
         {
         "id": 35,
         "name": "Comedy"
         }
         ],
         */

        public int id;
        public String name;

        public static final Creator<Genre> CREATOR = new Creator<Genre>() {
            @Override
            public Genre createFromParcel(Parcel in) {
                return new Genre(in);
            }

            @Override
            public Genre[] newArray(int size) {
                return new Genre[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(name);
        }

        private Genre(Parcel in){
            this.id = in.readInt();
            this.name = in.readString();
        }


    }
}
