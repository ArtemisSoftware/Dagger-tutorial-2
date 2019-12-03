package com.titan.daggertutorial2.ui.auth;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.titan.daggertutorial2.R;
import com.titan.daggertutorial2.models.User;
import com.titan.daggertutorial2.ui.main.MainActivity;
import com.titan.daggertutorial2.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.DaggerAppCompatActivity;
import timber.log.Timber;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener{

    private AuthViewModel viewModel;
    private EditText userId;
    private ProgressBar progressBar;

    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager;

    @Inject
    @Named("app_user")
    User userNumber1;

    @Inject
    @Named("auth_user")
    User userNumber2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        userId = findViewById(R.id.user_id_input);
        progressBar = findViewById(R.id.progress_bar);
        findViewById(R.id.login_button).setOnClickListener(this);


        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);

        setLogo();
        subscribeObservers();

        Timber.d("onCreate userNumber1: " + userNumber1);
        Timber.d("onCreate userNumber2:" + userNumber2);
    }

    private void subscribeObservers(){
        viewModel.observeAuthState().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource != null){
                    switch (userAuthResource.status){

                        case LOADING:{

                            showProgressBar(true);

                            break;
                        }
                        case AUTHENTICATED:{

                            showProgressBar(false);
                            Timber.d("onChanged: LOGIN SUCCESS: " + userAuthResource.data.getEmail());
                            onLoginSuccess();
                            break;
                        }
                        case ERROR:{

                            showProgressBar(false);
                            Timber.d("onChanged: LOGIN ERROR: " + userAuthResource.message);
                            Toast.makeText(AuthActivity.this, userAuthResource.message + "\nDid you enter a number between 1 and 10?", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case NOT_AUTHENTICATED:{

                            showProgressBar(false);
                            break;
                        }

                    }
                }
            }
        });
    }


    private void showProgressBar(boolean isVisible){
        if(isVisible){
            progressBar.setVisibility(View.VISIBLE);
        }
        else{
            progressBar.setVisibility(View.GONE);
        }
    }

    private void onLoginSuccess(){

        Timber.d("Login success. Moving to main activity");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();;
    }

    private void setLogo(){
        Timber.d("setLogo");
        requestManager
                .load(logo)
                .into((ImageView) findViewById(R.id.login_logo));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.login_button:{

                attemptLogin();
                break;
            }
        }
    }


    private void attemptLogin(){
        if(TextUtils.isEmpty(userId.getText().toString())){
            return;
        }

        viewModel.authenticateWithId(Integer.parseInt(userId.getText().toString()));
    }

}
