package com.todor.yalantistask1.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.todor.yalantistask1.R;
import com.todor.yalantistask1.utils.NetworkUtils;
import com.todor.yalantistask1.utils.Utils;
import com.todor.yalantistask1.adapter.ItemDecorator;
import com.todor.yalantistask1.adapter.ImageTaskRecyclerViewAdapter;
import com.todor.yalantistask1.interfaces.OnItemClickListener;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.organization) TextView organization;
    @Bind(R.id.problem_status) TextView problemStatus;
    @Bind(R.id.create_date) TextView createdDate;
    @Bind(R.id.registered_date) TextView registeredDate;
    @Bind(R.id.solve_date) TextView solveDate;
    @Bind(R.id.responsible_organ) TextView responsibleOrgan;
    @Bind(R.id.problem_description) TextView problemDescription;
    @Bind(R.id.recycler_view) RecyclerView recyclerView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupToolbar();
        setupView();
        setupRecyclerView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
        }
        return false;
    }

    public void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.toolbar_title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void setupView() {
        //suppose this view will be inflated by server data, so I decided to out this in separate method by SOLID
        organization.setText(R.string.organization);
        problemStatus.setText(R.string.problem_status);
        createdDate.setText(R.string.problem_created_date);
        registeredDate.setText(R.string.problem_registered_date);
        solveDate.setText(R.string.problem_solve_date);
        responsibleOrgan.setText(R.string.responsible_organ);
        problemDescription.setText(R.string.problem_description);
    }

    public void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addItemDecoration(new ItemDecorator());

        if (NetworkUtils.isOnline(this)) {
            recyclerView.setAdapter(new ImageTaskRecyclerViewAdapter(Utils.getImageFromNetwork(this), this, new OnItemClickListener() {
                @Override
                public void onItemClick(int item) {
                    toast((item + 1) + getString(R.string.image));
                }
            }));
        } else {
            recyclerView.setAdapter(new ImageTaskRecyclerViewAdapter(Utils.getImageFromDrawable(), this, new OnItemClickListener() {
                @Override
                public void onItemClick(int item) {
                    toast((item + 1) + getString(R.string.image));
                }
            }));
        }
    }

    @OnClick(R.id.organization)
    public void organization() {
        toast(R.string.organization);
    }

    @OnClick(R.id.problem_status)
    public void problemStatus() {
        toast(R.string.problem_status);
    }

    @OnClick(R.id.created)
    public void created() {
        toast(R.string.created);
    }

    @OnClick(R.id.registered)
    public void registered() {
        toast(R.string.registered);
    }

    @OnClick(R.id.solved)
    public void solved() {
        toast(R.string.solve);
    }

    @OnClick(R.id.responsible)
    public void responsible() {
        toast(R.string.responsible);
    }

    @OnClick(R.id.problem_description)
    public void description() {
        toast(R.string.description);
    }

}
