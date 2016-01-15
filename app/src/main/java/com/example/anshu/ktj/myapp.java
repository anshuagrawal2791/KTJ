package com.example.anshu.ktj;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;

/**
 * Created by mayank on 13/1/16.
 */
public class myapp extends Application {


    private static myapp sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this);
        sInstance=this;
        Parse.enableLocalDatastore(this);
       // Parse.initialize(this);


    }

    public static myapp getsInstance(){
        return sInstance;
    }

    public static Context getAppContext(){
        return getsInstance().getApplicationContext();
    }

}