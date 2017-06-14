package com.android.bahukhandi.aneesha.movie_buff.moviebuff.page_transformer;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.android.bahukhandi.aneesha.movie_buff.moviebuff.utils.Constants;
import com.android.bahukhandi.aneesha.movie_buff.moviebuff.utils.Utility;

/**
 * Created by Aneesha Bahukhandi on 26-09-2016
 */
public class CustomPageTransformer implements ViewPager.PageTransformer {

    private int maxTranslateOffsetX;
    private ViewPager viewPager;

    public CustomPageTransformer(Context context) {
        this.maxTranslateOffsetX = Utility.convertDpToPixels(Constants.OFFSET_X, context);
    }

    public void transformPage(View view, float position) {
        if (viewPager == null) {
            viewPager = (ViewPager) view.getParent();
        }

        int leftInScreen = view.getLeft() - viewPager.getScrollX();
        int centerXInViewPager = leftInScreen + view.getMeasuredWidth() / 2;
        int offsetX = centerXInViewPager - viewPager.getMeasuredWidth() / 2;
        float offsetRate = (float) offsetX * 0.38f / viewPager.getMeasuredWidth();
        float scaleFactor = 1 - Math.abs(offsetRate);
        if (scaleFactor > 0) {
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
            view.setTranslationX(-maxTranslateOffsetX * offsetRate);
        }
    }
}

