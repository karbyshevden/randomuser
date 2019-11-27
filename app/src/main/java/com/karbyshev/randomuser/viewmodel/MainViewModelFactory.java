package com.karbyshev.randomuser.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.karbyshev.randomuser.data.repository.Repository;


public class MainViewModelFactory implements ViewModelProvider.Factory {
    private static final String TAG = "MainViewModelFactory";
    private Repository repository;

    public MainViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
