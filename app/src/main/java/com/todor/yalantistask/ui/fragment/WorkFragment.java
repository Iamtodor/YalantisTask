package com.todor.yalantistask.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.todor.yalantistask.R;
import com.todor.yalantistask.adapter.WorkAdapter;
import com.todor.yalantistask.interfaces.OnItemClickListener;
import com.todor.yalantistask.model.Example;
import com.todor.yalantistask.model.Task;
import com.todor.yalantistask.network.API;
import com.todor.yalantistask.network.ApiService;
import com.todor.yalantistask.ui.activity.DetailsActivity;
import com.todor.yalantistask.utils.Utils;

import java.util.List;

import butterknife.Bind;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WorkFragment extends BaseFragment implements OnItemClickListener {

    @Bind(R.id.recycler_view) protected RecyclerView recyclerView;
    @Bind(R.id.fab) protected FloatingActionButton fab;
    private List<Task> mTasks;

    @Override
    protected int getContentViewId() {
        return R.layout.work_fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        mTasks = Utils.getTasks();
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerView.setItemAnimator(itemAnimator);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new WorkAdapter(getActivity(), mTasks, this));

        setFabBehavior(recyclerView, fab);

        ApiService apiService = new ApiService();
        API api = apiService.getApiService();

        api.getTickets("0")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Example>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Example> examples) {

                    }
                });

//        api.getTickets("0")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<List<Example>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(List<Example> examples) {
//                        Log.d(TAG, "onNext: " + examples);
//                    }
//                });
    }

    @Override
    public void onItemClick(int position) {
        startActivity(new Intent(getContext(), DetailsActivity.class));
    }
}
