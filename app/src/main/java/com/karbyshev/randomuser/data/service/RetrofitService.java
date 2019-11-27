package com.karbyshev.randomuser.data.service;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private final static long TIMEOUT = 10L;
    private static final String BASE_URL = "https://randomuser.me/";

    private static Api apiImpl;

    public static Api getApi() {
        return apiImpl;
    }

    public static void init(){
        apiImpl = createRetrofit().create(Api.class);
    }

    private static OkHttpClient createOkHttpClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient.Builder httpClient =
                new OkHttpClient.Builder()
                        .addInterceptor(interceptor)
                        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                        .readTimeout(TIMEOUT, TimeUnit.SECONDS);
        return httpClient.build();
    }

    private static Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(createOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
