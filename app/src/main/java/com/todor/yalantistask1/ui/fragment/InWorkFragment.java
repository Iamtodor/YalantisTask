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
import com.todor.yalantistask1.adapter.OnTheGoAdapter;
import com.todor.yalantistask1.interfaces.OnItemClickListener;
import com.todor.yalantistask1.model.Task;
import com.todor.yalantistask1.ui.activity.DetailsActivity;
import com.todor.yalantistask1.ui.activity.MainActivity;
import com.todor.yalantistask1.utils.Utils;

import java.util.List;

import butterknife.Bind;

public class InWorkFragment extends BaseFragment implements OnItemClickListener {

    private static final float TOOLBAR_ELEVATION = 14f;
    private static final int HIDE_THRESHOLD = 20;
    @Bind(R.id.recycler_view) protected RecyclerView recyclerView;
    @Bind(R.id.fab) protected FloatingActionButton fab;
    private List<Task> mTasks;
    private AppBarLayout mAppBar;
    private int scrolledDistance = 0;
    private boolean controlsVisible = true;

    @Override
    protected int getContentViewId() {
        return R.layout.in_work_fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        mTasks = Utils.getTasks();
        mAppBar = ((MainActivity) getActivity()).getAppBar();
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerView.setItemAnimator(itemAnimator);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new OnTheGoAdapter(getActivity(), mTasks, this));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            // Keeps track of the overall vertical offset in the list
            int verticalOffset;

            // Determines the scroll UP/DOWN direction
            boolean scrollingUp;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (scrollingUp) {
                        if (verticalOffset > mAppBar.getHeight()) {
                            toolbarAnimateHide();
                        } else {
                            toolbarAnimateShow(verticalOffset);
                        }
                    } else {
                        if (mAppBar.getTranslationY() < mAppBar.getHeight() * -0.6 && verticalOffset > mAppBar.getHeight()) {
                            toolbarAnimateHide();
                        } else {
                            toolbarAnimateShow(verticalOffset);
                        }
                    }
                }
            }

            @Override
            public final void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                verticalOffset += dy;
                scrollingUp = dy > 0;
                int toolbarYOffset = (int) (dy - mAppBar.getTranslationY());
                mAppBar.animate().cancel();
                if (scrollingUp) {
                    if (toolbarYOffset < mAppBar.getHeight()) {
                        if (verticalOffset > mAppBar.getHeight()) {
                            toolbarSetElevation(TOOLBAR_ELEVATION);
                        }
                        mAppBar.setTranslationY(-toolbarYOffset);
                    } else {
                        toolbarSetElevation(0);
                        mAppBar.setTranslationY(-mAppBar.getHeight());
                    }
                } else {
                    if (toolbarYOffset < 0) {
                        if (verticalOffset <= 0) {
                            toolbarSetElevation(0);
                        }
                        mAppBar.setTranslationY(0);
                    } else {
                        if (verticalOffset > mAppBar.getHeight()) {
                            toolbarSetElevation(TOOLBAR_ELEVATION);
                        }
                        mAppBar.setTranslationY(-toolbarYOffset);
                    }
                }

                if (scrolledDistance > HIDE_THRESHOLD && controlsVisible) {
                    hideFab();
                    controlsVisible = false;
                    scrolledDistance = 0;
                } else if (scrolledDistance < -HIDE_THRESHOLD && !controlsVisible) {
                    showFab();
                    controlsVisible = true;
                    scrolledDistance = 0;
                }

                if ((controlsVisible && dy > 0) || (!controlsVisible && dy < 0)) {
                    scrolledDistance += dy;
                }
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void toolbarSetElevation(float elevation) {
        // setElevation() only works on Lollipop
        if (Utils.isLollipop()) {
            mAppBar.setElevation(elevation);
        }
    }

    private void toolbarAnimateShow(final int verticalOffset) {
        mAppBar.animate()
                .translationY(0)
                .setInterpolator(new LinearInterpolator())
                .setDuration(180)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        toolbarSetElevation(verticalOffset == 0 ? 0 : TOOLBAR_ELEVATION);
                    }
                });
    }

    private void toolbarAnimateHide() {
        mAppBar.animate()
                .translationY(-mAppBar.getHeight())
                .setInterpolator(new LinearInterpolator())
                .setDuration(180)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        toolbarSetElevation(0);
                    }
                });
    }

    private void hideFab() {
        mAppBar.animate()
                .translationY(-mAppBar.getHeight())
                .setInterpolator(new AccelerateInterpolator(2));

        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
        int fabBottomMargin = lp.bottomMargin;
        fab.animate().translationY(fab.getHeight() + fabBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();
    }

    private void showFab() {
        mAppBar.animate().translationY(0).setInterpolator(new DecelerateInterpolator());
        fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
    }

    @Override
    public void onItemClick(int position) {
        startActivity(new Intent(getContext(), DetailsActivity.class));
    }
}
