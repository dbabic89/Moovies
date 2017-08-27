package com.example.android.moovies.ui.movie_detail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager;
import com.example.android.moovies.R;
import com.example.android.moovies.data.models.movie.Backdrop;
import com.example.android.moovies.data.models.movie.Genre;
import com.example.android.moovies.data.models.movie.Keyword;
import com.example.android.moovies.data.models.movie.MovieDetail;
import com.example.android.moovies.data.models.movie.Review;
import com.example.android.moovies.data.models.movie.Video;
import com.example.android.moovies.ui.movie_list.HorizontalRecyclerView;
import com.example.android.moovies.utils.Constants;
import com.example.android.moovies.utils.FragmentCommunication;
import com.example.android.moovies.utils.StringFormating;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailFragment extends Fragment implements MovieDetailMvpView {

    @BindView(R.id.text_movie_title)
    TextView textMovieTitle;

    @BindView(R.id.text_movie_rating)
    TextView textMovieRating;

    @BindView(R.id.image_movie_backdrop)
    ImageView imageMovieBackdrop;

    @BindView(R.id.image_movie_poster)
    ImageView imageMoviePoster;

    @BindView(R.id.text_movie_tagline)
    TextView textMovieTagline;

    @BindView(R.id.text_movie_overview)
    TextView textMovieOverview;

    @BindView(R.id.text_release_date)
    TextView textMovieReleaseDate;

    @BindView(R.id.text_directed_by_date)
    TextView textMovieDirectedBy;

    @BindView(R.id.text_duration)
    TextView textMovieDuration;

    @BindView(R.id.text_watchlist)
    TextView textWatchlist;

    @BindView(R.id.text_rating)
    TextView textRating;

    @BindView(R.id.text_list)
    TextView textList;

    @BindView(R.id.image_collection_backdrop)
    ImageView imageMovieCollection;

    @BindView(R.id.text_collection_name)
    TextView textCollectionName;

    @BindView(R.id.relative_layout_collection)
    RelativeLayout relativeLayoutCollection;

    @BindView(R.id.text_belongs_to_collection)
    TextView textBelognsToCollection;

    @BindView(R.id.recycler_view_keywords)
    RecyclerView recyclerViewKeywords;

    MovieDetailPresenter mPresenter;
    LayoutInflater layoutInflater;
    LinearLayout.LayoutParams layoutParams;
    KeywordsAdapter keywordsAdapter;

    View mView;

    int movieId;
    private int numberOfBackdrops;
    private FragmentCommunication fragmentCommunication;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        layoutInflater = getActivity().getLayoutInflater();

        fragmentCommunication = (FragmentCommunication) getActivity();

        layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        mView = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, mView);

        movieId = getArguments().getInt("movie_id");

        mPresenter = new MovieDetailPresenter();
        mPresenter.attachView(this);
        mPresenter.getMovieDetails(movieId);

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
//        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
//        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void showMovieDetail(MovieDetail movieDetail) {

        textMovieRating.setText(String.valueOf(movieDetail.getVoteAverage()));
        textMovieTitle.setText(movieDetail.getTitle());
        showGenres(movieDetail.getGenres());
        textMovieTagline.setText(movieDetail.getTagline());
        textMovieOverview.setText(movieDetail.getOverview());
        textMovieReleaseDate.setText(StringFormating.dateFormating(movieDetail.getReleaseDate()));
        textMovieDirectedBy.setText(StringFormating.getDirectors(movieDetail.getCredits().getCrew()));
        textMovieDuration.setText(StringFormating.timeFormating(movieDetail.getRuntime()));
        showKeywords(movieDetail.getKeywords().getKeywordsList());

        Picasso.with(getActivity()).load(Constants.URL_IMG_BACKDROP + movieDetail.getBackdropPath()).into(imageMovieBackdrop);
        Picasso.with(getActivity()).load(Constants.URL_IMG_MOVIE_POSTER + movieDetail.getPosterPath()).into(imageMoviePoster);

        if (movieDetail.getBelongsToCollection() != null) {
            Picasso.with(getActivity()).load(Constants.URL_IMG_BACKDROP + movieDetail.getBelongsToCollection().getBackdropPath()).into(imageMovieCollection);
            textCollectionName.setText(movieDetail.getBelongsToCollection().getName());
            relativeLayoutCollection.setVisibility(View.VISIBLE);
            textBelognsToCollection.setVisibility(View.VISIBLE);
        }

        FragmentManager fragmentManager = getChildFragmentManager();

        Fragment similar = new HorizontalRecyclerView();
        Bundle bundle = new Bundle();
        bundle.putInt("tab", 4);
        bundle.putInt("movie_id", movieId);
        similar.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.similar, similar).commit();

        List<Backdrop> backdropList = movieDetail.getImages().getBackdrops();

        List<Video> videoList = movieDetail.getVideos().getResults();
        getVideosForSlider(videoList);

        getImagesForSlider(backdropList);


        List<Review> reviewList = movieDetail.getReviews().getResults();

        getReviews(reviewList);
    }

    @Override
    public void showError() {

    }

    @Override
    public void showWatchlist(boolean watchlist) {
        if (watchlist) {
            textWatchlist.setText(R.string.on_watchlist);
        } else {
            textWatchlist.setText(R.string.add_to_watchlist);
        }
    }

    @Override
    public void showRating(int rating) {
        textRating.setText(String.valueOf(rating));
    }

    @Override
    public void showList(boolean list) {
        if (list) {
            textList.setText(R.string.on_list);
        } else {
            textList.setText(R.string.add_to_list);
        }
    }

    private void showGenres(List<Genre> genreList) {

        LinearLayout linearLayout = (LinearLayout) mView.findViewById(R.id.linear_layout_genres);

        for (String string : StringFormating.getGenres(genreList)) {

            TextView textView = (TextView) layoutInflater.inflate(R.layout.genre_text, null);
            textView.setText(string);
            layoutParams.setMargins(0, 0, 8, 8);
            textView.setLayoutParams(layoutParams);
            linearLayout.addView(textView);
        }

    }

    private void showKeywords(List<Keyword> keywordsList) {

        ChipsLayoutManager chipsLayoutManager = ChipsLayoutManager.newBuilder(getContext())
                .setScrollingEnabled(false)
                .build();

        keywordsAdapter = new KeywordsAdapter(StringFormating.getKeywords(keywordsList));
        recyclerViewKeywords.setLayoutManager(chipsLayoutManager);
        recyclerViewKeywords.setAdapter(keywordsAdapter);

    }

    private void getImagesForSlider(List<Backdrop> backdropList) {

        numberOfBackdrops = backdropList.size();

        for (int i = 0; i < numberOfBackdrops; i++) {
            Backdrop backdrop = backdropList.get(i);
            Log.i("TAG", i + " getImagesForSlider " + backdrop.getFilePath());
        }

        LinearLayout imageButtonLayout = (LinearLayout) mView.findViewById(R.id.linear_layout_images);
        ImageView imageButton = (ImageView) imageButtonLayout.findViewById(R.id.image_button);
        TextView textButton = (TextView) imageButtonLayout.findViewById(R.id.text_button);

        Random random = new Random();
        int randomPicture = random.nextInt(numberOfBackdrops);

        textButton.setText("Images (" + numberOfBackdrops + ")");
        Picasso.with(getActivity()).load(Constants.URL_IMG_BACKDROP + backdropList.get(randomPicture).getFilePath()).into(imageButton);

    }

    private void getVideosForSlider(List<Video> videoList) {

        for (int i = 0; i < videoList.size(); i++){
            Video video = videoList.get(i);
            Log.i("TAG", i + " getVideosForSlider " + video.getName());
        }

        LinearLayout imageButtonLayout = (LinearLayout) mView.findViewById(R.id.linear_layout_videos);
        ImageView imageButton = (ImageView) imageButtonLayout.findViewById(R.id.image_button);
        ImageView playButton = (ImageView) imageButtonLayout.findViewById(R.id.play_button);
        TextView textButton = (TextView) imageButtonLayout.findViewById(R.id.text_button);

        if (!videoList.isEmpty()){
            playButton.setVisibility(View.VISIBLE);
            Random random = new Random();
            int randomPicture = random.nextInt(videoList.size());

            textButton.setText("Videos (" + videoList.size() + ")");
            Picasso.with(getActivity()).load("http://img.youtube.com/vi/" +  videoList.get(randomPicture).getKey() + "/0.jpg").into(imageButton);
        } else {
            textButton.setText("No videos");
        }

    }

    private void getReviews(final List<Review> reviewList){

        for (int i = 0; i < reviewList.size(); i++){
            Review video = reviewList.get(i);
            Log.i("TAG", i + " getReview " + video.getAuthor());
        }

        if (!reviewList.isEmpty()){
            LinearLayout linearLayout = (LinearLayout) mView.findViewById(R.id.linear_layout_reviews);
            linearLayout.setVisibility(View.VISIBLE);
            TextView te = (TextView) linearLayout.findViewById(R.id.text_reviews);
            te.setText("Reviews (" + reviewList.size() + ")");
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ArrayList<Review> reviews = (ArrayList<Review>) reviewList;
                    fragmentCommunication.startReviewList(reviews);
                }
            });
        }
    }
}
