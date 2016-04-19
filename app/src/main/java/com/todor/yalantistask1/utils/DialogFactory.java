package com.todor.yalantistask1.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.todor.yalantistask1.R;

public class DialogFactory {

    public DialogFactory() {}

    public static AlertDialog.Builder getLoadingDialog(@NonNull Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loader_layout, null, false);
        return new AlertDialog.Builder(context, R.style.TransparentAlertDialog)
                .setView(view)
                .setCancelable(false);
    }
}
