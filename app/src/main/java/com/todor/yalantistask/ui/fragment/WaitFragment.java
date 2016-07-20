package com.todor.yalantistask.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.todor.yalantistask.R;
import com.todor.yalantistask.adapter.WorkAdapter;
import com.todor.yalantistask.interfaces.OnItemClickListener;
import com.todor.yalantistask.model.Item;
import com.todor.yalantistask.network.API;
import com.todor.yalantistask.network.ApiService;

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

        setFabBehavior(recyclerView, fab);

        initRealm();
        ApiService apiService = new ApiService();
        API api = apiService.getApiService();

        api.getData("10,6")
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

    }
}
