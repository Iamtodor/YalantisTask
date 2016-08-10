package com.todor.yalantistask.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.todor.yalantistask.R;
import com.todor.yalantistask.adapter.ViewPagerAdapter;

import butterknife.Bind;

public class AllRequestsFragment extends BaseFragment {

    @Bind(R.id.viewPager) protected ViewPager viewPager;
    @Bind(R.id.tabs) protected TabLayout tabLayout;

    @Override
    protected int getContentViewId() {
        return R.layout.all_requests_fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new WorkFragment(), "Work");
        adapter.addFragment(new DoneFragment(), "Done");
        adapter.addFragment(new WaitFragment(), "Wait");
        viewPager.setAdapter(adapter);
    }
}
