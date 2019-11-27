package com.karbyshev.randomuser.view.main.adapter;

import com.karbyshev.randomuser.data.model.UserModel;

import java.util.List;

public interface RecyclerItemClickListener {
    void onItemClick (int position, List<UserModel.Results> list);
}
