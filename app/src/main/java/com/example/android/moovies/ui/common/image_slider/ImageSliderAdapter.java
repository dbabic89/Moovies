package com.example.android.moovies.ui.common.image_slider;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Collections;
import java.util.List;


public class ImageSliderAdapter  extends FragmentPagerAdapter {

    private List<String> images = Collections.EMPTY_LIST;

    public ImageSliderAdapter(FragmentManager fm, List<String> images) {
        super(fm);
        this.images = images;
    }

    @Override
    public Fragment getItem(int position) {
        return ImageSliderFragment.newInstance(images.get(position));
    }

    @Override
    public int getCount() {
        return images.size();
    }

}