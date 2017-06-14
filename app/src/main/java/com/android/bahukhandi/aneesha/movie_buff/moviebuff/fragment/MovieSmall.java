package com.android.bahukhandi.aneesha.movie_buff.moviebuff.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.bahukhandi.aneesha.movie_buff.moviebuff.R;
import com.android.bahukhandi.aneesha.movie_buff.moviebuff.activity.MovieDetailActivity;
import com.android.bahukhandi.aneesha.movie_buff.moviebuff.activity.MovieList;
import com.android.bahukhandi.aneesha.movie_buff.moviebuff.models.Movie;
import com.android.bahukhandi.aneesha.movie_buff.moviebuff.utils.Constants;
import com.android.bahukhandi.aneesha.movie_buff.moviebuff.views.DragLayout;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MovieSmall#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieSmall extends Fragment implements DragLayout.GotoDetailListener{

    private Context mContext;
    private View mView;

    public MovieSmall(){

    }

    public static Fragment newInstance(Context context, Movie movie) {
        Bundle b = new Bundle();
        b.putParcelable(Constants.MOVIE, movie);
        return Fragment.instantiate(context, MovieSmall.class.getName(), b);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        View view = inflater.inflate(R.layout.fragment_movie_small, container, false);
        mView = view;
        populateView((Movie) this.getArguments().get(Constants.MOVIE));
        return view;
    }

    private void populateView( Movie movie){
        String poster = movie.posterSrc;
        if (!TextUtils.isEmpty(poster)) {
            poster = Constants.BASE_URL + poster;
            Picasso.with(mContext).load(poster)
                    .placeholder(ContextCompat.getDrawable(mContext, R.drawable.ic_film))
                    .error(ContextCompat.getDrawable(mContext, R.drawable.ic_film))
                    .into((ImageView) mView.findViewById(R.id.movie_poster));
        }else{
            Picasso.with(mContext).load(R.drawable.ic_film)
                    .placeholder(ContextCompat.getDrawable(mContext, R.drawable.ic_film))
                    .error(ContextCompat.getDrawable(mContext, R.drawable.ic_film))
                    .into((ImageView) mView.findViewById(R.id.movie_poster));
        }
        ((TextView)mView.findViewById(R.id.movie_title)).setText(movie.title);
        ((TextView)mView.findViewById(R.id.imdb_id)).setText(String.valueOf(movie.id));
        ((TextView)mView.findViewById(R.id.imdb_year)).setText(movie.releaseDate);
        ((DragLayout)mView.findViewById(R.id.drag_layout)).setGotoDetailListener(this);
    }

    @Override
    public void gotoDetail() {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((MovieList)mContext,
                new Pair<>(mView.findViewById(R.id.movie_poster), MovieDetailActivity.IMAGE_TRANSITION_NAME),
                new Pair<>(mView.findViewById(R.id.movie_title), MovieDetailActivity.MOVIE_TITLE),
                new Pair<>(mView.findViewById(R.id.imdb_id), MovieDetailActivity.IMDB_ID),
                new Pair<>(mView.findViewById(R.id.imdb_year), MovieDetailActivity.YEAR),
                new Pair<>(mView.findViewById(R.id.movie_icon), MovieDetailActivity.MOVIE_ICON)
        );

        Intent intent = new Intent(mContext, MovieDetailActivity.class);
        intent.putExtra(Constants.MOVIE, (Parcelable) this.getArguments().get(Constants.MOVIE));
        ActivityCompat.startActivity(mContext, intent, options.toBundle());
    }
}
