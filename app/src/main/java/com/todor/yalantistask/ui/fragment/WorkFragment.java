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
import com.todor.yalantistask.model.ItemDAO;
import com.todor.yalantistask.network.API;
import com.todor.yalantistask.network.ApiService;
import com.todor.yalantistask.ui.activity.DetailsActivity;

import java.util.List;

import butterknife.Bind;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WorkFragment extends BaseFragment implements OnItemClickListener {

    @Bind(R.id.recycler_view) protected RecyclerView recyclerView;
    @Bind(R.id.fab) protected FloatingActionButton fab;
    private List<Item> mFilteredItems;
    private WorkAdapter adapter;

    @Override
    protected int getContentViewId() {
        return R.layout.work_fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerView.setItemAnimator(itemAnimator);

//        RealmResults<Item> modelFromDB = mRealm.where(Item.class).findAll();
//        List<Item> modelForAdapter = new ArrayList<>();
//
//        for(Item item : modelFromDB) {
//            if(item.getState().getId() == 0 |item.getState().getId() == 5 |item.getState().getId() == 7 |
//                    item.getState().getId() == 8 |item.getState().getId() == 9) {
//                modelForAdapter.add(item);
//            }
//        }

//        List<Item> items = ItemDAO.getItemsForDone();

//        adapter = new WorkAdapter(getActivity(), items, this);
//        recyclerView.setAdapter(adapter);

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
                    public void onNext(final List<Item> items) {
                        List<Item> results = ItemDAO.saveItems(items);
                        Log.d(TAG, "onNext: " + results);
                        recyclerView.setAdapter(new WorkAdapter(results, WorkFragment.this));
                    }
                });
    }

    @Override
    public void onItemClick(int position) {
        startActivity(new Intent(getContext(), DetailsActivity.class));
    }
}
