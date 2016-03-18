package com.todor.yalantistask1.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.todor.yalantistask1.R;
import com.todor.yalantistask1.Utils;
import com.todor.yalantistask1.adapter.ItemDecorator;
import com.todor.yalantistask1.adapter.RecyclerViewAdapter;
import com.todor.yalantistask1.interfaces.OnImageClickListener;

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

        if(Utils.isOn(this)) {
            recyclerView.setAdapter(new RecyclerViewAdapter(Utils.getImageFromNetwork(), this, new OnImageClickListener() {
                @Override
                public void onImageClick(int item) {
                    Toast.makeText(MainActivity.this, (item + 1) + getString(R.string.image), Toast.LENGTH_SHORT).show();
                }
            }));
        } else {
            recyclerView.setAdapter(new RecyclerViewAdapter(Utils.getImageFromDrawable(), this, new OnImageClickListener() {
                @Override
                public void onImageClick(int item) {
                    Toast.makeText(MainActivity.this, (item + 1) + getString(R.string.image), Toast.LENGTH_SHORT).show();
                }
            }));
        }
    }

    @OnClick(R.id.organization)
    public void organization() {
        Toast.makeText(MainActivity.this, R.string.organization, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.problem_status)
    public void problemStatus() {
        Toast.makeText(MainActivity.this, R.string.problem_status, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.created)
    public void created() {
        Toast.makeText(MainActivity.this, R.string.created, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.registered)
    public void registered() {
        Toast.makeText(MainActivity.this, R.string.registered, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.solved)
    public void solved() {
        Toast.makeText(MainActivity.this, R.string.solve, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.responsible)
    public void responsible() {
        Toast.makeText(MainActivity.this, R.string.responsible, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.problem_description)
    public void description() {
        Toast.makeText(MainActivity.this, R.string.description, Toast.LENGTH_SHORT).show();
    }

}
