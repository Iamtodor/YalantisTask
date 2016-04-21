package com.todor.yalantistask1.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;
import com.todor.yalantistask1.R;
import com.todor.yalantistask1.adapter.OnTheWaitAdapter;
import com.todor.yalantistask1.model.Task;
import com.todor.yalantistask1.ui.activity.DetailsActivity;
import com.todor.yalantistask1.utils.Utils;

import java.util.List;

import butterknife.Bind;

public class OnTheWaitFragment extends BaseFragment {

    @Bind(R.id.list_view) protected ListView listView;
    @Bind(R.id.fab) protected FloatingActionButton fab;
    private List<Task> mTasks;
    private OnTheWaitAdapter mOnTheWaitAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.on_the_wait_fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTasks = Utils.getTasks();

        mOnTheWaitAdapter = new OnTheWaitAdapter(getContext(), mTasks);
        listView.setAdapter(mOnTheWaitAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getContext(), DetailsActivity.class));
            }
        });

        fab.attachToListView(listView);
    }
}
