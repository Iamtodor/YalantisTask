package com.todor.yalantistask.ui.fragment;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.todor.yalantistask.R;
import com.todor.yalantistask.adapter.ViewPagerAdapter;
import com.todor.yalantistask.interfaces.ToolbarListener;
import com.todor.yalantistask.utils.AbstractTabSelectedListener;

import butterknife.Bind;

public class AllRequestsFragment extends BaseFragment {

    @Bind(R.id.viewPager) protected ViewPager viewPager;
    @Bind(R.id.tabs) protected TabLayout tabLayout;
    private ToolbarListener toolbarListener;
    private Toolbar toolbar;

    @Override
    protected int getContentViewId() {
        return R.layout.all_requests_fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        toolbarListener = (ToolbarListener) context;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        toolbar = toolbarListener.getToolbar();

        setTheme(0, getColorForTab(0));

        tabLayout.addOnTabSelectedListener(new AbstractTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int colorFrom = ((ColorDrawable) toolbar.getBackground()).getColor();
                int colorTo = getColorForTab(tab.getPosition());
                setTheme(colorFrom, colorTo);
            }
        });

    }

    private void setTheme(int colorFrom, int colorTo) {
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(),
                colorFrom, colorTo);

        valueAnimator.addUpdateListener(animation -> {
            int color = (int) animation.getAnimatedValue();

            toolbar.setBackgroundColor(color);
            tabLayout.setBackgroundColor(color);

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getActivity().getWindow().setStatusBarColor(color);
            }
        });

        valueAnimator.setDuration(250);
        valueAnimator.start();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new WorkFragment(), "Work");
        adapter.addFragment(new DoneFragment(), "Done");
        adapter.addFragment(new WaitFragment(), "Wait");
        viewPager.setAdapter(adapter);
    }

    private int getColorForTab(int position) {
        if(position == 0) {
            return ContextCompat.getColor(getContext(), R.color.purple);
        } else if (position == 1) {
            return ContextCompat.getColor(getContext(), R.color.red);
        } else if(position == 2) {
            return ContextCompat.getColor(getContext(), R.color.light_blue);
        } else {
            throw new RuntimeException("The tab doesn't exist");
        }
    }

}
