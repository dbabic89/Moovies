package com.example.android.moovies.ui.celebs_detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.moovies.Moovies;
import com.example.android.moovies.R;
import com.example.android.moovies.di.component.DaggerMovieComponent;
import com.example.android.moovies.di.component.MovieComponent;
import com.example.android.moovies.di.module.ActivityModule;
import com.example.android.moovies.domain.models.celebrity.AsCast;
import com.example.android.moovies.domain.models.celebrity.MovieCredits;
import com.example.android.moovies.domain.models.celebrity.MtvPoster;
import com.example.android.moovies.domain.models.celebrity.Posters;
import com.example.android.moovies.domain.models.celebrity.TvCredits;
import com.example.android.moovies.ui.common.mtv_grid.MtvGridFragment;
import com.example.android.moovies.utils.Constants;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CelebsDetailFragment extends Fragment implements CelebsDetailMvpView {

    TextView textName, textBirthday, textBirthplace, textDeathDay;
    ExpandableTextView expTvBiography;
    ImageView imagePoster;
    View mView;

    @Inject CelebsDetailPresenter celebsDetailPresenter;
    @Inject Picasso picasso;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_celebs_detail, container, false);

        MovieComponent component = DaggerMovieComponent.builder()
                .applicationComponent(Moovies.get(getActivity()).getApplicationComponent())
                .activityModule(new ActivityModule(getActivity()))
                .build();

        component.inject(this);

        initializeViews(mView);
        celebsDetailPresenter.attachView(this);
        celebsDetailPresenter.getDetails(getArguments().getInt("celebs_id"));

        return mView;
    }

    private void initializeViews(View view) {

        imagePoster = (ImageView) view.findViewById(R.id.image_poster);
        textName = (TextView) view.findViewById(R.id.text_name);
        textBirthday = (TextView) view.findViewById(R.id.text_birthday);
        textBirthplace = (TextView) view.findViewById(R.id.text_birthplace);
        textDeathDay = (TextView) view.findViewById(R.id.text_deathday);
        expTvBiography = (ExpandableTextView) view.findViewById(R.id.expand_text_view);

    }

    @Override
    public void showPoster(String poster) {
        picasso.load(Constants.URL_POSTER + poster).into(imagePoster);
    }

    @Override
    public void showName(String name) {
        Log.i("TAG", name);
        textName.setText(name);
    }

    @Override
    public void showBirthday(String birthday) {
        Log.i("TAG", birthday);
        textBirthday.setText("Born: " + birthday);
    }

    @Override
    public void showBirthplace(String birthplace) {
        Log.i("TAG", birthplace);
        textBirthplace.setText("in " + birthplace);
    }

    @Override
    public void showDeathday(String deathday) {
        Log.i("TAG", deathday);
        textDeathDay.setText("Died: " + deathday);
    }

    @Override
    public void showBiography(String biography) {
        Log.i("TAG", biography);
        expTvBiography.setText(biography);
    }

    @Override
    public void showCredits(MovieCredits movieCredits, TvCredits tvCredits) {

        List<MtvPoster> movies = new ArrayList<>();
        List<MtvPoster> tvs = new ArrayList<>();

        for (AsCast cast: movieCredits.getCast()) {
            movies.add(new MtvPoster(cast.getId(), cast.getPosterPath()));
        }
        for (AsCast cast: tvCredits.getCast()) {
            tvs.add(new MtvPoster(cast.getId(), cast.getPosterPath()));
        }

//        CelebsCredits celebsCredits = new CelebsCredits(new Posters(movies), new Posters(tvs));

        Fragment fragment = new MtvGridFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putSerializable("movies", new Posters(movies));
        fragment.setArguments(bundle1);

        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.view_pager, fragment).commit();

    }
}
