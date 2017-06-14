package com.android.bahukhandi.aneesha.movie_buff.moviebuff.utils;

import android.content.Context;
import android.util.DisplayMetrics;


/**
 * Created by Aneesha Bahukhandi on 10-08-2016

 */
public class Utility {

    public static int convertDpToPixels(int dp, Context context){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int)((dp * displayMetrics.density) + 0.5);
    }

}
