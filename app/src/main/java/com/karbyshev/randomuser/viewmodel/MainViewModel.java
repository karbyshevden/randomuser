package com.karbyshev.randomuser.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.karbyshev.randomuser.data.model.UserModel;
import com.karbyshev.randomuser.data.repository.Repository;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

public class MainViewModel extends ViewModel {
    private static final String TAG = "MainViewModel";
    private static final int PAGE_SIZE = 50;

    private Disposable disposable = Disposables.empty();
    private MutableLiveData<UserModel> liveData;
    private Repository repository;

    public MainViewModel(Repository repository) {
        this.repository = repository;
        liveData = new MutableLiveData<>();
    }

    public MutableLiveData<UserModel> getUserList() {
        disposable = Repository.getUsers(PAGE_SIZE).subscribe(
                model -> liveData.postValue(model),
                error -> liveData.postValue(null));
        return liveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
