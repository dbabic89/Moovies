package com.example.android.moovies.ui.gallery_videos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.moovies.R;
import com.example.android.moovies.domain.models.movie.Video;
import com.squareup.picasso.Picasso;

import java.util.List;

class GalleryVideosAdapter extends RecyclerView.Adapter<GalleryVideosAdapter.MovieViewHolder> {

    private List<Video> videos;
    private Context context;
    private GalleryVideosAdapter.RecyclerViewInterface recyclerViewInterface;

    GalleryVideosAdapter(Context context, List<Video> videos) {
        this.context = context;
        this.videos = videos;
    }

    interface RecyclerViewInterface {
        void onCardClick(int position);
    }

    void setRecyclerViewInterface(GalleryVideosAdapter.RecyclerViewInterface recyclerViewInterface) {
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @Override
    public GalleryVideosAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_video, parent, false);
        return new GalleryVideosAdapter.MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GalleryVideosAdapter.MovieViewHolder holder, final int position) {

        Video video = videos.get(position);

        holder.videoTitle.setText(video.getName());
        Picasso.with(context).load("http://img.youtube.com/vi/" +  video.getKey() + "/0.jpg").into(holder.videoImage);

    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView videoImage;
        TextView videoTitle;

        MovieViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            videoImage = (ImageView) v.findViewById(R.id.image_video);
            videoTitle = (TextView) v.findViewById(R.id.text_video_title);
        }

        @Override
        public void onClick(View view) {
            recyclerViewInterface.onCardClick(getAdapterPosition());
        }
    }


}