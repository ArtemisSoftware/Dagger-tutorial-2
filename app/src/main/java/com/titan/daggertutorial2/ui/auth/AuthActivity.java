package com.titan.daggertutorial2.ui.auth;

import androidx.lifecycle.ViewModelProviders;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.titan.daggertutorial2.R;
import com.titan.daggertutorial2.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import timber.log.Timber;

public class AuthActivity extends DaggerAppCompatActivity {

    private AuthViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);

        setLogo();
    }

    private void setLogo(){
        Timber.d("setLogo");
        requestManager
                .load(logo)
                .into((ImageView) findViewById(R.id.login_logo));
    }
}
