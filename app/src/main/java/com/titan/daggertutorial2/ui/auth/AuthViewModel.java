package com.titan.daggertutorial2.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.titan.daggertutorial2.models.User;
import com.titan.daggertutorial2.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class AuthViewModel extends ViewModel {

    private final AuthApi authApi;
    private MediatorLiveData<User> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi authApi){

        this.authApi = authApi;
        Timber.d("viewmodel is working");
        Timber.d("auth api: " + this.authApi);
    }

    public void authenticateWithId(int userId){
        final LiveData<User> source = LiveDataReactiveStreams.fromPublisher(authApi.getUser(userId).subscribeOn(Schedulers.io()));

        authUser.addSource(source, new Observer<User>() {
            @Override
            public void onChanged(User user) {

                Timber.d("Auth API responded: " + user.toString());
                authUser.setValue(user);
                authUser.removeSource(source);
            }
        });
    }

    public LiveData<User> observeUser(){
        return authUser;
    }

}
