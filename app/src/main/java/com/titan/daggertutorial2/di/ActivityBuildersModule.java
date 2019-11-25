package com.titan.daggertutorial2.di;

import com.titan.daggertutorial2.di.auth.AuthModule;
import com.titan.daggertutorial2.di.auth.AuthViewModelsModule;
import com.titan.daggertutorial2.di.main.MainFragmentBuildersModule;
import com.titan.daggertutorial2.di.main.MainViewModelsModule;
import com.titan.daggertutorial2.ui.auth.AuthActivity;
import com.titan.daggertutorial2.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class, AuthModule.class}
    )
    abstract AuthActivity contributeAuthActivity();

    @ContributesAndroidInjector(
            modules = {MainFragmentBuildersModule.class, MainViewModelsModule.class}
    )
    abstract MainActivity contributeMainActivity();
}
