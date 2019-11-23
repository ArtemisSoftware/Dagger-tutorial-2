package com.titan.daggertutorial2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.titan.daggertutorial2.models.User;
import com.titan.daggertutorial2.ui.auth.AuthResource;

import javax.inject.Inject;

import timber.log.Timber;

public class SessionManager {

    private MediatorLiveData<AuthResource<User>> cachedUser = new MediatorLiveData<>();

    @Inject
    public SessionManager() {
        Timber.d("SessionManager constructor");
    }

    public void authenticateWithId(final LiveData<AuthResource<User>> source){
        if(cachedUser != null){

            Timber.d("Cached user is NOT NULL");

            cachedUser.setValue(AuthResource.loading((User) null));
            cachedUser.addSource(source, new Observer<AuthResource<User>>() {
                @Override
                public void onChanged(AuthResource<User> userAuthResource) {
                    cachedUser.setValue(userAuthResource);
                    cachedUser.removeSource(source);
                }
            });
        }
        else{
            Timber.d("Cached user is NULL");
        }
    }

    public void logOut(){
        Timber.d("logging out...");
        cachedUser.setValue(AuthResource.<User>logout());
    }


    public LiveData<AuthResource<User>> getAuthUser(){
        return cachedUser;
    }
}
