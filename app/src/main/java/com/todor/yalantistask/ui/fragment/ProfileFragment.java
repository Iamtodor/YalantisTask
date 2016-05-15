package com.todor.yalantistask.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.todor.yalantistask.R;
import com.todor.yalantistask.model.User;
import com.todor.yalantistask.utils.CircleTransform;

import butterknife.Bind;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ProfileFragment extends BaseFragment {

    @Bind(R.id.profile_icon) protected ImageView userIcon;
    @Bind(R.id.profile_name) protected TextView userName;
    @Bind(R.id.profile_email) protected TextView userEmail;
    @Bind(R.id.profile_birthday) protected TextView userBirthday;

    private Realm mRealm;
    private RealmConfiguration mRealmConfig;

    @Override
    protected int getContentViewId() {
        return R.layout.profile_fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        initRealm();
        final User user = mRealm.where(User.class).findFirst();
        Glide.with(getContext())
                .load(user.getProfileIcon())
                .asBitmap()
                .transform(new CircleTransform(getContext()))
//                .transform(new RoundedCornersTransformation(getContext(), 150, 0))
                .into(userIcon);

        userName.setText(user.getName());
        userEmail.setText(user.getEmail());
        userBirthday.setText(user.getBirthday());
    }

    private void initRealm() {
        mRealmConfig = new RealmConfiguration.Builder(getContext()).build();
        mRealm = Realm.getInstance(mRealmConfig);
    }
}
