package com.todor.yalantistask1.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.todor.yalantistask1.utils.DialogFactory;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    protected final String TAG = this.getClass().getSimpleName();
    private AlertDialog loader;

    protected abstract int getContentViewId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getContentViewId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }

    protected void toast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    protected void toast(@StringRes int msg) {
        toast(getString(msg));
    }

    protected void showLoader() {
        if (loader == null) {
            loader = DialogFactory.getLoadingDialog(getActivity()).create();
        }
        loader.show();
    }

    protected void hideLoader() {
        if (loader != null) {
            loader.hide();
        }
    }

}
