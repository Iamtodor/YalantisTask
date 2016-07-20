package com.todor.yalantistask.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.todor.yalantistask.R;
import com.todor.yalantistask.adapter.WorkListAdapter;
import com.todor.yalantistask.interfaces.OnItemClickListener;
import com.todor.yalantistask.model.Item;
import com.todor.yalantistask.model.State;
import com.todor.yalantistask.model.Task;
import com.todor.yalantistask.network.API;
import com.todor.yalantistask.network.ApiService;
import com.todor.yalantistask.ui.activity.DetailsActivity;
import com.todor.yalantistask.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WorkFragment extends BaseFragment implements OnItemClickListener {

    @Bind(R.id.recycler_view) protected RecyclerView recyclerView;
    @Bind(R.id.fab) protected FloatingActionButton fab;
    private List<Task> mTasks;
    private Realm mRealm;
    private RealmConfiguration mRealmConfig;
    private List<Item> mFilteredItems;

    @Override
    protected int getContentViewId() {
        return R.layout.work_fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        mTasks = Utils.getTasks();
        initRealm();
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerView.setItemAnimator(itemAnimator);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        RealmResults<Item> results = mRealm.where(Item.class)
                .findAll();

        mFilteredItems = getFilteredResult(results);

        recyclerView.setAdapter(new WorkListAdapter(getActivity(), mFilteredItems, WorkFragment.this));

        setFabBehavior(recyclerView, fab);

        ApiService apiService = new ApiService();
        API api = apiService.getApiService();

        api.getData("0,9,5,7,8")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Item>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Item> items) {
                        mRealm.beginTransaction();
                        mRealm.copyToRealmOrUpdate(items);
                        mRealm.commitTransaction();

                        RealmResults<Item> results = mRealm.where(Item.class).findAll();
//                        realm.where(Item.class).equalTo("state.id", ids)

                        mFilteredItems = getFilteredResult(results);

                        recyclerView.setAdapter(new WorkListAdapter(getActivity(), mFilteredItems, WorkFragment.this));
                    }
                });
    }

    private List<Item> getFilteredResult(RealmResults<Item> results) {
        List<Item> filteredResult = new ArrayList<>();
        for(Item item : results) {
            State state = item.getState();
            if(state.getId() == 0 | state.getId() == 5 | state.getId() == 7 | state.getId() == 8 |
                    state.getId() == 9) {
                filteredResult.add(item);
            }
        }
        return filteredResult;
    }

    private void initRealm() {
        mRealmConfig = new RealmConfiguration.Builder(getContext()).build();
        mRealm = Realm.getInstance(mRealmConfig);
    }

    @Override
    public void onItemClick(int position) {
        startActivity(new Intent(getContext(), DetailsActivity.class));
    }
}
