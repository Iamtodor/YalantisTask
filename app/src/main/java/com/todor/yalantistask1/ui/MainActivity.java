package com.todor.yalantistask1.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.todor.yalantistask1.R;
import com.todor.yalantistask1.Utils;
import com.todor.yalantistask1.adapter.ItemDecorator;
import com.todor.yalantistask1.adapter.RecyclerViewAdapter;

import butterknife.Bind;

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

    public void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.toolbar_title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void setupView() {
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
        recyclerView.setAdapter(new RecyclerViewAdapter(Utils.getImageUrlList(), this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
        }
        return false;
    }}
