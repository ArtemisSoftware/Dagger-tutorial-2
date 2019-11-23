package com.titan.daggertutorial2.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.titan.daggertutorial2.SessionManager;
import com.titan.daggertutorial2.models.User;
import com.titan.daggertutorial2.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class AuthViewModel extends ViewModel {

    //inject
    private final AuthApi authApi;
    private SessionManager sessionManager;

    @Inject
    public AuthViewModel(AuthApi authApi, SessionManager sessionManager){

        this.authApi = authApi;
        this.sessionManager = sessionManager;
        Timber.d("viewmodel is working");
        Timber.d("auth api: " + this.authApi);
    }

    public void authenticateWithId(int userId){

        Timber.d("Atempting to log in....");
        sessionManager.authenticateWithId(queryUserId(userId));
    }

    private LiveData<AuthResource<User>> queryUserId(int userId){
        return LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(userId)

                        //instead of calling onError (error happens)
                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Exception {
                                User errorUser = new User();
                                errorUser.setId(-1);

                                Timber.d("error getting data");
                                return errorUser;
                            }
                        })
                        .map(new Function<User, AuthResource<User>>() {
                            @Override
                            public AuthResource<User> apply(User user) throws Exception {
                                if(user.getId() == -1){
                                    Timber.d("Could not authenticate");
                                    return AuthResource.error("Could not authenticate", (User) null);
                                }
                                Timber.d("authenticated");
                                return AuthResource.authenticated(user);
                            }
                        })
                        .subscribeOn(Schedulers.io()));
    }


    public LiveData<AuthResource<User>> observeAuthState(){
        return sessionManager.getAuthUser();
    }

}
