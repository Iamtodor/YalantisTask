package com.todor.yalantistask.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.todor.yalantistask.R;
import com.todor.yalantistask.adapter.WorkAdapter;
import com.todor.yalantistask.interfaces.OnItemClickListener;
import com.todor.yalantistask.model.Item;
import com.todor.yalantistask.network.API;
import com.todor.yalantistask.network.ApiService;
import com.todor.yalantistask.ui.activity.DetailsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static io.realm.Realm.*;

public class WaitFragment extends BaseFragment implements OnItemClickListener {

    @Bind(R.id.recycler_view) protected RecyclerView recyclerView;
    @Bind(R.id.fab) protected FloatingActionButton fab;
    private RealmConfiguration mRealmConfig;
    private Realm mRealm;

    @Override
    protected int getContentViewId() {
        return R.layout.wait_fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        initRealm();

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerView.setItemAnimator(itemAnimator);

        RealmResults<Item> modelFromDB = mRealm.where(Item.class).findAll();
        List<Item> modelForAdapter = new ArrayList<>();

        for(Item item : modelFromDB) {
            if(item.getState().getId() == 1 |item.getState().getId() == 3 |item.getState().getId() == 4) {
                modelForAdapter.add(item);
            }
        }

        recyclerView.setAdapter(new WorkAdapter(getActivity(), modelForAdapter, this));
        setFabBehavior(recyclerView, fab);

        ApiService apiService = new ApiService();
        API api = apiService.getApiService();

        api.getData("1,3,4")
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
                    public void onNext(final List<Item> items) {
                        mRealm.executeTransaction(realm -> realm.copyToRealmOrUpdate(items));
                        mRealm.close();

                        RealmResults<Item> results = mRealm.where(Item.class).findAll();
                        recyclerView.setAdapter(new WorkAdapter(getActivity(), results, WaitFragment.this));
                    }
                });
    }

    private void initRealm() {
        mRealmConfig = new RealmConfiguration.Builder(getContext()).build();
        mRealm = getInstance(mRealmConfig);
    }

    @Override
    public void onItemClick(int position) {
        startActivity(new Intent(getContext(), DetailsActivity.class));
    }
}
