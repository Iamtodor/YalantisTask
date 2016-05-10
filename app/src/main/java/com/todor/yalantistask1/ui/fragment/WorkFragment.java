package com.todor.yalantistask1.ui.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.todor.yalantistask1.R;
import com.todor.yalantistask1.adapter.WorkAdapter;
import com.todor.yalantistask1.interfaces.OnItemClickListener;
import com.todor.yalantistask1.model.Task;
import com.todor.yalantistask1.ui.activity.DetailsActivity;
import com.todor.yalantistask1.ui.activity.MainActivity;
import com.todor.yalantistask1.utils.Utils;

import java.util.List;

import butterknife.Bind;

public class WorkFragment extends BaseFragment implements OnItemClickListener {

    @Bind(R.id.recycler_view) protected RecyclerView recyclerView;
    @Bind(R.id.fab) protected FloatingActionButton fab;
    private List<Task> mTasks;
    private AppBarLayout mAppBar;

    @Override
    protected int getContentViewId() {
        return R.layout.work_fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        mTasks = Utils.getTasks();
        mAppBar = ((MainActivity) getActivity()).getAppBar();
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerView.setItemAnimator(itemAnimator);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new WorkAdapter(getActivity(), mTasks, this));

        setFabBehavior();
    }

    private void setFabBehavior() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 && fab.isShown())
                    fab.hide();
                else if (dy < 0 && !fab.isShown())
                    fab.show();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        startActivity(new Intent(getContext(), DetailsActivity.class));
    }
}
