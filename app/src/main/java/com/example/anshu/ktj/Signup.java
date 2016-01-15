package com.example.anshu.ktj;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Signup extends AppCompatActivity {
    Button sub;
    EditText user, city, email, phone;
    String username, password, emailid, phoneno;

    TextView alreadyregistered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        sub=(Button)findViewById(R.id.submit);
        alreadyregistered=(TextView)findViewById(R.id.alreadyamember);
        alreadyregistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this,LoginActivity.class));
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = (EditText) findViewById(R.id.name);
                city = (EditText) findViewById(R.id.password);
                email = (EditText) findViewById(R.id.email);
                phone = (EditText) findViewById(R.id.phone);

                username = user.getText().toString();
                password = city.getText().toString();
                emailid = email.getText().toString();
                phoneno = phone.getText().toString();

                if(username.equals("")){
                    user.setError("This Field is required");
                }if(emailid.equals("")){
                    email.setError("This Field is required");
                }if(password.equals("")){
                    city.setError("This Field is required");
                }if(phoneno.equals("")){
                    phone.setError("This Field is required");
                } else {
                    // Save new user data into Parse.com Data Storage
                    ParseUser user = new ParseUser();
                      user.setUsername(emailid);
                      user.setPassword("password");
                    user.put("city", city.getText().toString());

                    user.put("phone", phoneno);
                    user.setEmail(emailid);
                    user.put("name",username);
                    user.put("kyc",0);
                    user.put("bitcoin",5.0);
                    user.put("rupee",10000.0);
                    final ProgressDialog dialog= new ProgressDialog(Signup.this);
                    dialog.setIndeterminate(true);
                    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    dialog.setMessage("Signing Up..");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                    user.signUpInBackground(new SignUpCallback() {
                        public void done(ParseException e) {
                            dialog.dismiss();
                            if (e == null) {
                                // Show a simple Toast message upon successful registration
                                Toast.makeText(getApplicationContext(),
                                        "Successfully Signed up, please log in.",
                                        Toast.LENGTH_LONG).show();
                                startActivity(new Intent(Signup.this,LoginActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        e.toString(), Toast.LENGTH_LONG)
                                        .show();
                            }
                        }


                    });
                }
            }
        });



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}