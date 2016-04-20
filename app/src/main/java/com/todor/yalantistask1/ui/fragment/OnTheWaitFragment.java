package com.todor.yalantistask1.ui.fragment;

import android.support.design.widget.FloatingActionButton;
import android.widget.ListView;

import com.todor.yalantistask1.R;

import butterknife.Bind;

public class OnTheWaitFragment extends BaseFragment {

    @Bind(R.id.list_view) protected ListView listView;
    @Bind(R.id.fab) protected FloatingActionButton fab;

    @Override
    protected int getContentViewId() {
        return R.layout.on_the_wait_fragment;
    }
}
