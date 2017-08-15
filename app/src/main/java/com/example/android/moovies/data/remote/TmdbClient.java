package com.example.android.moovies.data.remote;

import android.util.Base64;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class TmdbClient {

    private static final String TMDB_BASE_URL_V3 = "http://api.themoviedb.org/3/";
    private static final String TMDB_BASE_URL_V4 = "http://api.themoviedb.org/4/";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyM2ZiZmIzM2JjZGIzYjE4ZjJkYzY4YTM0MzgyNGEyMiIsInN1YiI6IjU4Zjg4YTUwYzNhMzY4NzVmMDAwNjc3NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.gAGNBXdD0Xf53WusYqEU4ioLAp0vCWmdUhyCjr8qqHo";
    String base64 = "Bearer " + Base64.encodeToString(token.getBytes(), Base64.NO_WRAP);

    private static Retrofit retrofit = null;

    public static Retrofit getTmdbV3Client() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(TMDB_BASE_URL_V3)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }




    public static Retrofit getTmdbV4Client() {

//        OkHttpClient httpClient = new OkHttpClient();
//
//        httpClient.interceptors().add(new Interceptor() {
//            @Override
//            public Response intercept(Interceptor.Chain chain) throws IOException {
//                Request original = chain.request();
//
//                // Set authorization token here
//                Request.Builder requestBuilder = original.newBuilder()
//                        .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyM2ZiZmIzM2JjZGIzYjE4ZjJkYzY4YTM0MzgyNGEyMiIsInN1YiI6IjU4Zjg4YTUwYzNhMzY4NzVmMDAwNjc3NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.gAGNBXdD0Xf53WusYqEU4ioLAp0vCWmdUhyCjr8qqHo")
//                        .method(original.method(), original.body());
//
//                Request request = requestBuilder.build();
//                return chain.proceed(request);
//            }
//        });

//        Interceptor interceptor = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                Request.Builder requestBuilder = request.newBuilder()
//                        .addHeader("Authorization", " Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyM2ZiZmIzM2JjZGIzYjE4ZjJkYzY4YTM0MzgyNGEyMiIsInN1YiI6IjU4Zjg4YTUwYzNhMzY4NzVmMDAwNjc3NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.gAGNBXdD0Xf53WusYqEU4ioLAp0vCWmdUhyCjr8qqHo")
//                        .addHeader("Content-Type", " application/json;charset=utf-8")
//                        .method(request.method(), request.body());
//                Request newRequest = requestBuilder.build();
//
//                return chain.proceed(newRequest);
//            }
//
//        };
//
//
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addNetworkInterceptor(interceptor)
//                .build();

//        OkHttpClient.Builder client = new OkHttpClient.Builder();
//
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        client.addInterceptor(loggingInterceptor);

//        OkHttpClient.Builder client = new OkHttpClient.Builder()
//                // add our curl logger here
//                .addInterceptor(new CurlLoggerInterceptor("aaa"));

//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//// set your desired log level
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        OkHttpClient.Builder client = new OkHttpClient.Builder();
//// add your other interceptors …
//
//// add logging as last interceptor
//        client.addNetworkInterceptor(logging);

        Timber.plant(new Timber.DebugTree());

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.i(message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(TMDB_BASE_URL_V4)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;

    }
}
