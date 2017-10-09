package com.example.android.moovies.ui.common.gallery_images;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.moovies.R;
import com.example.android.moovies.domain.models.mtv.Images;
import com.example.android.moovies.utils.FragmentCommunication;
import com.example.android.moovies.utils.StringFormating;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryGridFragment extends Fragment {

    @BindView(R.id.recycler_view_images)
    RecyclerView recyclerViewImages;

    FragmentCommunication fragmentCommunication;
    View mView;
    Images images;

    public static GalleryGridFragment newInstance(Images images) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("images", images);

        GalleryGridFragment fragment = new GalleryGridFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            images = (Images) bundle.getSerializable("images");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_gallery_images, container, false);

        readBundle(getArguments());

        fragmentCommunication = (FragmentCommunication) getActivity();
        ButterKnife.bind(this, mView);

        GalleryGridAdapter galleryImageAdapter = new GalleryGridAdapter(getActivity(), StringFormating.getImageList(images));
        galleryImageAdapter.setRecyclerViewInterface(new GalleryGridAdapter.RecyclerViewInterface() {
            @Override
            public void onCardClick(int position) {
                fragmentCommunication.startImageDetail(images, position);
            }
        });
        recyclerViewImages.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerViewImages.setAdapter(galleryImageAdapter);

        return mView;
    }

}
