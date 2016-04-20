package com.todor.yalantistask1.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.todor.yalantistask1.R;
import com.todor.yalantistask1.adapter.OnTheGoAdapter;
import com.todor.yalantistask1.interfaces.OnItemClickListener;
import com.todor.yalantistask1.model.Task;
import com.todor.yalantistask1.utils.HidingScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class OnTheGoFragment extends BaseFragment implements OnItemClickListener {

    @Bind(R.id.recycler_view) protected RecyclerView recyclerView;
    @Bind(R.id.fab) protected FloatingActionButton fab;

    @Override
    protected int getContentViewId() {
        return R.layout.on_the_go_fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            task.setAddress("address " + i);
            task.setDate("date " + i);
            task.setExpiredTime("exp date " + i);
            task.setHeader("header " + i);
            task.setImgUrl("img " + i);
            task.setLikeValue(String.valueOf(i));
            tasks.add(task);
        }

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerView.setItemAnimator(itemAnimator);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new OnTheGoAdapter(getActivity(), tasks, this));
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
        Log.d(TAG, "onItemClick: " + position);
    }
}
