package com.example.android.moovies.ui.common.gallery_images;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.moovies.R;
import com.example.android.moovies.domain.models.mtv.Image;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.android.moovies.utils.Constants.URL_ICON;

class GalleryGridAdapter extends RecyclerView.Adapter<GalleryGridAdapter.ImageViewHolder> {

    private List<Image> images;
    private Context mContext;
    private RecyclerViewInterface recyclerViewInterface;


    GalleryGridAdapter(Context context, List<Image> images) {
        mContext = context;
        this.images = images;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.icon_picture, parent, false);
        return new ImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Image image = images.get(position);
        Picasso.with(mContext).load(URL_ICON + image.getFilePath()).into(holder.imagePicture);

    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imagePicture;

        ImageViewHolder(View view) {
            super(view);
            imagePicture = (ImageView) view.findViewById(R.id.image_picture);
            imagePicture.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recyclerViewInterface.onCardClick(getAdapterPosition());
        }
    }

    interface RecyclerViewInterface {
        void onCardClick(int position);
    }

    void setRecyclerViewInterface(GalleryGridAdapter.RecyclerViewInterface recyclerViewInterface) {
        this.recyclerViewInterface = recyclerViewInterface;
    }


}