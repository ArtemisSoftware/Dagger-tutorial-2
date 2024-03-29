package com.titan.daggertutorial2.di;

import com.titan.daggertutorial2.di.auth.AuthModule;
import com.titan.daggertutorial2.di.auth.AuthScope;
import com.titan.daggertutorial2.di.auth.AuthViewModelsModule;
import com.titan.daggertutorial2.di.main.MainFragmentBuildersModule;
import com.titan.daggertutorial2.di.main.MainModule;
import com.titan.daggertutorial2.di.main.MainScope;
import com.titan.daggertutorial2.di.main.MainViewModelsModule;
import com.titan.daggertutorial2.ui.auth.AuthActivity;
import com.titan.daggertutorial2.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class, AuthModule.class}
    )
    abstract AuthActivity contributeAuthActivity();

    @MainScope
    @ContributesAndroidInjector(
            modules = {MainFragmentBuildersModule.class, MainViewModelsModule.class, MainModule.class}
    )
    abstract MainActivity contributeMainActivity();
}
