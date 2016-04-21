package com.todor.yalantistask1.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.todor.yalantistask1.R;
import com.todor.yalantistask1.adapter.OnTheGoAdapter;
import com.todor.yalantistask1.interfaces.OnItemClickListener;
import com.todor.yalantistask1.model.Task;
import com.todor.yalantistask1.ui.activity.DetailsActivity;
import com.todor.yalantistask1.utils.HidingScrollListener;
import com.todor.yalantistask1.utils.Utils;

import java.util.List;

import butterknife.Bind;

public class InWorkFragment extends BaseFragment implements OnItemClickListener {

    @Bind(R.id.recycler_view) protected RecyclerView recyclerView;
    @Bind(R.id.fab) protected FloatingActionButton fab;
    private List<Task> mTasks;

    @Override
    protected int getContentViewId() {
        return R.layout.in_work_fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        mTasks = Utils.getTasks();

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerView.setItemAnimator(itemAnimator);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new OnTheGoAdapter(getActivity(), mTasks, this));
        recyclerView.addOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                hideFab();
            }

            @Override
            public void onShow() {
                showFab();
            }
        });
    }

    private void hideFab() {
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
        int fabBottomMargin = lp.bottomMargin;
        fab.animate().translationY(fab.getHeight() + fabBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();
    }

    private void showFab() {
        fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
    }

    @Override
    public void onItemClick(int position) {
        startActivity(new Intent(getContext(), DetailsActivity.class));
    }
}
