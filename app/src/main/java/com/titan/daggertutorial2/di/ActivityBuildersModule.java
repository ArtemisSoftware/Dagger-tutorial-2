package com.titan.daggertutorial2.di;

import com.titan.daggertutorial2.di.auth.AuthViewModelsModule;
import com.titan.daggertutorial2.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class}
    )
    abstract AuthActivity contributeAuthActivity();
}
