package com.titan.daggertutorial2.di.auth;

import androidx.lifecycle.ViewModel;

import com.titan.daggertutorial2.di.ViewModelKey;
import com.titan.daggertutorial2.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);

}
