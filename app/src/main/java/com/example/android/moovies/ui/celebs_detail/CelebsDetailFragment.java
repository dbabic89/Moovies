package com.example.android.moovies.ui.celebs_detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import com.example.android.moovies.domain.models.celebrity.Posters;
import com.example.android.moovies.domain.models.celebrity.TvCredits;
import com.example.android.moovies.domain.models.mtv.MtvPoster;
import com.example.android.moovies.ui.common.mtv_grid.MtvGridFragment;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.moovies.utils.Constants.URL_POSTER;

public class CelebsDetailFragment extends Fragment implements CelebsDetailMvpView {

    @BindView(R.id.text_name)
    TextView textName;

    @BindView(R.id.text_birthday)
    TextView textBirthday;

    @BindView(R.id.text_birthplace)
    TextView textBirthplace;

    @BindView(R.id.text_deathday)
    TextView textDeathDay;

    @BindView(R.id.expand_text_view)
    ExpandableTextView expTvBiography;

    @BindView(R.id.image_poster)
    ImageView imagePoster;

    @Inject
    CelebsDetailPresenter celebsDetailPresenter;

    @Inject
    Picasso picasso;

    int id;
    View mView;

    public static CelebsDetailFragment newInstance(int id) {

        Bundle bundle = new Bundle();
        bundle.putInt("celebs_id", id);

        CelebsDetailFragment fragment = new CelebsDetailFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            id = bundle.getInt("celebs_id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_celebs_detail, container, false);
        readBundle(getArguments());
        ButterKnife.bind(this, mView);

        createComponent();
        createPresenter();

        return mView;
    }

    @Override
    public void showPoster(String poster) {
        picasso.load(URL_POSTER + poster).into(imagePoster);
    }

    @Override
    public void showName(String name) {
        textName.setText(name);
    }

    @Override
    public void showBirthday(String birthday) {
        textBirthday.setText("Born: " + birthday);
    }

    @Override
    public void showBirthplace(String birthplace) {
        textBirthplace.setText("in " + birthplace);
    }

    @Override
    public void showDeathday(String deathday) {
        textDeathDay.setText("Died: " + deathday);
    }

    @Override
    public void showBiography(String biography) {
        expTvBiography.setText(biography);
    }

    @Override
    public void showCredits(MovieCredits movieCredits, TvCredits tvCredits) {

        List<MtvPoster> movies = new ArrayList<>();
        List<MtvPoster> tvs = new ArrayList<>();

        for (AsCast cast : movieCredits.getCast()) {
            movies.add(new MtvPoster(cast.getId(), cast.getPosterPath()));
        }
        for (AsCast cast : tvCredits.getCast()) {
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

    private void createPresenter() {
        celebsDetailPresenter.attachView(this);
        celebsDetailPresenter.getDetails(id);
    }

    private void createComponent() {
        MovieComponent component = DaggerMovieComponent.builder()
                .applicationComponent(Moovies.get(getActivity()).getApplicationComponent())
                .activityModule(new ActivityModule(getActivity()))
                .build();

        component.inject(this);
    }

}
