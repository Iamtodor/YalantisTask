package com.todor.yalantistask1.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.todor.yalantistask1.R;
import com.todor.yalantistask1.adapter.OnTheGoAdapter;
import com.todor.yalantistask1.interfaces.OnItemClickListener;
import com.todor.yalantistask1.model.Task;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class OnTheGo extends BaseFragment implements OnItemClickListener {

    @Bind(R.id.recycler_view) protected RecyclerView recyclerView;

    @Override
    protected int getContentViewId() {
        return R.layout.on_the_go;
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
            task.setLikeValue("like " + i);
            tasks.add(task);
        }

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerView.setItemAnimator(itemAnimator);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new OnTheGoAdapter(getActivity(), tasks, this));
    }

    @Override
    public void onImageClick(int position) {
        Log.d(TAG, "onImageClick: " + position);
    }
}
