package com.todor.yalantistask1.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.todor.yalantistask1.R;
import com.todor.yalantistask1.adapter.WorkAdapter;
import com.todor.yalantistask1.interfaces.OnItemClickListener;
import com.todor.yalantistask1.model.Task;
import com.todor.yalantistask1.utils.Utils;

import java.util.List;

import butterknife.Bind;

public class WaitFragment extends BaseFragment implements OnItemClickListener {

    @Bind(R.id.recycler_view) protected RecyclerView recyclerView;
    @Bind(R.id.fab) protected FloatingActionButton fab;
    private List<Task> mTasks;

    @Override
    protected int getContentViewId() {
        return R.layout.wait_fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTasks = Utils.getTasks();

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

    }
}
