package com.example.android.moovies.ui.common.gallery_images;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.android.moovies.R;
import com.squareup.picasso.Picasso;

import java.util.List;


class GalleryDetailAdapter extends PagerAdapter {

    private Context context;
    private List<String> imagePaths;

    GalleryDetailAdapter(Context context, List<String> imagePaths) {
        this.context = context;
        this.imagePaths = imagePaths;
    }

    @Override
    public int getCount() {
        return this.imagePaths.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.view_fullscreen_image, container, false);

        TouchImageView image_full = (TouchImageView) viewLayout.findViewById(R.id.image_full);

        Picasso.with(context).load("https://image.tmdb.org/t/p/w780/" + imagePaths.get(position)).into(image_full);

        (container).addView(viewLayout);

        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        (container).removeView((RelativeLayout) object);

    }
}