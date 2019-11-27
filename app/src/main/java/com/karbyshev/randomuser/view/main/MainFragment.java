package com.karbyshev.randomuser.view.main;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.karbyshev.randomuser.R;
import com.karbyshev.randomuser.data.model.UserModel;
import com.karbyshev.randomuser.data.repository.Repository;
import com.karbyshev.randomuser.view.main.adapter.MainRecyclerAdapter;
import com.karbyshev.randomuser.view.main.adapter.RecyclerItemClickListener;
import com.karbyshev.randomuser.viewmodel.MainViewModel;
import com.karbyshev.randomuser.viewmodel.MainViewModelFactory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment implements RecyclerItemClickListener {
    private static final String TAG = "MainFragment";

    private MainViewModel mViewModel;
    private View mRootView;
    private MainActivity mActivity;

    @BindView(R.id.mainRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mainProgressBar)
    ProgressBar mProgressBar;

    private MainRecyclerAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mActivity = (MainActivity) getActivity();
        mRootView = inflater.inflate(R.layout.main_fragment, container, false);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.bind(this, mRootView);
        Repository repository = new Repository();
        mViewModel = ViewModelProviders
                .of(this, new MainViewModelFactory(repository))
                .get(MainViewModel.class);

        setupRecyclerView();
        bindUI();
    }

    private void setupRecyclerView(){
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MainRecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    private void bindUI() {
        mViewModel.getUserList().observe(this, userModel -> {
            mAdapter.addAll(userModel.getResults());
            progressBarIsVisible(false);
        });
    }

    private void progressBarIsVisible(boolean isVisible) {
        if (isVisible) {
            mProgressBar.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.INVISIBLE);
        } else {
            mProgressBar.setVisibility(View.INVISIBLE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(int position, List<UserModel.Results> list) {
        String user = new Gson().toJson(list.get(position));
        Bundle bundle = new Bundle();
        bundle.putString("user", user);
        mActivity.mNavController.navigate(R.id.profileFragment, bundle);
    }
}
