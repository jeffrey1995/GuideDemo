package com.jeffrey.demo.guidedemo;

import android.util.Log;
import android.view.View;

/**
 * Created by tianxiying on 2017/11/12.
 */

public class DepthPageTransformer implements android.support.v4.view.ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);

        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page
            view.setAlpha(1 + position);
            view.setTranslationX(7 * pageWidth / 24 * -position);
//                view.setScaleX(1);
//                view.setScaleY(1);

        } else if (position <= 1) { // (0,1]
            // Fade the page out.
            view.setAlpha(1 - position);

            // Counteract the default slide transition
            Log.d("ty", "position:" + position + "Traslation" + pageWidth * position);

            if (position >= 0.5) {
                view.setTranslationX(pageWidth / 2);
            } else {
                view.setTranslationX(pageWidth * position);
            }
            //Scale the page down (between MIN_SCALE and 1) UI缩放的设置
//                float scaleFactor = MIN_SCALE
//                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
//                view.setScaleX(scaleFactor);
//                view.setScaleY(scaleFactor);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }
    }
}
