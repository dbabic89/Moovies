package com.example.android.moovies.ui.movie_detail;

import android.app.Dialog;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager;
import com.example.android.moovies.R;
import com.example.android.moovies.data.local.SharedPreferencesManager;
import com.example.android.moovies.data.models.movie.Backdrop;
import com.example.android.moovies.data.models.movie.Cast;
import com.example.android.moovies.data.models.movie.CollectionDetail;
import com.example.android.moovies.data.models.movie.Crew;
import com.example.android.moovies.data.models.movie.Genre;
import com.example.android.moovies.data.models.movie.Keyword;
import com.example.android.moovies.data.models.movie.Reviews;
import com.example.android.moovies.data.models.movie.Video;
import com.example.android.moovies.data.models.movie.Videos;
import com.example.android.moovies.ui.gallery_videos.GalleryVideosActivity;
import com.example.android.moovies.ui.movie_list.HorizontalRecyclerView;
import com.example.android.moovies.utils.Constants;
import com.example.android.moovies.utils.FragmentCommunication;
import com.example.android.moovies.utils.StringFormating;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

public class MovieDetailFragment extends Fragment implements MovieDetailMvpView, View.OnClickListener {

    View mView;
    TextView textTitle, textRating, textTagline, textOverview, textStatus, textReleaseDate, textDirectedBy, textDuration, textWatchlist,
            textUserRating, textList, textCollectionName, textBelongsToCollection, textCredits, textCertification, textReviewLabel,
            textInfoLabel, textMovieFullTitle, textVoteRating, textVoteCount, textProductionCompanies, textProductionCountries,
            textSpokenLanguage, textBudget, textRevenue, textKeywordsLabel, textButtonImages, textButtonVideos;
    ImageView imageBackdrop, imagePoster, imageCollection, imageButtonImages, imageButtonVideos, imagePlayButtonVideos;
    ImageButton imageButtonWatchlist, imageButtonRating;
    Button buttonCheckCollection;

    RecyclerView recyclerViewGenres, recyclerViewKeywords;
    LinearLayout linearLayoutReviews, linearLayoutVideos, linearLayoutImages;
    RelativeLayout relativeLayoutCollection;
    FrameLayout frameLayoutSimilar;

    LayoutInflater layoutInflater;

    MovieDetailPresenter mPresenter;
    ChipsAdapter chipsAdapter;
    SharedPreferencesManager sharedPreferencesManager;
    FragmentCommunication fragmentCommunication;

    int currentRating, movieId = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        movieId = getArguments().getInt("movie_id");

        sharedPreferencesManager = new SharedPreferencesManager(getActivity());
        fragmentCommunication = (FragmentCommunication) getActivity();
        layoutInflater = getActivity().getLayoutInflater();

        mView = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        initializeViews(mView);
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
    public void showMovieDetail(String title, String voteRating, String voteCount, String status) {

        textRating.setText(voteRating);
        textVoteRating.setText(voteRating);
        textVoteCount.setText(voteCount);
        textTitle.setText(title);
        textInfoLabel.setVisibility(View.VISIBLE);
        textMovieFullTitle.setText(title);
        textStatus.setText(status);

    }

    @Override
    public void showBackdrop(String imageUrl) {
        Picasso.with(getActivity()).load(imageUrl).into(imageBackdrop);
    }

    @Override
    public void showNoBackdrop() {
        imagePoster.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        Picasso.with(getActivity()).load(R.drawable.blue_circle).into(imageBackdrop);
    }

    @Override
    public void showTagline(String tagline) {
        textTagline.setText(tagline);
    }

    @Override
    public void showPoster(String imageUrl) {
        Picasso.with(getActivity()).load(imageUrl).into(imagePoster);
    }

    @Override
    public void showNoPoster() {
        Picasso.with(getActivity()).load(R.drawable.blue_circle).into(imagePoster);
    }

    @Override
    public void showOverview(String overview) {
        textOverview.setText(overview);
    }

    @Override
    public void showGenres(List<Genre> genreList) {


        ChipsLayoutManager chipsLayoutManager = ChipsLayoutManager.newBuilder(getContext())
                .setScrollingEnabled(false)
                .build();

        chipsAdapter = new ChipsAdapter(StringFormating.getGenres(genreList));
        recyclerViewGenres.setVisibility(View.VISIBLE);
        recyclerViewGenres.setLayoutManager(chipsLayoutManager);
        recyclerViewGenres.setAdapter(chipsAdapter);

    }

    @Override
    public void showCollection(CollectionDetail collectionDetail) {

        final int id = collectionDetail.getId();

        Picasso.with(getActivity()).load(Constants.URL_IMG_BACKDROP + collectionDetail.getBackdropPath()).into(imageCollection);
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
        textDirectedBy.setText(StringFormating.getDirectors(crewList));
    }

    @Override
    public void showNoDirectedBy() {
        textDirectedBy.setText(R.string.not_available);
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
            imageButtonRating.setBackground(getResources().getDrawable(R.drawable.blue_circle));
        } else {
            textUserRating.setText(String.valueOf(rating));
            imageButtonRating.setBackground(getResources().getDrawable(R.drawable.green_circle));
        }
    }

    @Override
    public void showList(boolean list) {
        if (list) textList.setText(R.string.on_list);
        else textList.setText(R.string.add_to_list);
    }

    @Override
    public void showImages(List<Backdrop> backdropList) {
        textButtonImages.setText("Images (" + backdropList.size() + ")");
        Picasso.with(getActivity()).load(Constants.URL_IMG_BACKDROP + backdropList.get(new Random().nextInt(backdropList.size())).getFilePath()).into(imageButtonImages);
    }

    @Override
    public void showNoImages() {
        textButtonImages.setText(R.string.no_images);
        Picasso.with(getActivity()).load(R.drawable.blue_circle).into(imageButtonImages);
    }

    @Override
    public void showVideos(final Videos videos, final String title, final String overview) {

        imagePlayButtonVideos.setVisibility(View.VISIBLE);

        imageButtonVideos.setOnClickListener(new View.OnClickListener() {
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
        textButtonVideos.setText("Videos (" + videoList.size() + ")");
        Picasso.with(getActivity()).load("http://img.youtube.com/vi/" + videoList.get(new Random().nextInt(videoList.size())).getKey() + "/0.jpg").into(imageButtonVideos);

    }

    @Override
    public void showNoVideos() {
        textButtonVideos.setText(R.string.no_videos);
        Picasso.with(getActivity()).load(R.drawable.blue_circle).into(imageButtonVideos);

    }

    @Override
    public void showReviews(final Reviews reviews) {

        textReviewLabel.setVisibility(View.VISIBLE);
        linearLayoutReviews.setVisibility(View.VISIBLE);
        TextView te = (TextView) linearLayoutReviews.findViewById(R.id.text_reviews);
        te.setText("Reviews (" + reviews.getResults().size() + ")");
        linearLayoutReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentCommunication.startReviewList(reviews);
            }
        });
    }

    @Override
    public void showCast(List<Cast> castList) {

        textCredits.setVisibility(View.VISIBLE);
        LinearLayout linearLayout = (LinearLayout) mView.findViewById(R.id.linear_layout_cast);

        for (int i = 0; i < 3; i++) {

            RelativeLayout relativeLayout = (RelativeLayout) layoutInflater.inflate(R.layout.icon_person, null);
            TextView textView = (TextView) relativeLayout.findViewById(R.id.text_person);
            ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.image_person);
            textView.setText(castList.get(i).getName());
            Picasso.with(getActivity()).load(Constants.URL_IMG_MOVIE_POSTER + castList.get(i).getProfilePath()).resize(400, 200).into(imageView);
            linearLayout.addView(relativeLayout);

        }
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
        bundle.putInt("tab", 4);
        bundle.putInt("movie_id", movieId);
        similar.setArguments(bundle);
        frameLayoutSimilar.setVisibility(View.VISIBLE);
        fragmentManager.beginTransaction().replace(R.id.similar, similar).commit();

    }

    @Override
    public void showKeywords(List<Keyword> keywordsList) {

        ChipsLayoutManager chipsLayoutManager = ChipsLayoutManager.newBuilder(getContext())
                .setScrollingEnabled(false)
                .build();

        chipsAdapter = new ChipsAdapter(StringFormating.getKeywords(keywordsList));
        textKeywordsLabel.setVisibility(View.VISIBLE);
        recyclerViewKeywords.setVisibility(View.VISIBLE);
        recyclerViewKeywords.setLayoutManager(chipsLayoutManager);
        recyclerViewKeywords.setAdapter(chipsAdapter);

    }

    @Override
    public void showError() {

    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if (id == R.id.image_button_watchlist) {

            if (!sharedPreferencesManager.getSessionId().isEmpty()){

                if (textWatchlist.getText().equals("Add to watchlist")) {
                    textWatchlist.setText("On watchlist");
                    imageButtonWatchlist.setBackground(getResources().getDrawable(R.drawable.green_circle));
                    mPresenter.addMovieToWatchlist(movieId, true);
                } else {
                    textWatchlist.setText("Add to watchlist");
                    mPresenter.addMovieToWatchlist(movieId, false);
                    imageButtonWatchlist.setBackground(getResources().getDrawable(R.drawable.blue_circle));
                }
            } else {
                Toast.makeText(getActivity(), "Please login", Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.image_button_rating) {

            if (!sharedPreferencesManager.getSessionId().isEmpty()) {
                createDialog(movieId);
            } else {
                Toast.makeText(getActivity(), "Please login", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setPresenter() {
        mPresenter = new MovieDetailPresenter(this, sharedPreferencesManager);
        mPresenter.attachView(this);
        mPresenter.getMovieDetails(movieId);
        mPresenter.getAccountStatesRated(movieId);
    }

    private void initializeViews(View view) {

        linearLayoutReviews = (LinearLayout) mView.findViewById(R.id.linear_layout_reviews);
        linearLayoutImages = (LinearLayout) mView.findViewById(R.id.linear_layout_images);
        linearLayoutVideos = (LinearLayout) mView.findViewById(R.id.linear_layout_videos);
        relativeLayoutCollection = (RelativeLayout) view.findViewById(R.id.relative_layout_collection);
        frameLayoutSimilar = (FrameLayout) view.findViewById(R.id.similar);

        textTitle = (TextView) view.findViewById(R.id.text_movie_title);
        textRating = (TextView) view.findViewById(R.id.text_movie_rating);
        textTagline = (TextView) view.findViewById(R.id.text_movie_tagline);
        textOverview = (TextView) view.findViewById(R.id.text_movie_overview);
        textReleaseDate = (TextView) view.findViewById(R.id.text_release_date);
        textDirectedBy = (TextView) view.findViewById(R.id.text_directed_by);
        textStatus = (TextView) view.findViewById(R.id.text_status);
        textDuration = (TextView) view.findViewById(R.id.text_duration);
        textWatchlist = (TextView) view.findViewById(R.id.text_watchlist);
        textUserRating = (TextView) view.findViewById(R.id.text_rating);
        textList = (TextView) view.findViewById(R.id.text_list);
        textCollectionName = (TextView) view.findViewById(R.id.text_collection_name);
        textBelongsToCollection = (TextView) view.findViewById(R.id.text_belongs_to_collection_label);
        textCredits = (TextView) view.findViewById(R.id.text_credits_label);
        textCertification = (TextView) view.findViewById(R.id.text_certification);
        textReviewLabel = (TextView) view.findViewById(R.id.text_reviews_label);
        textInfoLabel = (TextView) view.findViewById(R.id.text_info_label);
        textMovieFullTitle = (TextView) view.findViewById(R.id.text_movie_full_title);
        textVoteRating = (TextView) view.findViewById(R.id.text_movie_vote_rating);
        textVoteCount = (TextView) view.findViewById(R.id.text_movie_vote_count);
        textProductionCompanies = (TextView) view.findViewById(R.id.text_movie_production_companies);
        textProductionCountries = (TextView) view.findViewById(R.id.text_movie_production_countries);
        textSpokenLanguage = (TextView) view.findViewById(R.id.text_movie_spoken_language);
        textBudget = (TextView) view.findViewById(R.id.text_movie_budget);
        textRevenue = (TextView) view.findViewById(R.id.text_movie_revenue);
        textKeywordsLabel = (TextView) view.findViewById(R.id.text_keywords_label);
        textButtonImages = (TextView) linearLayoutImages.findViewById(R.id.text_button);
        textButtonVideos = (TextView) linearLayoutVideos.findViewById(R.id.text_button);

        imageBackdrop = (ImageView) view.findViewById(R.id.image_movie_backdrop);
        imagePoster = (ImageView) view.findViewById(R.id.image_movie_poster);
        imageCollection = (ImageView) view.findViewById(R.id.image_collection_backdrop);
        imageButtonImages = (ImageView) linearLayoutImages.findViewById(R.id.image_button);
        imageButtonVideos = (ImageView) linearLayoutVideos.findViewById(R.id.image_button);
        imagePlayButtonVideos = (ImageView) linearLayoutVideos.findViewById(R.id.play_button);

        imageButtonWatchlist = (ImageButton) view.findViewById(R.id.image_button_watchlist);
        imageButtonRating = (ImageButton) view.findViewById(R.id.image_button_rating);

        buttonCheckCollection = (Button) view.findViewById(R.id.button_check_collection);

        recyclerViewGenres = (RecyclerView) view.findViewById(R.id.recycler_view_genres);
        recyclerViewKeywords = (RecyclerView) view.findViewById(R.id.recycler_view_keywords);

    }

    private void createDialog(final int movieId) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog__rating);
        dialog.setTitle("Rate this movie");
        com.hedgehog.ratingbar.RatingBar ratingBar = (com.hedgehog.ratingbar.RatingBar) dialog.findViewById(R.id.rating_bar);
        ratingBar.setOnRatingChangeListener(new com.hedgehog.ratingbar.RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(final float RatingCount) {

                currentRating = (int) RatingCount;

            }
        });

        Button b1 = (Button) dialog.findViewById(R.id.button3);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (currentRating < 1) {
                    Toast.makeText(getActivity(), "Rate movie please", Toast.LENGTH_SHORT).show();
                } else {
                    mPresenter.addMovieRating(movieId, currentRating);
                    dialog.dismiss();
                }
            }
        });

        Button b2 = (Button) dialog.findViewById(R.id.button4);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPresenter.deleteMovieRating(movieId, 0);
                dialog.dismiss();
            }
        });

        if (textUserRating.getText().toString().equals("Rate this movie")) ratingBar.setStar(0);
        else ratingBar.setStar(Integer.parseInt(textUserRating.getText().toString()));

        dialog.show();
    }

}