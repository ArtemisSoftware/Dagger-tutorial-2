package com.titan.daggertutorial2.di.main;

import com.titan.daggertutorial2.network.main.MainApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @Provides
    static MainApi provideMainApo(Retrofit retrofit){
        return retrofit.create(MainApi.class);
    }
}
