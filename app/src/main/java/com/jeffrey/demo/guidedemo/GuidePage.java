package com.jeffrey.demo.guidedemo;

/**
 * Created by tianxiying on 2017/11/12.
 */

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;

public class GuidePage extends FrameLayout {
    private ViewPager mPager;
    private ArrayList<View> mPageViews;
    private PagerAdapter mPageAdapter;

    private ViewPager mFramePager;
    private ArrayList<View> mFramePageViews;
    private MyPagerAdapter mFramePageAdapter;

    private View view;

    private ImageView start_img;


    public GuidePage(@NonNull Context context) {
        this(context, null);
    }

    public GuidePage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public GuidePage(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(OnClickListener onClickListener) {
        LayoutInflater.from(getContext()).inflate(R.layout.guide_page_layout, this);
        initViewPager(onClickListener);
    }

    private void initViewPager(OnClickListener onClickListener) {
        mPager = (ViewPager) findViewById(R.id.view_pager);
        mFramePager = (ViewPager) findViewById(R.id.bg_view_pager);

        mPageViews = new ArrayList<View>();
        mFramePageViews = new ArrayList<View>();

        view = findViewById(R.id.progress_mark_view);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View frameView1 = inflater.inflate(R.layout.guide_layer, null);
        initFramePagerView(frameView1, R.drawable.guide1_02, 1);
        View frameView2 = inflater.inflate(R.layout.guide_layer, null);
        initFramePagerView(frameView2, R.drawable.guide2_02, 1);
        View frameView3 = inflater.inflate(R.layout.guide_layer, null);
        initFramePagerView(frameView3, R.drawable.guide3_02, 1);
        View frameView4 = inflater.inflate(R.layout.guide_layer, null);
        initFramePagerView(frameView4, R.drawable.guide4_02, 1);
        mFramePageViews.add(frameView1);
        mFramePageViews.add(frameView2);
        mFramePageViews.add(frameView3);
        mFramePageViews.add(frameView4);
        mFramePageAdapter = new MyPagerAdapter(mFramePageViews);
        mFramePager.setAdapter(mFramePageAdapter);

        final View view1 = inflater.inflate(R.layout.guide_layer, null);
        initFramePagerView(view1, R.drawable.guide1_01, 0);
        View view2 = inflater.inflate(R.layout.guide_layer, null);
        initFramePagerView(view2, R.drawable.guide2_01, 0);
        View view3 = inflater.inflate(R.layout.guide_layer, null);
        initFramePagerView(view3, R.drawable.guide3_01, 0);
        View view4 = inflater.inflate(R.layout.guide_layer, null);
        initFramePagerView(view4, R.drawable.guide4_01, 0);
        start_img = (ImageView) view4.findViewById(R.id.start_img);
        start_img.setVisibility(View.VISIBLE);
        start_img.setOnClickListener(onClickListener);

        mPageViews.add(view1);
        mPageViews.add(view2);
        mPageViews.add(view3);
        mPageViews.add(view4);
        mPageAdapter = new MyPagerAdapter(mPageViews);
        mPager.setAdapter(mPageAdapter);
        mFramePager.setOffscreenPageLimit(4);
        mPager.setPageTransformer(true, new DepthPageTransformer());
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("xxxxxx", "scrolled: postion" + position + ",positionOffset:" + positionOffset + ",positionOffsetPixels" + positionOffsetPixels + ",width" + view.getWidth());
                //进度条
                view.setTranslationX((view.getWidth() + view.getWidth() / 20f) * (position + positionOffset));
                //设置联动
                float pageOffset = position + positionOffset;
                Log.d("pageOffset", pageOffset + "");
                if (mFramePager != null) {
                    mFramePager.scrollTo((int) (pageOffset * mFramePager.getWidth()), mFramePager.getScrollY());
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * @param frameView
     * @param drawable
     * @param mark      0：文字部分  1：背景图部分
     */
    public void initFramePagerView(View frameView, int drawable, int mark) {
        if (mark == 0) {
            ImageView image = (ImageView) frameView.findViewById(R.id.text_img);
            image.setImageResource(drawable);
        } else if (mark == 1) {
            ImageView image = (ImageView) frameView.findViewById(R.id.bg_img);
            image.setImageResource(drawable);
        }
    }
}
