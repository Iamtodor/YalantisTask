package com.todor.yalantistask.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.todor.yalantistask.R;
import com.todor.yalantistask.interfaces.ToolbarListener;
import com.todor.yalantistask.model.CurrentUser;
import com.todor.yalantistask.model.User;
import com.todor.yalantistask.ui.fragment.AllRequestsFragment;
import com.todor.yalantistask.ui.fragment.ProfileFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import butterknife.Bind;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, FacebookCallback<LoginResult>,
        ToolbarListener{

    private static final String IT_RUH_DNIPRO = "http://www.itruh.dp.ua/";
    private static final String YALANTIS = "https://yalantis.com/";
    private static final String EMAIL = "email";
    private static final String BIRTHDAY = "birthday";
    private static final String NAME = "name";
    private static final String FACEBOOK_URL = "https://graph.facebook.com/";
    private static final String PICTURE_TYPE_LARGE = "/picture?type=large";
    private static final String URL = "url";

    @Bind(R.id.made_by) protected TextView footerMadeBy;
    @Bind(R.id.toolbar) protected Toolbar toolbar;
    @Bind(R.id.drawer_layout) protected DrawerLayout drawer;
    @Bind(R.id.nav_view) protected NavigationView navigationView;

    private CallbackManager mCallbackManager;
    private CurrentUser mUser;
    private Realm mRealm;
    private RealmConfiguration mRealmConfig;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);
        FacebookSdk.sdkInitialize(this);
        initRealm();

        mCallbackManager = CallbackManager.Factory.create();

        if(savedInstanceState == null) {
            getSupportActionBar().setTitle(R.string.all_requests);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content, new AllRequestsFragment());
            transaction.commit();
        }

        LoginManager.getInstance().registerCallback(mCallbackManager, this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        setColor(footerMadeBy, footerMadeBy.getText().toString());
        setLoginLogoutViews();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (id) {
            case R.id.all_handling:
                getSupportActionBar().setTitle(R.string.all_requests);
                transaction.replace(R.id.content, new AllRequestsFragment());
                break;
            case R.id.map_handling:
                // TODO: 22.04.16 map requests
                getSupportActionBar().setTitle(R.string.map_requests);
                break;
            case R.id.profile:
                getSupportActionBar().setTitle(R.string.profile);
                transaction.replace(R.id.content, new ProfileFragment());
                break;
            case R.id.log_in:
                loginLogout();
                break;
        }
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.some_item) {
            // TODO: 22.04.16 handle click on some item
            return true;
        }

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSuccess(final LoginResult loginResult) {
        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                (jsonUser, response) -> {
                    try {
                        saveCurrentUser(jsonUser, loginResult);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender,birthday");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    public void onCancel() {
        snackbar(toolbar, R.string.login_cancel);
    }

    @Override
    public void onError(FacebookException error) {
        toast(error.getMessage());
    }

    private void initRealm() {
        mRealmConfig = new RealmConfiguration.Builder(MainActivity.this).build();
        mRealm = Realm.getInstance(mRealmConfig);
    }

    private void setColor(TextView view, String fulltext) {
        view.setText(fulltext, TextView.BufferType.SPANNABLE);
        Spannable str = (Spannable) view.getText();
        str.setSpan(new UnderlineSpan(), 0, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        str.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.progress_color)), 0, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        str.setSpan(new UnderlineSpan(), 18, fulltext.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        str.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.progress_color)), 18, fulltext.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        ClickableSpan span1 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                startWebViewActivity(IT_RUH_DNIPRO);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(ContextCompat.getColor(MainActivity.this, R.color.progress_color));
            }
        };

        ClickableSpan span2 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                startWebViewActivity(YALANTIS);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(ContextCompat.getColor(MainActivity.this, R.color.progress_color));
            }
        };

        str.setSpan(span1, 0, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        str.setSpan(span2, 18, fulltext.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        footerMadeBy.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void startWebViewActivity(String url) {
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        intent.putExtra(URL, url);
        startActivity(intent);
    }

    private void loginLogout() {
        if (isCurrentUserExists()) {
            LoginManager.getInstance().logOut();
            mRealm.beginTransaction();
            mRealm.delete(User.class);
            mRealm.commitTransaction();
        } else {
            LoginManager.getInstance().logInWithReadPermissions(this,
                    Arrays.asList("public_profile", "user_friends", "email", "user_birthday"));
        }
        setLoginLogoutViews();
    }

    private void setLoginLogoutViews() {
        Menu navigationMenu = navigationView.getMenu();
        MenuItem loginLogout = navigationMenu.findItem(R.id.log_in);
        if (isCurrentUserExists()) {
            navigationMenu.findItem(R.id.profile).setVisible(true);
            loginLogout.setTitle(R.string.log_out);
        } else {
            navigationMenu.findItem(R.id.profile).setVisible(false);
            loginLogout.setTitle(R.string.log_in);
        }
    }

    private boolean isCurrentUserExists() {
        CurrentUser user = mRealm.where(CurrentUser.class).findFirst();
        return user != null && !TextUtils.isEmpty(user.getToken());
    }

    private void saveCurrentUser(JSONObject object, LoginResult loginResult) throws JSONException {
        String profileIconUrl = FACEBOOK_URL + loginResult.getAccessToken().getUserId() + PICTURE_TYPE_LARGE;
        String token = loginResult.getAccessToken().getToken();

        mRealm.beginTransaction();

        mUser = new CurrentUser();
        mUser.setEmail(object.getString(EMAIL));
        mUser.setBirthday(object.getString(BIRTHDAY));
        mUser.setName(object.getString(NAME));

        mUser.setProfileIcon(profileIconUrl);
        mUser.setToken(token);
        mRealm.copyToRealmOrUpdate(mUser);
        mRealm.commitTransaction();
        setLoginLogoutViews();
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

}
