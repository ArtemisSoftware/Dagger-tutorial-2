package com.titan.daggertutorial2.ui.auth;

import androidx.lifecycle.ViewModel;

import com.titan.daggertutorial2.network.auth.AuthApi;

import javax.inject.Inject;

import timber.log.Timber;

public class AuthViewModel extends ViewModel {

    private final AuthApi authApi;

    @Inject
    public AuthViewModel(AuthApi authApi){

        this.authApi = authApi;
        Timber.d("viewmodel is working");

        if(this.authApi == null){
            Timber.d("auth api is NULL");
        }
        else{
            Timber.d("auth api is NOT NULL");
        }
    }
}
