package com.titan.daggertutorial2.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.titan.daggertutorial2.BaseActivity;
import com.titan.daggertutorial2.R;
import com.titan.daggertutorial2.ui.main.profile.ProfileFragment;

import timber.log.Timber;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timber.d("creating...");
        testFragment();
    }

    private void testFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new ProfileFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.logout: {
                sessionManager.logOut();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
