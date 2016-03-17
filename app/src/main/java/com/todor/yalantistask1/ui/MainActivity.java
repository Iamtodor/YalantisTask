package com.todor.yalantistask1.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.todor.yalantistask1.R;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.create_date) TextView createdDate;
    @Bind(R.id.registrate_date) TextView registratedDate;
    @Bind(R.id.solve_date) TextView solveDate;
    @Bind(R.id.responsible) TextView responsible;
    @Bind(R.id.recycler_view) RecyclerView recyclerView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
