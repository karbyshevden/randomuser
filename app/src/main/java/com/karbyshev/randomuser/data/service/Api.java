package com.karbyshev.randomuser.data.service;

import com.karbyshev.randomuser.data.model.UserModel;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface Api {

    @GET("api/")
    Single<UserModel> getUserModel(@QueryMap Map<String, String> params);
}
