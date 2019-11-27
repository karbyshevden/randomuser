package com.karbyshev.randomuser.data.repository;

import android.util.Log;

import com.karbyshev.randomuser.data.model.UserModel;
import com.karbyshev.randomuser.data.service.RetrofitService;

import java.util.HashMap;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Repository {
    private static final String TAG = "Repository";
    private final static String PARAM_PAGE = "page";
    private static final String PARAM_SIZE = "results";

    public static Single<UserModel> getUsers(int page, int pageSize) {
        HashMap<String, String> map = new HashMap<>();
        map.put(PARAM_PAGE, Integer.toString(page));
        map.put(PARAM_SIZE, Integer.toString(pageSize));

        return RetrofitService.getApi().getUserModel(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
