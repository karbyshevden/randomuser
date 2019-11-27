package com.karbyshev.randomuser.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.karbyshev.randomuser.data.model.UserModel;
import com.karbyshev.randomuser.data.repository.Repository;
import com.karbyshev.randomuser.data.datasource.UserDataSourceFactory;

import io.reactivex.disposables.CompositeDisposable;

public class MainViewModel extends ViewModel {
    private static final String TAG = "MainViewModel";

    private CompositeDisposable compositeDisposable;
    private LiveData<PagedList<UserModel.Results>> pagedListLiveData;
    private Repository repository;

    public MainViewModel(Repository repository) {
        this.repository = repository;
        compositeDisposable = new CompositeDisposable();
        init();
    }

    private void init() {
        UserDataSourceFactory dataSourceFactory = new UserDataSourceFactory(repository, compositeDisposable);

        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(20)
                .build();

        pagedListLiveData = new LivePagedListBuilder(dataSourceFactory, config).build();
    }

    public LiveData<PagedList<UserModel.Results>> getPagedListLiveData() {
        return pagedListLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
