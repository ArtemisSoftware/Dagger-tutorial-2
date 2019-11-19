package com.titan.daggertutorial2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import timber.log.Timber;

public class AuthActivity extends DaggerAppCompatActivity {

    @Inject
    String sdfsdf;

    @Inject
    boolean isAppNull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        Timber.d("onCreate: sdfsdf = " + sdfsdf);
        Timber.d("onCreate: is app null? " + isAppNull);
    }
}
