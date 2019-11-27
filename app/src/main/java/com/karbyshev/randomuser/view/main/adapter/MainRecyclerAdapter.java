package com.karbyshev.randomuser.view.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.karbyshev.randomuser.R;
import com.karbyshev.randomuser.data.model.UserModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private List<UserModel.Results> userList = new ArrayList<>();
    private RecyclerItemClickListener mListener;
    private boolean isLoading = false;

    public void setOnItemClickListener(RecyclerItemClickListener mListener){
        this.mListener = mListener;
    }

    public void setLoading(boolean isLoading) {
        boolean oldValue = this.isLoading;
        this.isLoading = isLoading;

        if (!oldValue && isLoading) {
            notifyItemInserted(userList.size());
        }
        if (oldValue && !isLoading) {
            notifyItemRemoved(userList.size());
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        if (viewType == VIEW_TYPE_ITEM) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
//            return new UserViewHolder(view);
//        } else if (viewType == VIEW_TYPE_LOADING) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_item, parent, false);
//            return new LoadingViewHolder(view);
//        }
//        return null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.userItemCircleImageView)
        CircleImageView mAvatarImageView;
        @BindView(R.id.userItemNameTextView)
        TextView mUserName;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> {
                if (mListener != null){
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        mListener.onItemClick(position, userList);
                    }
                }
            });
        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.progressBarItem)
        ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UserViewHolder) {
            UserModel.Results user = userList.get(position);

            UserViewHolder userViewHolder = (UserViewHolder) holder;

            String userName = user.getName().getFirst() + " " + user.getName().getLast();
            String avatarImageUrl = user.getPicture().getThumbnail();

            userViewHolder.mUserName.setText(userName);
            Picasso.get().load(avatarImageUrl).into(userViewHolder.mAvatarImageView);
        } else if (holder instanceof LoadingViewHolder){
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoading && position == userList.size()) {
            return VIEW_TYPE_LOADING;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return userList.size() + (isLoading ? 1 : 0);
    }

    public void addAll(List<UserModel.Results> list){
        userList.addAll(list);
        notifyDataSetChanged();
    }

    public List<UserModel.Results> getUserList() {
        return userList;
    }

    public void deleteAll(){
        userList.clear();
        notifyDataSetChanged();
    }
}
