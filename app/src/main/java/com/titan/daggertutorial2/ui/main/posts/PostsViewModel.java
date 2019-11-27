package com.titan.daggertutorial2.ui.main.posts;

import androidx.lifecycle.ViewModel;

import com.titan.daggertutorial2.SessionManager;
import com.titan.daggertutorial2.network.main.MainApi;

import javax.inject.Inject;

import timber.log.Timber;

public class PostsViewModel extends ViewModel {

    //inject
    private final SessionManager sessionManager;
    private final MainApi mainApi;

    @Inject
    public PostsViewModel(SessionManager sessionManager, MainApi mainApi) {
        this.sessionManager = sessionManager;
        this.mainApi = mainApi;

        Timber.d("PostsViewModel: view model is working");
    }
}
