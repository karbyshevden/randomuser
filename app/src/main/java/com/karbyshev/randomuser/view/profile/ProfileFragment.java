package com.karbyshev.randomuser.view.profile;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.karbyshev.randomuser.R;
import com.karbyshev.randomuser.data.model.UserModel;
import com.karbyshev.randomuser.view.main.MainActivity;
import com.squareup.picasso.Picasso;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    private View mRootView;
    private MainActivity mActivity;
    private UserModel.Results user;

    @BindView(R.id.detailCircleImageView)
    CircleImageView mImageView;
    @BindView(R.id.detailUerNameTextView)
    TextView mName;
    @BindView(R.id.detailUerAgeTextView)
    TextView mAge;
    @BindView(R.id.detailUerPhoneTextView)
    TextView mCellPhone;
    @BindView(R.id.detailUerEmailTextView)
    TextView mEmail;
    @BindView(R.id.detailUerSkypeTextView)
    TextView mSkype;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mActivity = (MainActivity) getActivity();
        mRootView = inflater.inflate(R.layout.fragment_profile, container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, mRootView);

        if (getArguments() != null) {
            String object = getArguments().getString("user", "");
            user = new Gson().fromJson(object, UserModel.Results.class);

            mCellPhone.setOnClickListener(v -> mActivity.dialPhoneNumber(user.getCell()));
            bindUI();
        }
    }

    private void bindUI(){
        String name = user.getName().getFirst() + " " + user.getName().getLast();
        String age = user.getDob().getDate();
        String skype = user.getLocation().getCity() + ", " + user.getLocation().getCountry();
        String imgeUrl = user.getPicture().getMedium();

        mName.setText(name);
        mAge.setText(getFormatDate(age));
        mCellPhone.setText(user.getCell());
        mEmail.setText(user.getEmail());
        mSkype.setText(skype);

        Picasso.get().load(imgeUrl).into(mImageView);


    }

    private String getFormatDate(String day) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        SimpleDateFormat newDateFormat = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault()); //Wrong format given in the task YYYY-mm-dd
        String result = "Happy Birthday :)";
        Date date;
        try {
            date = dateFormat.parse(day);
            result = newDateFormat.format(date != null ? date : new Date());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
