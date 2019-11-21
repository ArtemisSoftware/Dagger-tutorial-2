package com.titan.daggertutorial2.di.auth;

import com.titan.daggertutorial2.network.auth.AuthApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import timber.log.Timber;

@Module
public class AuthModule {

    @Provides
    static AuthApi provideAuthApi(Retrofit retrofit){

        Timber.d("provideAuthApi retrofit: " + retrofit);
        return retrofit.create(AuthApi.class);
    }
}
