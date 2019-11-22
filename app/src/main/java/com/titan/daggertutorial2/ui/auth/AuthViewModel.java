package com.titan.daggertutorial2.ui.auth;

import androidx.lifecycle.ViewModel;

import com.titan.daggertutorial2.models.User;
import com.titan.daggertutorial2.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class AuthViewModel extends ViewModel {

    private final AuthApi authApi;

    @Inject
    public AuthViewModel(AuthApi authApi){

        this.authApi = authApi;
        Timber.d("viewmodel is working");
        Timber.d("auth api: " + this.authApi);

        authApi.getUser(1)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        Timber.d("onNext: "+ user.getEmail());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e("onError: "+ e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
