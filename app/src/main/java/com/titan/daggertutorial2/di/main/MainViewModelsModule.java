package com.titan.daggertutorial2.di.main;

import androidx.lifecycle.ViewModel;

import com.titan.daggertutorial2.di.ViewModelKey;
import com.titan.daggertutorial2.ui.main.posts.PostsViewModel;
import com.titan.daggertutorial2.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel.class)
    public abstract ViewModel bindPostsViewModel(PostsViewModel viewModel);
}
