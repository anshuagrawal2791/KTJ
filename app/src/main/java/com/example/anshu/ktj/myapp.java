package com.example.anshu.ktj;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;

/**
 * Created by mayank on 13/1/16.
 */
public class myapp extends Application {
    private static myapp inst;


    @Override
    public void onCreate() {
        super.onCreate();
        inst = this;
        Parse.enableLocalDatastore(this);
      // Parse.initialize(this, "vfovUtfqlcbuYehNSNi4SAVOCZKMfwEpaXH13mPc", "kczjeGWSUm36slzD0bGPI7JcLNaw17b9kV6nPLHK");
        Parse.initialize(this);



      //  ParseFacebookUtils.initialize(this);
//        ParseUser.enableAutomaticUser();
//        ParseACL defaultACL = new ParseACL();
//
//        // If you would like all objects to be private by default, remove this
//        // line.
//        defaultACL.setPublicReadAccess(true);
//
//        ParseACL.setDefaultACL(defaultACL, true);
    }
    public static myapp getInst(){
        return inst;

    }
    public static Context getAppContext()
    {
        return inst.getApplicationContext();
    }
}
