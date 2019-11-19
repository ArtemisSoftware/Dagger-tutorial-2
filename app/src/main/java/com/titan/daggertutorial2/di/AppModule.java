package com.titan.daggertutorial2.di;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    static String someString(){
        return  "this is a test string";
    }

    @Provides
    static boolean getApp(Application application){
        return  application == null;
    }

    @Provides
    static int someInt(String string){
        if(string.equals("this is a test string")){
            return 1;
        }
        return 0;
    }
}
