package com.example.anshu.ktj;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

public class kyc_form extends AppCompatActivity {

    AutoCompleteTextView name;
    EditText gender,dob,nationality,pan,bankname,bankac,mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        gender = (EditText)findViewById(R.id.gender);
        dob = (EditText)findViewById(R.id.dob);
        nationality = (EditText)findViewById(R.id.nationality);
        pan = (EditText)findViewById(R.id.pan);
        bankname = (EditText)findViewById(R.id.bankname);
        bankac = (EditText)findViewById(R.id.account);
        mobile=(EditText)findViewById(R.id.mobile);





    }

}