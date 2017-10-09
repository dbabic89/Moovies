package com.example.android.moovies.ui.movie_detail;

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
import android.widget.Toast;

import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager;
import com.example.android.moovies.Moovies;
import com.example.android.moovies.R;
import com.example.android.moovies.data.local.SharedPreferencesManager;
import com.example.android.moovies.di.component.DaggerMovieComponent;
import com.example.android.moovies.di.component.MovieComponent;
import com.example.android.moovies.di.module.ActivityModule;
import com.example.android.moovies.domain.models.movie.CollectionDetail;
import com.example.android.moovies.domain.models.movie.Keyword;
import com.example.android.moovies.domain.models.movie.Reviews;
import com.example.android.moovies.domain.models.mtv.Cast;
import com.example.android.moovies.domain.models.mtv.Credits;
import com.example.android.moovies.domain.models.mtv.Crew;
import com.example.android.moovies.domain.models.mtv.Genre;
import com.example.android.moovies.domain.models.mtv.Images;
import com.example.android.moovies.domain.models.mtv.Video;
import com.example.android.moovies.domain.models.mtv.Videos;
import com.example.android.moovies.ui.common.adapters.ChipsAdapter;
import com.example.android.moovies.ui.common.gallery_videos.GalleryVideosActivity;
import com.example.android.moovies.ui.common.mtv_list.HorizontalRecyclerView;
import com.example.android.moovies.utils.Constants;
import com.example.android.moovies.utils.FragmentCommunication;
import com.example.android.moovies.utils.RatingDialog;
import com.example.android.moovies.utils.StringFormating;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.moovies.R.id.button_see_more;

public class MovieDetailFragment extends Fragment implements MovieDetailMvpView, View.OnClickListener {

    View mView;

    @BindView(R.id.text_mtv_rating)
    TextView textMtvRating;

    @BindView(R.id.image_mtv_backdrop)
    ImageView imageBackdrop;

    @BindView(R.id.recycler_view_genres)
    RecyclerView recyclerViewGenres;

    @BindView(R.id.text_original_title_label)
    TextView textOriginalTitleLabel;

    @BindView(R.id.text_mtv_title)
    TextView textTitle;

    @BindView(R.id.text_full_title)
    TextView textFullTitle;

    @BindView(R.id.text_mtv_tagline)
    TextView textTagline;

    @BindView(R.id.image_poster)
    ImageView imagePoster;

    @BindView(R.id.text_mtv_overview)
    TextView textOverview;

    @BindView(R.id.text_keywords_label)
    TextView textKeywordsLabel;

    @BindView(R.id.recycler_view_keywords)
    RecyclerView recyclerViewKeywords;

    @BindView(R.id.similar)
    FrameLayout frameLayoutSimilar;

    @BindView(R.id.text_original_title)
    TextView textOriginalTitle;

    @BindView(R.id.text_vote_rating)
    TextView textVoteRating;

    @BindView(R.id.text_vote_count)
    TextView textVoteCount;

    @BindView(R.id.text_production_companies)
    TextView textProductionCompanies;

    @BindView(R.id.text_production_countries)
    TextView textProductionCountries;

    @BindView(R.id.text_spoken_language)
    TextView textSpokenLanguage;

    @BindView(R.id.text_release_date)
    TextView textReleaseDate;

    @BindView(R.id.text_duration)
    TextView textDuration;

    @BindView(R.id.text_budget)
    TextView textBudget;

    @BindView(R.id.text_revenue)
    TextView textRevenue;

    @BindView(R.id.text_created_by_label)
    TextView textCreatedByLabel;

    @BindView(R.id.text_cast_label)
    TextView textCastLabel;

    @BindView(R.id.linear_layout_crew)
    LinearLayout linearLayoutCrew;

    @BindView(R.id.linear_layout_cast)
    LinearLayout linearLayoutCast;

    @BindView(button_see_more)
    Button buttonCast;

    @BindView(R.id.text_reviews_label)
    TextView textReviewLabel;

    @BindView(R.id.linear_layout_reviews)
    LinearLayout linearLayoutReviews;

    @BindView(R.id.text_collection_name)
    TextView textCollectionName;

    @BindView(R.id.button_check_collection)
    Button buttonCheckCollection;

    @BindView(R.id.relative_layout_collection)
    RelativeLayout relativeLayoutCollection;

    @BindView(R.id.text_belongs_to_collection_label)
    TextView textBelongsToCollection;

    @BindView(R.id.image_collection_backdrop)
    ImageView imageCollection;

    @BindView(R.id.text_certification)
    TextView textCertification;

    @BindView(R.id.image_button_watchlist)
    ImageView imageButtonWatchlist;

    @BindView(R.id.image_button_rating)
    ImageView imageButtonRating;

    @BindView(R.id.text_watchlist)
    TextView textWatchlist;

    @BindView(R.id.text_rating)
    TextView textUserRating;

    @BindView(R.id.view_mtv_images_n_videos)
    LinearLayout linearLayoutImagesVideos;

    @Inject
    Picasso picasso;
    @Inject
    MovieDetailPresenter mPresenter;
    @Inject
    SharedPreferencesManager sharedPreferencesManager;

    FragmentCommunication fragmentCommunication;
    LayoutInflater layoutInflater;

    int movieId = 0;

    public static MovieDetailFragment newInstance(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("movie_id", id);

        MovieDetailFragment fragment = new MovieDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            movieId = bundle.getInt("movie_id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        readBundle(getArguments());

        fragmentCommunication = (FragmentCommunication) getActivity();
        layoutInflater = getActivity().getLayoutInflater();

        mView = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, mView);
        createComponent();

        setPresenter();

        imageButtonWatchlist.setOnClickListener(this);
        imageButtonRating.setOnClickListener(this);

        return mView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void showMovieDetail(String title, String rating, String voteCount, String status) {

        textMtvRating.setText(rating);
        textTitle.setText(title);
        textFullTitle.setText(title);
        textVoteRating.setText(" " + rating);
        textVoteCount.setText(" " + voteCount);

    }

    @Override
    public void showBackdrop(String imageUrl) {
        picasso.load(imageUrl).into(imageBackdrop);
    }

    @Override
    public void showNoBackdrop() {
        imagePoster.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        picasso.load(R.drawable.red_circle).into(imageBackdrop);
    }

    @Override
    public void showTagline(String tagline) {
        textTagline.setText(tagline);
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
    public void showOverview(String overview) {
        textOverview.setText(overview);
    }

    @Override
    public void showGenres(List<Genre> genreList) {
        createChips(StringFormating.getGenres(genreList), recyclerViewGenres);
    }

    @Override
    public void showCollection(CollectionDetail collectionDetail) {

        final int id = collectionDetail.getId();

        picasso.load(Constants.URL_BACKDROP + collectionDetail.getBackdropPath()).into(imageCollection);
        textCollectionName.setText(collectionDetail.getName());
        relativeLayoutCollection.setVisibility(View.VISIBLE);
        textBelongsToCollection.setVisibility(View.VISIBLE);

        buttonCheckCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentCommunication.startCollectionList(id);
            }
        });
    }

    @Override
    public void showCertification(String certification) {
        textCertification.setText(certification);
    }

    @Override
    public void showNoCertification() {
        textCertification.setText(R.string.not_available);
    }

    @Override
    public void showReleaseDate(String releaseDate) {
        textReleaseDate.setText(StringFormating.dateFormating(releaseDate));
    }

    @Override
    public void showNoReleaseDate() {
        textReleaseDate.setText(R.string.not_available);
    }

    @Override
    public void showDuration(int duration) {
        textDuration.setText(StringFormating.timeFormating(duration));
    }

    @Override
    public void showNoDuration() {
        textDuration.setText(R.string.not_available);
    }

    @Override
    public void showDirectedBy(List<Crew> crewList) {

        textCreatedByLabel.setVisibility(View.VISIBLE);

        for (int i = 0; i < crewList.size(); i++) {

            final Crew crew = crewList.get(i);
            if (crew.getJob().equals("Director")) {

                LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(R.layout.list_item_director, null);
                TextView textView = (TextView) linearLayout.findViewById(R.id.text_director);
                textView.setText(crew.getName());
                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        fragmentCommunication.startCelebrityDetail(crew.getId());
                    }
                });
                linearLayoutCrew.addView(linearLayout);
            }
        }
    }

    @Override
    public void showWatchlist(boolean watchlist) {
        if (watchlist) {
            textWatchlist.setText(R.string.on_watchlist);
            imageButtonWatchlist.setBackground(getResources().getDrawable(R.drawable.green_circle));
        } else textWatchlist.setText(R.string.add_to_watchlist);
    }

    @Override
    public void showRating(int rating) {
        if (rating == 0) {
            textUserRating.setText("Rate this movie");
            imageButtonRating.setBackground(getResources().getDrawable(R.drawable.orange_circle));
        } else {
            textUserRating.setText(String.valueOf(rating));
            imageButtonRating.setBackground(getResources().getDrawable(R.drawable.green_circle));
        }
    }

    @Override
    public void showList(boolean list) {

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

        int imageCount = images.getBackdrops().size() + images.getPosters().size();

        textButtonImages.setText("Images (" + imageCount + ")");
        picasso.load(Constants.URL_BACKDROP + images.getBackdrops().get(new Random().nextInt(images.getBackdrops().size())).getFilePath()).into(imageButtonImages);
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
    public void showReviews(final Reviews reviews) {

        textReviewLabel.setVisibility(View.VISIBLE);
        linearLayoutReviews.setVisibility(View.VISIBLE);
        TextView te = (TextView) linearLayoutReviews.findViewById(R.id.text_user_reviews);
        te.setText("Reviews (" + reviews.getResults().size() + ")");
        linearLayoutReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentCommunication.startReviewList(reviews);
            }
        });
    }

    @Override
    public void showCast(final List<Cast> castList) {

        textCastLabel.setVisibility(View.VISIBLE);
        buttonCast.setVisibility(View.VISIBLE);

        int x;
        if (castList.size() < 3) {
            x = castList.size();
        } else x = 3;

        for (int i = 0; i < x; i++) {
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
        textOriginalTitleLabel.setVisibility(View.VISIBLE);
        textOriginalTitle.setVisibility(View.VISIBLE);
        textOriginalTitle.setText(originalTitle);
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
    public void showBudget(String budget) {
        textBudget.setText(budget + " $");
    }

    @Override
    public void showRevenue(String revenue) {
        textRevenue.setText(revenue + " $");
    }

    @Override
    public void showSimilarMovies(int movieId) {

        FragmentManager fragmentManager = getChildFragmentManager();

        Fragment similar = new HorizontalRecyclerView();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.LIST_ID, 8);
        bundle.putInt("movie_id", movieId);
        similar.setArguments(bundle);
        frameLayoutSimilar.setVisibility(View.VISIBLE);
        fragmentManager.beginTransaction().replace(R.id.similar, similar).commit();

    }

    @Override
    public void showKeywords(List<Keyword> keywordsList) {
        textKeywordsLabel.setVisibility(View.VISIBLE);
        createChips(StringFormating.getKeywords(keywordsList), recyclerViewKeywords);
    }

    @Override
    public void showError() {

    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if (id == R.id.image_button_watchlist) {

            if (!sharedPreferencesManager.getSessionId().isEmpty()) {

                if (textWatchlist.getText().equals("Add to watchlist")) {
                    textWatchlist.setText("On watchlist");
                    imageButtonWatchlist.setBackground(getResources().getDrawable(R.drawable.green_circle));
                    mPresenter.addToWatchlist(movieId, true);
                } else {
                    textWatchlist.setText("Add to watchlist");
                    mPresenter.addToWatchlist(movieId, false);
                    imageButtonWatchlist.setBackground(getResources().getDrawable(R.drawable.orange_circle));
                }
            } else {
                Toast.makeText(getActivity(), "Please login", Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.image_button_rating) {

            if (!sharedPreferencesManager.getSessionId().isEmpty()) {
                createDialog();
            } else {
                Toast.makeText(getActivity(), "Please login", Toast.LENGTH_SHORT).show();
            }
        }
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
        mPresenter.getMovieDetails(movieId);
        mPresenter.getAccountStatesRating(movieId);
    }

    private void createDialog() {
        final RatingDialog ratingDialog = new RatingDialog(getActivity(), textUserRating, "Rate this movie", "Rate this movie");

        Button b1 = ratingDialog.getPositiveButton();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int rating = ratingDialog.getCurrentRating();
                if (rating < 1) {
                    Toast.makeText(getActivity(), "Please rate this movie", Toast.LENGTH_SHORT).show();
                } else {
                    mPresenter.addRating(movieId, rating);
                    ratingDialog.dismiss();
                }
            }
        });

        Button b2 = ratingDialog.getNegativeButton();
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPresenter.deleteRating(movieId, 0);
                ratingDialog.dismiss();
            }
        });
    }

    private void createChips(List<String> list, RecyclerView recyclerView) {
        ChipsLayoutManager chipsLayoutManager = ChipsLayoutManager.newBuilder(getContext())
                .setScrollingEnabled(false)
                .build();

        ChipsAdapter chipsAdapter = new ChipsAdapter();
        chipsAdapter.addAll(list);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(chipsLayoutManager);
        recyclerView.setAdapter(chipsAdapter);
    }
}