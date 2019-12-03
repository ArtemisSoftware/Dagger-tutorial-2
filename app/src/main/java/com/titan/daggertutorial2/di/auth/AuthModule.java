package com.titan.daggertutorial2.di.auth;

import com.titan.daggertutorial2.models.User;
import com.titan.daggertutorial2.network.auth.AuthApi;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import timber.log.Timber;

@Module
public class AuthModule {

    @AuthScope
    @Provides
    @Named("auth_user")
    static User someUser(){
        return new User();
    }

    @AuthScope
    @Provides
    static AuthApi provideAuthApi(Retrofit retrofit){

        Timber.d("provideAuthApi retrofit: " + retrofit);
        return retrofit.create(AuthApi.class);
    }
}
