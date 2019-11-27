package com.karbyshev.randomuser.data.datasource;


import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.karbyshev.randomuser.data.model.UserModel;
import com.karbyshev.randomuser.data.repository.Repository;

import io.reactivex.disposables.CompositeDisposable;

public class UserDataSourceFactory extends DataSource.Factory<Integer, UserModel.Results> {
    private static final String TAG = "UserDataSourceFactory";
    private Repository repository;
    private CompositeDisposable disposable;

    private MutableLiveData<UserDataSource> liveData;
    private UserDataSource userDataSource;

    public UserDataSourceFactory(Repository repository, CompositeDisposable disposable) {
        this.repository = repository;
        this.disposable = disposable;

        liveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource<Integer, UserModel.Results> create() {
        userDataSource = new UserDataSource(repository, disposable);
        liveData.postValue(userDataSource);
        return userDataSource;
    }

    public MutableLiveData<UserDataSource> getLiveData() {
        return liveData;
    }
}
