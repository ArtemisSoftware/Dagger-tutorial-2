package com.titan.daggertutorial2.di;

import androidx.lifecycle.ViewModelProvider;

import com.titan.daggertutorial2.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory modelProviderFactory);
}
