package com.example.android.moovies.ui.tv_detail;

import com.example.android.moovies.domain.models.mtv.Cast;
import com.example.android.moovies.domain.models.mtv.Genre;
import com.example.android.moovies.domain.models.mtv.Images;
import com.example.android.moovies.domain.models.mtv.Videos;
import com.example.android.moovies.domain.models.tv.CreatedBy;
import com.example.android.moovies.domain.models.tv.KeywordsResults;
import com.example.android.moovies.domain.models.tv.Season;
import com.example.android.moovies.ui.base.BaseMvpView;

import java.util.List;

interface TvDetailMvpView extends BaseMvpView {

    void showDetails(String rating, String title, String voteCount, List<Season> seasons);

    void showBackdrop(String imageUrl);

    void showNoBackdrop();

    void showGenres(List<Genre> genreList);

    void showPoster(String imageUrl);

    void showNoPoster();

    void showSeasonAndEpisode(String season, String episode);

    void showReleaseDate(String releaseDate);

    void showDuration(String duration);

    void showCreatedBy(List<CreatedBy> crewList);

    void showOverview(String overview);

    void showWatchlist(boolean watchlist);

    void showRating(int rating);

    void showList(boolean list);

    void showImages(Images images);

    void showNoImages();

    void showVideos(Videos videos, String title, String overview);

    void showNoVideos();

    void showCast(List<Cast> castList);

    void showOriginalTitle(String originalTitle);

    void showNetwork(String network);

    void showStatus(String status);

    void showProductionCompanies(String productionCompanies);

    void showProductionCountries(String productionCountries);

    void showSpokenLanguage(String spokenLanguages);

    void showSimilarTvs(int movieId);

    void showKeywords(List<KeywordsResults> keywordsList);

    void showError();

}
