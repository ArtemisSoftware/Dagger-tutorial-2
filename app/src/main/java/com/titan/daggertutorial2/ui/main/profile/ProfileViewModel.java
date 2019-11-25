package com.titan.daggertutorial2.ui.main.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.titan.daggertutorial2.SessionManager;
import com.titan.daggertutorial2.models.User;
import com.titan.daggertutorial2.ui.auth.AuthResource;

import javax.inject.Inject;

import timber.log.Timber;

public class ProfileViewModel extends ViewModel {


    private final SessionManager sessionManager;

    @Inject
    public ProfileViewModel(SessionManager sessionManager){
        Timber.d("Viewmodel is ready");
        this.sessionManager = sessionManager;
    }

    public LiveData<AuthResource<User>> getAuthenticatedUser(){
        return sessionManager.getAuthUser();
    }
}
