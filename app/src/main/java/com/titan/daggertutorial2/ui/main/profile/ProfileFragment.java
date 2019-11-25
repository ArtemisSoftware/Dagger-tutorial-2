package com.titan.daggertutorial2.ui.main.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.titan.daggertutorial2.R;

import dagger.android.support.DaggerFragment;
import timber.log.Timber;

public class ProfileFragment extends DaggerFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Timber.d("creating the view of the fragment...");
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}
