package com.example.anshu.ktj;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.NotificationCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    String email;


    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    public Button genpwd;
    public Button login;
    public int n;
    public TextView passwordtf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // setupActionBar();
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        // populateAutoComplete();
        genpwd = (Button) findViewById(R.id.generatepassword);
        login = (Button) findViewById(R.id.login);
        passwordtf = (TextView) findViewById(R.id.password);

        final ParseUser user = ParseUser.getCurrentUser();
        genpwd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEmailView.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(), "Enter email id", Toast.LENGTH_LONG).show();
                else
                {
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext());
                    mBuilder.setSmallIcon(R.drawable.splash2);
                    mBuilder.setContentTitle("Your Login Password is:");
                    Random rand = new Random();
                    n = (100000 + rand.nextInt(900000));
                    mBuilder.setContentText("" + n);
                    Toast.makeText(getApplicationContext(), "Check your notifications for password", Toast.LENGTH_LONG).show();
                    NotificationManager mNotificationManager = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);

                    mNotificationManager.notify(0, mBuilder.build());
                }
            }
        });
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (passwordtf.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(), "Enter password displayed in notifications", Toast.LENGTH_LONG).show();
                else if (passwordtf.getText().toString().equals(n + "")) {
                    ParseQuery<ParseUser> query = ParseUser.getQuery();
                    query.whereEqualTo("email", mEmailView.getText().toString());
                    final ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
                    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    dialog.setMessage("Logging In...");
                    dialog.setIndeterminate(true);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                    query.findInBackground(new FindCallback<ParseUser>() {
                        @Override
                        public void done(List<ParseUser> objects, ParseException e) {
                            if (!objects.isEmpty()) {

                                ParseUser.logInInBackground(mEmailView.getText().toString(), "password",
                                        new LogInCallback() {
                                            public void done(ParseUser user, ParseException e) {
                                                dialog.dismiss();
                                                if (user != null) {
                                                    // If user exist and authenticated, send user to Welcome.class
                                                    Intent launchNextActivity = new Intent(getApplicationContext(), Home_Activity.class);
                                                    launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                                    startActivity(launchNextActivity);
                                                    Toast.makeText(getApplicationContext(),
                                                            "Successfully Logged in",
                                                            Toast.LENGTH_LONG).show();
                                                    //dialog.dismiss();
                                                    //finish();
                                                } else {
                                                    Toast.makeText(
                                                            getApplicationContext(),
                                                            "Wrong Password!!",
                                                            Toast.LENGTH_LONG).show();
                                                    //dialog.dismiss();
                                                }
                                            }
                                        });
                            } else {
                                Toast.makeText(
                                        getApplicationContext(),
                                        "No such user exist, please signup",
                                        Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        }
                    });
                }


            }
        });


    }

}