package com.todor.yalantistask.ui.activity;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.todor.yalantistask.utils.DialogFactory;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    protected final String TAG = this.getClass().getSimpleName();
    private AlertDialog loader;

    protected abstract int getContentViewId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    protected void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    protected void toast(@StringRes int msg) {
        toast(getString(msg));
    }

    protected void showLoader() {
        if (loader == null) {
            loader = DialogFactory.getLoadingDialog(this).create();
        }
        loader.show();
    }

    protected void hideLoader() {
        if (loader != null) {
            loader.hide();
        }
    }
}
