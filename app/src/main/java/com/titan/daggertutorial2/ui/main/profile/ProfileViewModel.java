package com.titan.daggertutorial2.ui.main.profile;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import timber.log.Timber;

public class ProfileViewModel extends ViewModel {

    @Inject
    public ProfileViewModel(){
        Timber.d("Viewmodel is ready");
    }
}
