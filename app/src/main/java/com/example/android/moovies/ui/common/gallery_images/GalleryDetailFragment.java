package com.example.android.moovies.ui.common.gallery_images;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.moovies.R;
import com.example.android.moovies.domain.models.mtv.Images;
import com.example.android.moovies.utils.StringFormating;

public class GalleryDetailFragment extends Fragment {

    int position;
    Images images;

    public static GalleryDetailFragment newInstance(Images images, int position) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("images", images);
        bundle.putInt("position", position);

        GalleryDetailFragment fragment = new GalleryDetailFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            images = (Images) bundle.getSerializable("images");
            position = bundle.getInt("position");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_gallery_detail, container, false);

        readBundle(getArguments());

        GalleryDetailAdapter galleryImagesAdapter = new GalleryDetailAdapter(getActivity(), StringFormating.getImageListtoString(images));

        ViewPager viewPager = (ViewPager) mView.findViewById(R.id.pager);
        viewPager.setAdapter(galleryImagesAdapter);
        viewPager.setCurrentItem(position);

        return mView;
    }

}
