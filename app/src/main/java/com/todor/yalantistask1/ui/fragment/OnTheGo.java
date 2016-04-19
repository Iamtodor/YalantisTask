package com.todor.yalantistask1.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.todor.yalantistask1.R;

import butterknife.Bind;

public class OnTheGo extends BaseFragment {

    @Bind(R.id.recycler_view) protected RecyclerView recyclerView;

    @Override
    protected int getContentViewId() {
        return R.layout.on_the_go;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
