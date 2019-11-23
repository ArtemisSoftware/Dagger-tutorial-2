package com.titan.daggertutorial2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.titan.daggertutorial2.models.User;
import com.titan.daggertutorial2.ui.auth.AuthActivity;
import com.titan.daggertutorial2.ui.auth.AuthResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import timber.log.Timber;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    @Inject
    public SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        susbcribeObservers();
    }


    private void susbcribeObservers(){
        sessionManager.getAuthUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource != null){
                    switch (userAuthResource.status){

                        case LOADING:{

                            break;
                        }
                        case AUTHENTICATED:{

                            Timber.d("onChanged: LOGIN SUCCESS: " + userAuthResource.data.getEmail());
                            break;
                        }
                        case ERROR:{

                            Timber.d("onChanged: LOGIN ERROR: " + userAuthResource.message);
                            break;
                        }
                        case NOT_AUTHENTICATED:{
                            navLoginScreen();
                            break;
                        }

                    }
                }
            }
        });
    }

    private void navLoginScreen(){

        Timber.d("Redirecting to authentication screen");

        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }
}
