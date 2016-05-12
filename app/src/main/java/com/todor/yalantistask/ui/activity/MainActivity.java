package com.todor.yalantistask.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.todor.yalantistask.Prefs;
import com.todor.yalantistask.R;
import com.todor.yalantistask.adapter.ViewPagerAdapter;
import com.todor.yalantistask.model.User;
import com.todor.yalantistask.ui.fragment.DoneFragment;
import com.todor.yalantistask.ui.fragment.ProfileFragment;
import com.todor.yalantistask.ui.fragment.WaitFragment;
import com.todor.yalantistask.ui.fragment.WorkFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import butterknife.Bind;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.made_by) protected TextView footerMadeBy;
    @Bind(R.id.toolbar_container) protected AppBarLayout appBarLayout;
    @Bind(R.id.toolbar) protected Toolbar toolbar;
    @Bind(R.id.drawer_layout) protected DrawerLayout drawer;
    @Bind(R.id.nav_view) protected NavigationView navigationView;
    @Bind(R.id.viewpager) protected ViewPager viewPager;
    @Bind(R.id.tabs) protected TabLayout tabLayout;

    public static final String IT_RUH_DNIPRO = "http://www.itruh.dp.ua/";
    public static final String YALANTIS = "https://yalantis.com/";

    private CallbackManager callbackManager;
    private Prefs prefs;
    private User user;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);
        FacebookSdk.sdkInitialize(this);

        callbackManager = CallbackManager.Factory.create();
        prefs = new Prefs(MainActivity.this);

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        prefs.saveFacebookToken(loginResult.getAccessToken().getToken());
                        user = new User();
                        final String profileIconUrl = "https://graph.facebook.com/" + loginResult.getAccessToken().getUserId() + "/picture?type=large";
                        prefs.saveProfileIcon(profileIconUrl);
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        try {
                                            user.setEmail(object.getString("email"));
                                            user.setBirthday(object.getString("birthday"));
                                            user.setName(object.getString("name"));
                                            user.setProfileIcon(profileIconUrl);
                                            prefs.saveCurrentUser(user);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,gender,birthday");
                        request.setParameters(parameters);
                        request.executeAsync();

                        setLoginLogoutText();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(MainActivity.this, "Login Cancel", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });


//        if (savedInstanceState == null) {
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.content, new InWorkFragment());
//            transaction.commit();
//            navigationView.setCheckedItem(R.id.all_handling);
//            getSupportActionBar().setTitle(R.string.all_requests);
//        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        setColor(footerMadeBy, footerMadeBy.getText().toString());
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.all_handling:
                // TODO: 22.04.16 all requests
                getSupportActionBar().setTitle(R.string.all_requests);
                break;
            case R.id.map_handling:
                // TODO: 22.04.16 map requests
                getSupportActionBar().setTitle(R.string.map_requests);
                break;
            case R.id.profile:
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new ProfileFragment());
                transaction.commit();
                break;
            case R.id.log_in:
                if (prefs.isFacebookTokenExists()) {
                    prefs.clearAll();
                    LoginManager.getInstance().logOut();
                } else {
                    LoginManager.getInstance().logInWithReadPermissions(this,
                            Arrays.asList("public_profile", "user_friends", "email", "user_birthday"));
                }
                setLoginLogoutText();
                break;
        }

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
        callbackManager.onActivityResult(requestCode, resultCode, data);
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

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new WorkFragment(), "On the go");
        adapter.addFragment(new DoneFragment(), "Done");
        adapter.addFragment(new WaitFragment(), "On the wait");
        viewPager.setAdapter(adapter);
    }

    private void startWebViewActivity(String url) {
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    private void setLoginLogoutText() {
        Menu navigationMenu = navigationView.getMenu();
        MenuItem loginLogout = navigationMenu.findItem(R.id.log_in);
        if (prefs.isFacebookTokenExists()) {
            loginLogout.setTitle("Log out");
        } else {
            loginLogout.setTitle("Log in");
        }
    }
}
