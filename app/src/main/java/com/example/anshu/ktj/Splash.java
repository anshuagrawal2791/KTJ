package com.example.anshu.ktj;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.parse.Parse;
import com.parse.ParseUser;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Parse.initialize(this);
        setContentView(R.layout.splash);

        findViewById(R.id.loading).setVisibility(View.VISIBLE);

        final boolean[] isConnected = {util.checkConnection(Splash.this)};

        Log.e("connection", Boolean.toString(isConnected[0]));


        if(isConnected[0]) {
            Thread timerThread = new Thread(){
                public void run(){
                    try{
                        //ParseUser user= ParseUser.getCurrentUser();

                        sleep(3000);




                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    finally {
                        ParseUser currentUser = ParseUser.getCurrentUser();
                        if (currentUser != null) {
                            // Send logged in users to Welcome.class
                            Intent intent = new Intent(Splash.this, Home.class);
                            //findViewById(R.id.loading).setVisibility(View.GONE);
                            startActivity(intent);
                            finish();
                            //Toast.makeText(this,"a",Toast.LENGTH_LONG).show();
                        } else {
                            // Send user to LoginSignupActivity.class
                            Intent intent = new Intent(Splash.this,
                                    Signup.class);
                            startActivity(intent);
                            finish();
                            //Toast.makeText(this,"b",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            };
            timerThread.start();





        }
        else {

            RelativeLayout coordinatorLayout = (RelativeLayout) findViewById(R.id
                    .coordinatorLayout);
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "No Network", Snackbar.LENGTH_INDEFINITE);


            snackbar.show();

            Thread timerThread = new Thread(){
                public void run(){
                    try{
                        //ParseUser user= ParseUser.getCurrentUser();
                        while(isConnected[0] ==false) {
                            sleep(1000);
                            if(util.checkConnection(Splash.this)==true)
                                isConnected[0] =true;

                        }

                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    finally {
                        ParseUser currentUser = ParseUser.getCurrentUser();
                        if (currentUser != null) {
                            // Send logged in users to Welcome.class
                            Intent intent = new Intent(Splash.this, Home.class);
                            //findViewById(R.id.loading).setVisibility(View.GONE);
                            startActivity(intent);
                            finish();
                            //Toast.makeText(this,"a",Toast.LENGTH_LONG).show();
                        } else {
                            // Send user to LoginSignupActivity.class
                            Intent intent = new Intent(Splash.this,
                                    Signup.class);
                            startActivity(intent);
                            finish();
                            //Toast.makeText(this,"b",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            };
            timerThread.start();


//            Toast.makeText(splashscreen.this,"c",Toast.LENGTH_LONG).show();
//            findViewById(R.id.loading).setVisibility(View.GONE);



        }




    }
}