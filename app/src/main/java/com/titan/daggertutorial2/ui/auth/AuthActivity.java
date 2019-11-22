package com.titan.daggertutorial2.ui.auth;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.titan.daggertutorial2.R;
import com.titan.daggertutorial2.models.User;
import com.titan.daggertutorial2.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import timber.log.Timber;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener{

    private AuthViewModel viewModel;
    private EditText userId;

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

        userId = findViewById(R.id.user_id_input);
        findViewById(R.id.login_button).setOnClickListener(this);


        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);

        setLogo();
        subscribeObservers();
    }

    private void subscribeObservers(){
        viewModel.observeUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user != null){
                    Timber.d("User authenticated");
                }
                else{
                    Timber.d("Authentication error");
                }
            }
        });
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
