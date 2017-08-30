package com.example.android.moovies.ui.movie_detail;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.example.android.moovies.data.models.movie.Country;
import com.example.android.moovies.data.models.movie.Genre;
import com.example.android.moovies.data.models.movie.Keyword;
import com.example.android.moovies.data.models.movie.MovieDetail;
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

public class MovieDetailFragment extends Fragment implements MovieDetailMvpView {

    View mView;
    TextView textMovieTitle, textMovieRating, textMovieTagline, textMovieOverview, textMovieReleaseDate, textMovieDirectedBy,
            textMovieDuration, textWatchlist, textRating, textList, textCollectionName, textBelognsToCollection, textCertification;
    ImageView imageMovieBackdrop, imageMoviePoster, imageMovieCollection;
    ImageButton imageButtonWatchlist, imageButtonRating;
    Button buttonCheckCollectioN;

    RecyclerView recyclerViewKeywords;
    RelativeLayout relativeLayoutCollection;

    LayoutInflater layoutInflater;
    LinearLayout.LayoutParams layoutParams;

    MovieDetailPresenter mPresenter;
    KeywordsAdapter keywordsAdapter;
    SharedPreferencesManager sharedPreferencesManager;
    FragmentCommunication fragmentCommunication;

    int currentRating, movieId = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        movieId = getArguments().getInt("movie_id");

        sharedPreferencesManager = new SharedPreferencesManager(getActivity());
        fragmentCommunication = (FragmentCommunication) getActivity();
        layoutInflater = getActivity().getLayoutInflater();

        layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        mView = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        initializeViews(mView);

        mPresenter = new MovieDetailPresenter(this, sharedPreferencesManager);
        mPresenter.attachView(this);
        mPresenter.getMovieDetails(movieId);
        mPresenter.getAccountStatesRated(movieId);

        imageButtonWatchlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (textWatchlist.getText().equals("Add to watchlist")) {
                    textWatchlist.setText("On watchlist");
                    imageButtonWatchlist.setBackground(getResources().getDrawable(R.drawable.green_circle));
                    mPresenter.addMovieToWatchlist(movieId, true);
                } else {
                    textWatchlist.setText("Add to watchlist");
                    mPresenter.addMovieToWatchlist(movieId, false);
                    imageButtonWatchlist.setBackground(getResources().getDrawable(R.drawable.blue_circle));
                }
            }
        });

        imageButtonRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog(movieId);
            }
        });

        return mView;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void showMovieDetail(final MovieDetail movieDetail) {

        textMovieRating.setText(String.valueOf(movieDetail.getVoteAverage()));
        textMovieTitle.setText(movieDetail.getTitle());
        textMovieTagline.setText(movieDetail.getTagline());
        textMovieOverview.setText(movieDetail.getOverview());
        textMovieReleaseDate.setText(StringFormating.dateFormating(movieDetail.getReleaseDate()));
        textMovieDirectedBy.setText(StringFormating.getDirectors(movieDetail.getCredits().getCrew()));
        textMovieDuration.setText(StringFormating.timeFormating(movieDetail.getRuntime()));

        Picasso.with(getActivity()).load(Constants.URL_IMG_BACKDROP + movieDetail.getBackdropPath()).into(imageMovieBackdrop);
        Picasso.with(getActivity()).load(Constants.URL_IMG_MOVIE_POSTER + movieDetail.getPosterPath()).into(imageMoviePoster);

    }

    @Override
    public void showGenres(List<Genre> genreList) {

        LinearLayout linearLayout = (LinearLayout) mView.findViewById(R.id.linear_layout_genres);

        for (String string : StringFormating.getGenres(genreList)) {

            TextView textView = (TextView) layoutInflater.inflate(R.layout.genre_text, null);
            textView.setText(string);
            layoutParams.setMargins(0, 0, 8, 8);
            textView.setLayoutParams(layoutParams);
            linearLayout.addView(textView);
        }

    }

    @Override
    public void showCollection(CollectionDetail collectionDetail) {
        if (collectionDetail != null) {
            final int id = collectionDetail.getId();

            Picasso.with(getActivity()).load(Constants.URL_IMG_BACKDROP + collectionDetail.getBackdropPath()).into(imageMovieCollection);
            textCollectionName.setText(collectionDetail.getName());
            relativeLayoutCollection.setVisibility(View.VISIBLE);
            textBelognsToCollection.setVisibility(View.VISIBLE);

            buttonCheckCollectioN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragmentCommunication.startCollectionList(id);
                }
            });
        }
    }

    @Override
    public void showCertification(List<Country> countries) {

        for (Country country : countries)
            if (country.getIso31661().equals("US")) {
                String certification = country.getCertification();
                if (certification.isEmpty()) textCertification.setText("N/A");
                else textCertification.setText(certification);
            }
    }

    @Override
    public void showWatchlist(boolean watchlist) {
        Log.i("TAG", "showWatchlist");
        if (watchlist) {
            textWatchlist.setText(R.string.on_watchlist);
            imageButtonWatchlist.setBackground(getResources().getDrawable(R.drawable.green_circle));
        } else textWatchlist.setText(R.string.add_to_watchlist);
    }

    @Override
    public void showRating(int rating) {
        Log.i("TAG", "showRating");
        if (rating == 0) {
            textRating.setText("Rate this movie");
            imageButtonRating.setBackground(getResources().getDrawable(R.drawable.blue_circle));
        } else {
            textRating.setText(String.valueOf(rating));
            imageButtonRating.setBackground(getResources().getDrawable(R.drawable.green_circle));
        }
    }

    @Override
    public void showList(boolean list) {
        if (list) textList.setText(R.string.on_list);
        else textList.setText(R.string.add_to_list);
    }

    @Override
    public void showImagesForSlider(List<Backdrop> backdropList) {

        LinearLayout imageButtonLayout = (LinearLayout) mView.findViewById(R.id.linear_layout_images);
        ImageView imageButton = (ImageView) imageButtonLayout.findViewById(R.id.image_button);
        TextView textButton = (TextView) imageButtonLayout.findViewById(R.id.text_button);

        if (!backdropList.isEmpty()) {

            textButton.setText("Images (" + backdropList.size() + ")");

            Picasso.with(getActivity()).load(Constants.URL_IMG_BACKDROP + backdropList.get(new Random().nextInt(backdropList.size())).getFilePath()).into(imageButton);
        } else textButton.setText("No images");

    }

    @Override
    public void showVideosForSlider(final Videos videos, final String title, final String overview) {

        LinearLayout imageButtonLayout = (LinearLayout) mView.findViewById(R.id.linear_layout_videos);
        ImageView imageButton = (ImageView) imageButtonLayout.findViewById(R.id.image_button);
        ImageView playButton = (ImageView) imageButtonLayout.findViewById(R.id.play_button);
        TextView textButton = (TextView) imageButtonLayout.findViewById(R.id.text_button);

        if (!videos.getResults().isEmpty()) {
            playButton.setVisibility(View.VISIBLE);

            imageButton.setOnClickListener(new View.OnClickListener() {
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
            textButton.setText("Videos (" + videoList.size() + ")");
            Picasso.with(getActivity()).load("http://img.youtube.com/vi/" + videoList.get(new Random().nextInt(videoList.size())).getKey() + "/0.jpg").into(imageButton);
        } else textButton.setText("No videos");

    }

    @Override
    public void showReviews(final Reviews reviews) {

        if (!reviews.getResults().isEmpty()) {
            LinearLayout linearLayout = (LinearLayout) mView.findViewById(R.id.linear_layout_reviews);
            linearLayout.setVisibility(View.VISIBLE);
            TextView te = (TextView) linearLayout.findViewById(R.id.text_reviews);
            te.setText("Reviews (" + reviews.getResults().size() + ")");
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    fragmentCommunication.startReviewList(reviews);
                }
            });
        }
    }

    @Override
    public void showCast(List<Cast> castList) {

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
    public void showSimilarMovies(int movieId) {

        FragmentManager fragmentManager = getChildFragmentManager();

        Fragment similar = new HorizontalRecyclerView();
        Bundle bundle = new Bundle();
        bundle.putInt("tab", 4);
        bundle.putInt("movie_id", movieId);
        similar.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.similar, similar).commit();

    }

    @Override
    public void showKeywords(List<Keyword> keywordsList) {

        ChipsLayoutManager chipsLayoutManager = ChipsLayoutManager.newBuilder(getContext())
                .setScrollingEnabled(false)
                .build();

        keywordsAdapter = new KeywordsAdapter(StringFormating.getKeywords(keywordsList));
        recyclerViewKeywords.setLayoutManager(chipsLayoutManager);
        recyclerViewKeywords.setAdapter(keywordsAdapter);

    }

    @Override
    public void showError() {

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

        if (textRating.getText().toString().equals("Rate this movie")) ratingBar.setStar(0);
        else ratingBar.setStar(Integer.parseInt(textRating.getText().toString()));

        dialog.show();
    }

    private void initializeViews(View view) {

        textMovieTitle = (TextView) view.findViewById(R.id.text_movie_title);
        textMovieRating = (TextView) view.findViewById(R.id.text_movie_rating);
        textMovieTagline = (TextView) view.findViewById(R.id.text_movie_tagline);
        textMovieOverview = (TextView) view.findViewById(R.id.text_movie_overview);
        textMovieReleaseDate = (TextView) view.findViewById(R.id.text_release_date);
        textMovieDirectedBy = (TextView) view.findViewById(R.id.text_directed_by);
        textMovieDuration = (TextView) view.findViewById(R.id.text_duration);
        textWatchlist = (TextView) view.findViewById(R.id.text_watchlist);
        textRating = (TextView) view.findViewById(R.id.text_rating);
        textList = (TextView) view.findViewById(R.id.text_list);
        textCollectionName = (TextView) view.findViewById(R.id.text_collection_name);
        textBelognsToCollection = (TextView) view.findViewById(R.id.text_belongs_to_collection);
        textCertification = (TextView) view.findViewById(R.id.text_certification);

        imageMovieBackdrop = (ImageView) view.findViewById(R.id.image_movie_backdrop);
        imageMoviePoster = (ImageView) view.findViewById(R.id.image_movie_poster);
        imageMovieCollection = (ImageView) view.findViewById(R.id.image_collection_backdrop);

        imageButtonWatchlist = (ImageButton) view.findViewById(R.id.image_button_watchlist);
        imageButtonRating = (ImageButton) view.findViewById(R.id.image_button_rating);

        buttonCheckCollectioN = (Button) view.findViewById(R.id.button_check_collection);

        recyclerViewKeywords =(RecyclerView) view.findViewById(R.id.recycler_view_keywords);

        relativeLayoutCollection = (RelativeLayout) view.findViewById(R.id.relative_layout_collection);

    }
}