package com.example.android.moovies.ui.gallery_videos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.moovies.BuildConfig;
import com.example.android.moovies.R;
import com.example.android.moovies.domain.models.mtv.Video;
import com.example.android.moovies.domain.models.mtv.Videos;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryVideosActivity extends YouTubeBaseActivity {

    @BindView(R.id.text_video_title)
    TextView textVideoTitle;

    @BindView(R.id.text_video_content)
    TextView textVideoOverview;

    @BindView(R.id.recycler_view_videos)
    RecyclerView recyclerViewVideos;

    YouTubePlayer mYouTubePlayer;

    private boolean fullScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_videos);

        ButterKnife.bind(this);

        Intent intent = getIntent();

        final Videos videos = (Videos) intent.getExtras().getSerializable("videos");

        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.player);

        youTubePlayerView.initialize(BuildConfig.YOUTUBE_APIKEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Video video = videos.getResults().get(0);

                mYouTubePlayer = youTubePlayer;
                mYouTubePlayer.loadVideo(video.getKey());
                mYouTubePlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
                    @Override
                    public void onFullscreen(boolean b) {
                        fullScreen = b;
                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });

        textVideoTitle.setText(intent.getExtras().getString("title"));
        textVideoOverview.setText(intent.getExtras().getString("overview"));

        if (videos.getResults().size() > 1) {
            GalleryVideosAdapter galleryVideosAdapter = new GalleryVideosAdapter(this, videos.getResults());
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

            recyclerViewVideos.setLayoutManager(layoutManager);
            recyclerViewVideos.setAdapter(galleryVideosAdapter);

            galleryVideosAdapter.setRecyclerViewInterface(new GalleryVideosAdapter.RecyclerViewInterface() {
                @Override
                public void onCardClick(int position) {
                    mYouTubePlayer.loadVideo(videos.getResults().get(position).getKey());
                }
            });
        } else recyclerViewVideos.setVisibility(View.GONE);

    }

    @Override
    public void onBackPressed() {
        if (fullScreen) {
            mYouTubePlayer.setFullscreen(false);
        } else {
            super.onBackPressed();
        }
    }
}
