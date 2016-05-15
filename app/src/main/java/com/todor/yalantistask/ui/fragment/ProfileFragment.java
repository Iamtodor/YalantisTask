package com.todor.yalantistask.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.todor.yalantistask.R;
import com.todor.yalantistask.model.User;
import com.todor.yalantistask.utils.CircleTransform;
import com.todor.yalantistask.utils.StrokeTransform;

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
        User user = mRealm.where(User.class).findFirst();
        Picasso.with(getContext())
                .load(user.getProfileIcon())
                .transform(new CircleTransform())
                .transform(new StrokeTransform())
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
