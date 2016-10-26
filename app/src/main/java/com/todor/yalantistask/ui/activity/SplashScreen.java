package com.todor.yalantistask.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class SplashScreen extends AppCompatActivity {

    public static final int DELAY_MILLIS = 400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Observable.just("")
                .delay(DELAY_MILLIS, TimeUnit.MILLISECONDS)
                .doOnNext(s -> {
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    finish();
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
