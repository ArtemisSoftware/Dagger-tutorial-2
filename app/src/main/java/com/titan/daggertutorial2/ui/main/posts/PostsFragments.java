package com.titan.daggertutorial2.ui.main.posts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.titan.daggertutorial2.R;
import com.titan.daggertutorial2.models.Post;
import com.titan.daggertutorial2.ui.main.Resource;
import com.titan.daggertutorial2.util.VerticalSpaceItemDecoration;
import com.titan.daggertutorial2.viewmodels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import timber.log.Timber;

public class PostsFragments extends DaggerFragment {

    private PostsViewModel viewModel;
    private RecyclerView recyclerView;

    @Inject
    PostRecyclerAdapter adapter;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_view);

        viewModel = ViewModelProviders.of(this, providerFactory).get(PostsViewModel.class);

        initRecyclerView();
        subscribeObservers();
    }

    private void subscribeObservers(){
        viewModel.observePosts().removeObservers(getViewLifecycleOwner());
        viewModel.observePosts().observe(getViewLifecycleOwner(), new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> listResource) {
                if(listResource != null){
                    Timber.d("onChanged: "+ listResource.data);

                    switch (listResource.status){

                        case LOADING:{
                            Timber.d("onChanged: LOADING...");
                            break;
                        }

                        case SUCCESS:{
                            Timber.d("onChanged: get posts...");
                            adapter.setPosts(listResource.data);
                            break;
                        }

                        case ERROR:{
                            Timber.d("onChanged: ERRORs..." + listResource.message);
                            break;
                        }

                    }
                }
            }
        });
    }

    private void initRecyclerView(){
        recyclerView.setLayoutManager((new LinearLayoutManager(getActivity())));

        VerticalSpaceItemDecoration itemDecoration = new VerticalSpaceItemDecoration(15);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);
    }
}
