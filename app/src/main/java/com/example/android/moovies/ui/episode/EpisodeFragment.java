package com.example.android.moovies.ui.episode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.moovies.Moovies;
import com.example.android.moovies.R;
import com.example.android.moovies.data.local.SharedPreferencesManager;
import com.example.android.moovies.di.component.DaggerMovieComponent;
import com.example.android.moovies.di.component.MovieComponent;
import com.example.android.moovies.di.module.ActivityModule;
import com.example.android.moovies.domain.models.account.EpisodeRating;
import com.example.android.moovies.domain.models.account.Rated;
import com.example.android.moovies.domain.models.mtv.Credits;
import com.example.android.moovies.domain.models.mtv.Images;
import com.example.android.moovies.domain.models.mtv.Video;
import com.example.android.moovies.domain.models.mtv.Videos;
import com.example.android.moovies.domain.models.tv.Episode;
import com.example.android.moovies.ui.common.gallery_videos.GalleryVideosActivity;
import com.example.android.moovies.utils.Constants;
import com.example.android.moovies.utils.FragmentCommunication;
import com.example.android.moovies.utils.RatingDialog;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EpisodeFragment extends Fragment implements EpisodeMvpView, View.OnClickListener {

    @BindView(R.id.button_see_more)
    Button button;

    @BindView(R.id.text_episode_overview)
    TextView episodeOverview;

    @BindView(R.id.text_episode_name)
    TextView episodeName;

    @BindView(R.id.text_season_name)
    TextView textSeasonName;

    @BindView(R.id.text_episode_vote_rating)
    TextView textEpisodeRating;

    @BindView(R.id.text_episode_vote_count)
    TextView textEpisodeCount;

    @BindView(R.id.text_rating)
    TextView textRating;

    @BindView(R.id.image_poster)
    ImageView imagePoster;

    @BindView(R.id.image_button_rating)
    ImageView imageUserRating;

    @BindView(R.id.view_mtv_images_n_videos)
    LinearLayout linearLayoutImagesVideos;

    @BindView(R.id.relative_layout_guests)
    RelativeLayout relativeLayoutGuests;

    @Inject
    SharedPreferencesManager sharedPreferencesManager;

    @Inject
    Picasso picasso;

    @Inject
    EpisodePresenter episodePresenter;

    private FragmentCommunication fragmentCommunication;
    View mView;
    private Episode episode;
    private int tvId;
    List<Integer> list;

    public static EpisodeFragment newInstance(int tvId, Episode episode) {

        Bundle bundle = new Bundle();
        bundle.putInt("tv_id", tvId);
        bundle.putSerializable("episode", episode);

        EpisodeFragment fragment = new EpisodeFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            tvId = getArguments().getInt("tv_id");
            episode = (Episode) getArguments().getSerializable("episode");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_episode, container, false);
        ButterKnife.bind(this, mView);
        readBundle(getArguments());

        fragmentCommunication = (FragmentCommunication) getActivity();
        createComponent();
        setPresenter();

        imageUserRating.setOnClickListener(this);
        button.setOnClickListener(this);

        return mView;
    }

    @Override
    public void showPoster(String poster) {
        picasso.load(Constants.URL_STILL_POSTER + poster).into(imagePoster);
    }

    @Override
    public void showSeasonAndEpisodeNum(String num) {
        textSeasonName.setText(num);
    }

    @Override
    public void showEpisodeName(String name) {
        episodeName.setText(name);
    }

    @Override
    public void showEpisodeRating(float rating, int count) {
        textEpisodeRating.setText(rating + "/10");
        textEpisodeCount.setText("Vote count: " + count);
    }

    @Override
    public void showEpisodeOverview(String overview) {
        episodeOverview.setText(overview);
    }

    @Override
    public void showImages(final Images images) {
        LinearLayout linearLayoutImages = (LinearLayout) linearLayoutImagesVideos.findViewById(R.id.linear_layout_images);
        TextView textButtonImages = (TextView) linearLayoutImages.findViewById(R.id.text_button);
        ImageView imageButtonImages = (ImageView) linearLayoutImages.findViewById(R.id.image_button);

        linearLayoutImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentCommunication.startImageGallery(images);
            }
        });

        int imageCount = images.getStills().size();

        textButtonImages.setText("Images (" + imageCount + ")");
        picasso.load(Constants.URL_BACKDROP + images.getStills().get(new Random().nextInt(images.getStills().size())).getFilePath()).into(imageButtonImages);

    }

    @Override
    public void showNoImages() {
        LinearLayout linearLayoutImages = (LinearLayout) linearLayoutImagesVideos.findViewById(R.id.linear_layout_videos);
        ImageView imageButtonImages = (ImageView) linearLayoutImages.findViewById(R.id.image_button);
        picasso.load(R.drawable.red_circle).into(imageButtonImages);
    }

    @Override
    public void showVideos(final Videos videos, final String title, final String overview) {

        LinearLayout linearLayoutVideos = (LinearLayout) linearLayoutImagesVideos.findViewById(R.id.linear_layout_videos);
        TextView textVideos = (TextView) linearLayoutVideos.findViewById(R.id.text_button);
        ImageView imageVideos = (ImageView) linearLayoutVideos.findViewById(R.id.image_button);
        ImageView imagePlayButtonVideos = (ImageView) linearLayoutVideos.findViewById(R.id.play_button);

        imagePlayButtonVideos.setVisibility(View.VISIBLE);
        linearLayoutVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), GalleryVideosActivity.class)
                        .putExtra("videos", videos)
                        .putExtra("title", title)
                        .putExtra("overview", overview);
                startActivity(intent);
            }
        });
        List<Video> videoList = videos.getResults();
        textVideos.setText("Videos (" + videoList.size() + ")");
        picasso.load("http://img.youtube.com/vi/" + videoList.get(new Random().nextInt(videoList.size())).getKey() + "/0.jpg").into(imageVideos);

    }

    @Override
    public void showNoVideos() {

    }

    @Override
    public void showGuestStars() {
        relativeLayoutGuests.setVisibility(View.VISIBLE);
        button.setVisibility(View.VISIBLE);
        button.setText("See all");
    }

    @Override
    public void showRating(int rating) {
        if (rating == 0) {
            textRating.setText("Rate this episode");
            imageUserRating.setBackground(getResources().getDrawable(R.drawable.orange_circle));
        } else {
            textRating.setText(String.valueOf(rating));
            imageUserRating.setBackground(getResources().getDrawable(R.drawable.green_circle));
        }
    }

    private void setPresenter() {
        episodePresenter.attachView(this);
        list = Arrays.asList(tvId, episode.getSeasonNumber(), episode.getEpisodeNumber());
        episodePresenter.getEpisodeDetails(list);
        episodePresenter.getEpisodeAccountStates(list);
    }

    private void createComponent() {
        MovieComponent movieComponent = DaggerMovieComponent.builder()
                .applicationComponent(Moovies.get(getActivity()).getApplicationComponent())
                .activityModule(new ActivityModule(getActivity()))
                .build();

        movieComponent.inject(this);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if (id == R.id.button_see_more) {
            Credits credits = new Credits();
            credits.setCast(episode.getGuestStars());
            fragmentCommunication.startCelebrityList(credits);
        } else if (id == R.id.image_button_rating) {

            if (!sharedPreferencesManager.getSessionId().isEmpty()) {
                createDialog();
            } else {
                Toast.makeText(getActivity(), "Please login", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void createDialog() {
        final RatingDialog ratingDialog = new RatingDialog(getActivity(), textRating, "Rate this episode", "Rate this episode");

        Button b1 = ratingDialog.getPositiveButton();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int rating = ratingDialog.getCurrentRating();
                if (rating < 1) {
                    Toast.makeText(getActivity(), "Please rate this episode", Toast.LENGTH_SHORT).show();
                } else {
                    episodePresenter.addRating(new EpisodeRating(list.get(0), list.get(1), list.get(2), new Rated(rating)));
                    ratingDialog.dismiss();
                }
            }
        });

        Button b2 = ratingDialog.getNegativeButton();
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                episodePresenter.deleteRating(new EpisodeRating(list.get(0), list.get(1), list.get(2), new Rated(0)));
                ratingDialog.dismiss();
            }
        });
    }
}
