package com.karbyshev.randomuser.view.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.karbyshev.randomuser.R;
import com.karbyshev.randomuser.data.model.UserModel;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.karbyshev.randomuser.data.model.UserModel.Results.DIFF_CALLBACK;

public class UserPageListAdapter extends PagedListAdapter<UserModel.Results, RecyclerView.ViewHolder> {

    private RecyclerItemClickListener mListener;

    public void setOnItemClickListener(RecyclerItemClickListener mListener){
        this.mListener = mListener;
    }

    public UserPageListAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.user_item, parent, false);
        return new UserItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((UserItemViewHolder) holder).bindTo(getItem(position));
    }

    public class UserItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.userItemCircleImageView)
        CircleImageView mAvatarImageView;
        @BindView(R.id.userItemNameTextView)
        TextView mUserName;

        public UserItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> {
                if (mListener != null){
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        mListener.onItemClick(position, getCurrentList());
                    }
                }
            });
        }

        public void bindTo(UserModel.Results user) {
            String userName = user.getName().getFirst() + " " + user.getName().getLast();
            String avatarImageUrl = user.getPicture().getThumbnail();

            mUserName.setText(userName);
            Picasso.get().load(avatarImageUrl).into(mAvatarImageView);
        }
    }
}
