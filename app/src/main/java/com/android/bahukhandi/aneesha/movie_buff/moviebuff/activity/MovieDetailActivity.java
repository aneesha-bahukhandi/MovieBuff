package com.android.bahukhandi.aneesha.movie_buff.moviebuff.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.bahukhandi.aneesha.movie_buff.moviebuff.R;
import com.android.bahukhandi.aneesha.movie_buff.moviebuff.interfaces.OnMovieDetailsReceived;
import com.android.bahukhandi.aneesha.movie_buff.moviebuff.models.FullMovie;
import com.android.bahukhandi.aneesha.movie_buff.moviebuff.models.Movie;
import com.android.bahukhandi.aneesha.movie_buff.moviebuff.network.RetrofitSingleton;
import com.android.bahukhandi.aneesha.movie_buff.moviebuff.utils.Constants;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity implements OnMovieDetailsReceived {

    public static final String IMAGE_TRANSITION_NAME = "transitionImage";
    public static final String MOVIE_TITLE = "title";
    public static final String IMDB_ID = "imdb_id";
    public static final String YEAR = "year";
    public static final String DESCRIPTION= "description";
    public static final String MOVIE_ICON = "movie_icon";

    public static final String STATUS = "release_status";
    public static final String GENRE= "genre";
    public static final String RATING_BAR = "rating_bar";

    private Movie mMovie;
    private FullMovie mFullMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mMovie = getIntent().getExtras().getParcelable(Constants.MOVIE);

        ImageView poster = (ImageView) findViewById(R.id.image);
        TextView imdbId = (TextView) findViewById(R.id.imdb_id);
        TextView year = (TextView) findViewById(R.id.imdb_year);
        ImageView icon = (ImageView) findViewById(R.id.movie_icon);
        TextView title = (TextView) findViewById(R.id.title);
        TextView description = (TextView) findViewById(R.id.description_tag);
        TextView status = (TextView)findViewById(R.id.status_tag);
        TextView genre = (TextView)findViewById(R.id.genre_tag);
        RatingBar ratingBar = (RatingBar)findViewById(R.id.rating);

        addPoster(poster);

        title.setText(mMovie.title);

        ViewCompat.setTransitionName(poster, IMAGE_TRANSITION_NAME);
        ViewCompat.setTransitionName(imdbId, IMDB_ID);
        ViewCompat.setTransitionName(year, YEAR);
        ViewCompat.setTransitionName(icon, MOVIE_ICON);
        ViewCompat.setTransitionName(title, MOVIE_TITLE);
        ViewCompat.setTransitionName(description, DESCRIPTION);
        ViewCompat.setTransitionName(status, STATUS);
        ViewCompat.setTransitionName(genre, GENRE);
        ViewCompat.setTransitionName(ratingBar, RATING_BAR);

        RetrofitSingleton singleton = RetrofitSingleton.getInstance();
        singleton.setUpRetrofit();
        singleton.makeAsyncCallToGetMovie(this, mMovie.id);

    }

    private void addPoster(ImageView posterView){
        String poster = mMovie.posterSrc;
        if (!TextUtils.isEmpty(poster)) {
            poster = Constants.BASE_URL + poster;
            Picasso.with(this).load(poster)
                    .placeholder(ContextCompat.getDrawable(this, R.drawable.ic_film))
                    .error(ContextCompat.getDrawable(this, R.drawable.ic_film))
                    .into(posterView);
        }else{
            Picasso.with(this).load(R.drawable.ic_film)
                    .placeholder(ContextCompat.getDrawable(this, R.drawable.ic_film))
                    .error(ContextCompat.getDrawable(this, R.drawable.ic_film))
                    .into(posterView);
        }
    }


    @Override
    public void onMovieDetailsReceived(FullMovie movieDetails) {
        setUpView(movieDetails);
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this,message, Toast.LENGTH_LONG ).show();
    }

    private void setUpView(FullMovie fullMovie){
        this. mFullMovie = fullMovie;
        ((TextView) findViewById(R.id.imdb_id)).setText(adjustString(mFullMovie.imdbId));
        ((TextView) findViewById(R.id.imdb_year)).setText(adjustString(mFullMovie.releaseDate));
        ((TextView) findViewById(R.id.status)).setText(adjustString(mFullMovie.status));
        ((TextView) findViewById(R.id.genre)).setText(getGenres());
        ((TextView) findViewById(R.id.description)).setText(adjustString(mFullMovie.overview));
        ((RatingBar) findViewById(R.id.rating)).setRating(mFullMovie.popularity * 100);
    }

    private String getGenres(){
        if (this.mFullMovie != null && this.mFullMovie.genres != null && this.mFullMovie.genres.size() > 0){
            String genres = "";
            for (FullMovie.Genre genre : this.mFullMovie.genres){
                genres += genre.name;
                genres += ", ";
            }
            return genres.substring(0, genres.length() - 2);
        } else {
            return "N/A";
        }
    }

    private String adjustString(String s){
        if (TextUtils.isEmpty(s)){
            return "N/A";
        }
        return s;
    }
}
