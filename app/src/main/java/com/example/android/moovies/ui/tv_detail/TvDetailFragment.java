package com.example.android.moovies.ui.tv_detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager;
import com.example.android.moovies.Moovies;
import com.example.android.moovies.R;
import com.example.android.moovies.di.component.DaggerMovieComponent;
import com.example.android.moovies.di.component.MovieComponent;
import com.example.android.moovies.di.module.ActivityModule;
import com.example.android.moovies.domain.models.mtv.Backdrop;
import com.example.android.moovies.domain.models.mtv.Cast;
import com.example.android.moovies.domain.models.mtv.Credits;
import com.example.android.moovies.domain.models.mtv.Genre;
import com.example.android.moovies.domain.models.mtv.Video;
import com.example.android.moovies.domain.models.mtv.Videos;
import com.example.android.moovies.domain.models.tv.ContentRatingResult;
import com.example.android.moovies.domain.models.tv.CreatedBy;
import com.example.android.moovies.domain.models.tv.KeywordsResults;
import com.example.android.moovies.ui.common.adapters.ChipsAdapter;
import com.example.android.moovies.ui.common.mtv_list.HorizontalRecyclerView;
import com.example.android.moovies.ui.gallery_videos.GalleryVideosActivity;
import com.example.android.moovies.utils.Constants;
import com.example.android.moovies.utils.FragmentCommunication;
import com.example.android.moovies.utils.StringFormating;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.moovies.R.id.button_see_more;

public class TvDetailFragment extends Fragment implements TvDetailMvpView {

    @Inject
    TvDetailPresenter mPresenter;

    @Inject
    Picasso picasso;

    @BindView(R.id.text_mtv_rating)
    TextView textRating;

    @BindView(R.id.image_mtv_backdrop)
    ImageView imageBackdrop;

    @BindView(R.id.recycler_view_genres)
    RecyclerView recyclerViewGenres;

    @BindView(R.id.text_mtv_title)
    TextView textTitle;

    @BindView(R.id.image_poster)
    ImageView imagePoster;

    @BindView(R.id.text_mtv_overview)
    TextView textOverview;

    @BindView(R.id.image_button)
    ImageView imageButtonImages;

    @BindView(R.id.text_button)
    TextView textButtonImages;

    @BindView(R.id.text_keywords_label)
    TextView textKeywordsLabel;

    @BindView(R.id.recycler_view_keywords)
    RecyclerView recyclerViewKeywords;

    @BindView(R.id.similar)
    FrameLayout frameLayoutSimilar;

    @BindView(R.id.text_full_title)
    TextView textFullTitle;

    @BindView(R.id.text_original_title)
    TextView textOriginalTitle;

    @BindView(R.id.text_vote_rating)
    TextView textVoteRating;

    @BindView(R.id.text_vote_count)
    TextView textVoteCount;

    @BindView(R.id.text_status)
    TextView textStatus;

    @BindView(R.id.text_network)
    TextView textNetwork;

    @BindView(R.id.text_production_companies)
    TextView textProductionCompanies;

    @BindView(R.id.text_production_countries)
    TextView textProductionCountries;

    @BindView(R.id.text_spoken_language)
    TextView textSpokenLanguage;

    @BindView(R.id.text_mtv_content_rating)
    TextView textContentRating;

    @BindView(R.id.text_first_air_date)
    TextView textFirstAirDate;

    @BindView(R.id.text_run_time)
    TextView textRunTime;

    @BindView(R.id.text_created_by_label)
            TextView textcreatedByLabel;

    @BindView(R.id.text_cast_label)
            TextView textCastLabel;

    @BindView(R.id.linear_layout_crew)
    LinearLayout linearLayoutCrew;

    @BindView(R.id.linear_layout_cast)
    LinearLayout linearLayoutCast;

    @BindView(button_see_more)
    Button buttonCast;

    @BindView(R.id.view_mtv_images_n_videos)
            LinearLayout linearLayoutImagesVideos;

    View mView;
    int tvId;
    private LayoutInflater layoutInflater;
    private FragmentCommunication fragmentCommunication;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_tv_detail, container, false);
        tvId = getArguments().getInt("tv_id");

        fragmentCommunication = (FragmentCommunication) getActivity();
        layoutInflater = getActivity().getLayoutInflater();

        ButterKnife.bind(this, mView);
        createComponent();
        setPresenter();

        return mView;
    }

    private void createComponent() {
        MovieComponent movieComponent = DaggerMovieComponent.builder()
                .applicationComponent(Moovies.get(getActivity()).getApplicationComponent())
                .activityModule(new ActivityModule(getActivity()))
                .build();

        movieComponent.inject(this);
    }

    private void setPresenter() {
        mPresenter.attachView(this);
        mPresenter.getTvDetails(tvId);
    }

    @Override
    public void showDetails(String rating, String title, String voteCount) {
        textRating.setText(rating);
        textTitle.setText(title);
        textFullTitle.setText(title);
        textVoteRating.setText(rating);
        textVoteCount.setText(voteCount);
    }

    @Override
    public void showBackdrop(String imageUrl) {
        picasso.load(imageUrl).into(imageBackdrop);
    }

    @Override
    public void showNoBackdrop() {
        picasso.load(R.drawable.red_circle).into(imageBackdrop);
    }

    @Override
    public void showGenres(List<Genre> genreList) {
        createChips(StringFormating.getGenres(genreList), recyclerViewGenres);
    }

    @Override
    public void showPoster(String imageUrl) {
        picasso.load(imageUrl).into(imagePoster);
    }

    @Override
    public void showNoPoster() {
        picasso.load(R.drawable.red_circle).into(imagePoster);
    }

    @Override
    public void showContentRating(List<ContentRatingResult> contentRating) {

        for (int i = 0; i <= contentRating.size() - 1; i++){
            ContentRatingResult contentRatingResult = contentRating.get(i);
            if (contentRatingResult.getIso31661().equals("US")) textContentRating.setText(contentRatingResult.getRating());
        }
    }

    @Override
    public void showReleaseDate(String releaseDate) {
        textFirstAirDate.setText(releaseDate);
    }

    @Override
    public void showDuration(String duration) {
        textRunTime.setText(duration);
    }

    @Override
    public void showOverview(String overview) {
        textOverview.setText(overview);
    }

    @Override
    public void showWatchlist(boolean watchlist) {

    }

    @Override
    public void showRating(int rating) {

    }

    @Override
    public void showList(boolean list) {

    }

    @Override
    public void showImages(List<Backdrop> backdropList) {
        textButtonImages.setText("Images (" + backdropList.size() + ")");
        picasso.load(Constants.URL_BACKDROP + backdropList.get(new Random().nextInt(backdropList.size())).getFilePath()).into(imageButtonImages);

    }

    @Override
    public void showNoImages() {
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
    public void showCreatedBy(List<CreatedBy> crewList) {

        textcreatedByLabel.setVisibility(View.VISIBLE);

        for (int i = 0; i < crewList.size(); i++) {

            final CreatedBy createdBy = crewList.get(i);

            LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(R.layout.list_item_director, null);
            TextView textView = (TextView) linearLayout.findViewById(R.id.text_director);
            textView.setText(createdBy.getName());
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragmentCommunication.startCelebrityDetail(createdBy.getId());
                }
            });
            linearLayoutCrew.addView(linearLayout);
        }
    }

    @Override
    public void showCast(final List<Cast> castList) {

        textCastLabel.setVisibility(View.VISIBLE);
        buttonCast.setVisibility(View.VISIBLE);

        for (int i = 0; i < 3; i++) {
            final Cast cast = castList.get(i);

            RelativeLayout relativeLayout = (RelativeLayout) layoutInflater.inflate(R.layout.icon_person, null);
            TextView textView = (TextView) relativeLayout.findViewById(R.id.text_person);
            ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.image_person);
            textView.setText(cast.getName());
            picasso.load(Constants.URL_POSTER + cast.getProfilePath()).resize(400, 200).into(imageView);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragmentCommunication.startCelebrityDetail(cast.getId());
                }
            });
            linearLayoutCast.addView(relativeLayout);
        }

        buttonCast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Credits credits = new Credits();
                credits.setCast(castList);
                fragmentCommunication.startCelebrityList(credits);
            }
        });
    }

    @Override
    public void showOriginalTitle(String originalTitle) {
        textOriginalTitle.setText(originalTitle);
    }

    @Override
    public void showNetwork(String network) {
        textNetwork.setText(network);
    }

    @Override
    public void showStatus(String status) {
        textStatus.setText(status);
    }

    @Override
    public void showProductionCompanies(String productionCompanies) {
        textProductionCompanies.setText(productionCompanies);
    }

    @Override
    public void showProductionCountries(String productionCountries) {
        textProductionCountries.setText(productionCountries);
    }

    @Override
    public void showSpokenLanguage(String spokenLanguages) {
        textSpokenLanguage.setText(spokenLanguages);
    }

    @Override
    public void showSimilarTvs(final int tvId) {
        FrameLayout frameLayoutSimilar = (FrameLayout) mView.findViewById(R.id.similar);

        FragmentManager fragmentManager = getChildFragmentManager();

        Fragment similar = new HorizontalRecyclerView();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.LIST_ID, 9);
        bundle.putInt("movie_id", tvId);
        similar.setArguments(bundle);
        frameLayoutSimilar.setVisibility(View.VISIBLE);
        fragmentManager.beginTransaction().replace(R.id.similar, similar).commit();

    }

    @Override
    public void showKeywords(List<KeywordsResults> keywordsList) {
        textKeywordsLabel.setVisibility(View.VISIBLE);
        createChips(StringFormating.getKeywordResults(keywordsList), recyclerViewKeywords);
    }

    @Override
    public void showError() {

    }

    private void createChips(List<String> genres, RecyclerView recyclerViewGenres) {
        ChipsLayoutManager chipsLayoutManager = ChipsLayoutManager.newBuilder(getContext())
                .setScrollingEnabled(false)
                .build();

        ChipsAdapter chipsAdapter = new ChipsAdapter();
        chipsAdapter.addAll(genres);
        recyclerViewGenres.setVisibility(View.VISIBLE);
        recyclerViewGenres.setLayoutManager(chipsLayoutManager);
        recyclerViewGenres.setAdapter(chipsAdapter);
    }

}
