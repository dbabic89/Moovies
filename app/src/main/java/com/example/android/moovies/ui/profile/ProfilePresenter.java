package com.example.android.moovies.ui.profile;

import android.util.Base64;
import android.util.Log;

import com.example.android.moovies.data.models.authentication.RequestTokenResponse;
import com.example.android.moovies.data.remote.TmdbClient;
import com.example.android.moovies.data.remote.TmdbInterface;
import com.example.android.moovies.ui.base.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfilePresenter extends BasePresenter<ProfileMvpView> {

    @Override
    public void attachView(ProfileMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void requestToken(){
        TmdbInterface tmdbInterface = TmdbClient.getTmdbV4Client().create(TmdbInterface.class);
        String bearer = "Basic %s";
        String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyM2ZiZmIzM2JjZGIzYjE4ZjJkYzY4YTM0MzgyNGEyMiIsInN1YiI6IjU4Zjg4YTUwYzNhMzY4NzVmMDAwNjc3NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.gAGNBXdD0Xf53WusYqEU4ioLAp0vCWmdUhyCjr8qqHo";
        String base64 = "Bearer " + Base64.encodeToString(token.getBytes(), Base64.NO_WRAP);
        String govno = "application/json;charset=utf-8";
        String base644 = Base64.encodeToString(govno.getBytes(), Base64.NO_WRAP);
//
//        Call<RequestTokenResponse> call = tmdbInterface.requestToken(base64, base644);
//        call.enqueue(new Callback<RequestTokenResponse>() {
//            @Override
//            public void onResponse(Call<RequestTokenResponse> call, Response<RequestTokenResponse> response) {
//                Log.i("TAG", String.valueOf(response.code()));
//                Log.i("TAG", String.valueOf(response.message()));
////                Log.i("TAG", response.body().getRequestToken());
//                Log.i("TAG", "response");
//            }
//
//            @Override
//            public void onFailure(Call<RequestTokenResponse> call, Throwable t) {
//
//                Log.i("TAG", "fail");
//            }
//        });


        Call<RequestTokenResponse> call1 = tmdbInterface.requestToken(token, govno);
        call1.enqueue(new Callback<RequestTokenResponse>() {
            @Override
            public void onResponse(Call<RequestTokenResponse> call, Response<RequestTokenResponse> response) {
                Log.i("TAG", String.valueOf(response.code()));
                Log.i("TAG", String.valueOf(response.message()));
//                Log.i("TAG", response.body().getRequestToken());
                Log.i("TAG", "response");
            }

            @Override
            public void onFailure(Call<RequestTokenResponse> call, Throwable t) {

                Log.i("TAG", "fail");
            }
        });
    }


    public boolean userLogin(){




        return true;
    }
}
