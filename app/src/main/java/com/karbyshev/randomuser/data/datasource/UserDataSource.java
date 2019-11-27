package com.karbyshev.randomuser.data.datasource;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.karbyshev.randomuser.data.model.UserModel;
import com.karbyshev.randomuser.data.repository.Repository;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class UserDataSource extends PageKeyedDataSource<Integer, UserModel.Results> {
    private static final String TAG = "UserDataSource";

    private Repository repository;
    private CompositeDisposable compositeDisposable;

    public UserDataSource(Repository repository, CompositeDisposable compositeDisposable) {
        this.repository = repository;
        this.compositeDisposable = compositeDisposable;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, UserModel.Results> callback) {
        Log.d(TAG, "loadInitial requestedLoadSize: " + params.requestedLoadSize);

        compositeDisposable.add(repository.getUsers(1, params.requestedLoadSize).subscribe(
                success -> {
                    callback.onResult(success.getResults(), null, 2);
                },
                error -> {
                    Log.e(TAG, "loadInitial error: " + error.getMessage());
                }
        ));
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, UserModel.Results> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, UserModel.Results> callback) {
        Log.d(TAG, "loadAfter Key: " + params.key + " Count: " + params.requestedLoadSize);


        Disposable disposable = repository.getUsers(params.key, params.requestedLoadSize).subscribe(
                success -> {
                    callback.onResult(success.getResults(), params.key + 1);
                },
                error -> {
                    Log.e(TAG, "loadAfter error: " + error.getMessage());
                }
        );

        compositeDisposable.add(disposable);
    }
}
