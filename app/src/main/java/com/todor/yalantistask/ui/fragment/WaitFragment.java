package com.todor.yalantistask.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.todor.yalantistask.R;
import com.todor.yalantistask.interfaces.OnItemClickListener;
import com.todor.yalantistask.model.Task;
import com.todor.yalantistask.network.API;
import com.todor.yalantistask.network.ApiService;
import com.todor.yalantistask.utils.Utils;

import java.util.List;

import butterknife.Bind;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class WaitFragment extends BaseFragment implements OnItemClickListener {

    @Bind(R.id.recycler_view) protected RecyclerView recyclerView;
    @Bind(R.id.fab) protected FloatingActionButton fab;
    private List<Task> mTasks;
    private RealmConfiguration mRealmConfig;
    private Realm mRealm;

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
//        recyclerView.setAdapter(new WorkAdapter(getActivity(), mTasks, this));

        setFabBehavior(recyclerView, fab);

        initRealm();
        ApiService apiService = new ApiService();
        API api = apiService.getApiService();

//        api.getData("10,6")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<List<Item>>() {
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
//                    public void onNext(List<Item> items) {
//                        mRealm.beginTransaction();
//                        mRealm.copyToRealmOrUpdate(items);
//                        mRealm.commitTransaction();
//
//                        RealmResults<Item> results = mRealm.where(Item.class).findAll();
//                        Log.d(TAG, "wait fragment: " + results.size());
//                        for(int i = 0; i < results.size(); i++) {
//                            Log.d(TAG, "wait fragment: " + results.get(i));
//                        }
//                    }
//                });
    }

    private void initRealm() {
        mRealmConfig = new RealmConfiguration.Builder(getContext()).build();
        mRealm = Realm.getInstance(mRealmConfig);
    }

    @Override
    public void onItemClick(int position) {

    }
}
