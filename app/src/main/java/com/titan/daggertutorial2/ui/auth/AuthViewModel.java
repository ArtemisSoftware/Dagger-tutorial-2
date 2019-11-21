package com.titan.daggertutorial2.ui.auth;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import timber.log.Timber;

public class AuthViewModel extends ViewModel {

    @Inject
    public AuthViewModel(){

        Timber.d("AuthViewModel: viewmodel is working");
    }
}
