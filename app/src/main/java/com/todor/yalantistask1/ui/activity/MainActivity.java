package com.todor.yalantistask1.ui.activity;

import android.support.design.widget.AppBarLayout;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.todor.yalantistask1.R;
import com.todor.yalantistask1.adapter.ViewPagerAdapter;
import com.todor.yalantistask1.ui.fragment.DoneFragment;
import com.todor.yalantistask1.ui.fragment.WorkFragment;
import com.todor.yalantistask1.ui.fragment.WaitFragment;

import butterknife.Bind;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String IT_RUH_DNIPRO = "http://www.itruh.dp.ua/";
    public static final String YALANTIS = "https://yalantis.com/";
    @Bind(R.id.made_by) protected TextView footerMadeBy;
    @Bind(R.id.toolbar_container) protected AppBarLayout appBarLayout;
    @Bind(R.id.toolbar) protected Toolbar toolbar;
    @Bind(R.id.drawer_layout) protected DrawerLayout drawer;
    @Bind(R.id.nav_view) protected NavigationView navigationView;
    @Bind(R.id.viewpager) protected ViewPager viewPager;
    @Bind(R.id.tabs) protected TabLayout tabLayout;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);

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
            case R.id.log_in:
                // TODO: 22.04.16 log in
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

        return super.onOptionsItemSelected(item);
    }

    public AppBarLayout getAppBar() {
        return appBarLayout;
    }
}
