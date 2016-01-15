package com.example.anshu.ktj;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class kyc_form extends AppCompatActivity {

    AutoCompleteTextView name;
    EditText gender,dob,nationality,pan,bankname,bankac,mobile;
    String gender_str,dob_str,nationality_str,pan_str,bankname_str,bankac_str,mobile_str;
    Button apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ParseUser user = ParseUser.getCurrentUser();
       EditText  name = (EditText)findViewById(R.id.name);
        name.setText(user.getString("name"));
        gender = (EditText)findViewById(R.id.gender);
        dob = (EditText)findViewById(R.id.dob);
        nationality = (EditText)findViewById(R.id.nationality);
        pan = (EditText)findViewById(R.id.pan);
        bankname = (EditText)findViewById(R.id.bankname);
        bankac = (EditText)findViewById(R.id.account);
        mobile=(EditText)findViewById(R.id.mobile);
        apply = (Button) findViewById(R.id.btn);




       // user.put();
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender_str = gender.getText().toString();
                dob_str = dob.getText().toString();
                nationality_str = nationality.getText().toString();
                pan_str = pan.getText().toString();
                bankname_str = bankname.getText().toString();
                bankac_str = bankac.getText().toString();
                mobile_str = mobile.getText().toString();
                if (gender_str.equals("")){
                    gender.setError("This Field is required");
                }
                if (dob_str.equals("")){
                    dob.setError("This Field is required");
                } if (nationality_str.equals("")){
                    nationality.setError("This Field is required");
                } if (pan_str.equals("")){
                    pan.setError("This Field is required");
                } if (bankname_str.equals("")){
                     bankname.setError("This Field is required");
                } if (bankac_str.equals("")){
                    bankac.setError("This Field is required");
                } if (mobile_str.equals("")){
                    mobile.setError("This Field is required");
                }
                else{


                    ParseUser user = ParseUser.getCurrentUser();
                    user.put("gender", gender_str);
                    user.put("dob", dob_str);
                    user.put("nationality", nationality_str);
                    user.put("pan", pan_str);
                    user.put("bank", bankname_str);
                    user.put("accno", bankac_str);
                    user.put("phone", mobile_str);

                    final ProgressDialog dialog = new ProgressDialog(kyc_form.this);
                    dialog.setIndeterminate(true);
                    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    dialog.setMessage("Signing Up..");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();


                    user.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {

                            if (e != null) {
                                Toast.makeText(kyc_form.this, "Error", Toast.LENGTH_LONG).show();
                                dialog.dismiss();

                            } else {
                                dialog.dismiss();
                                Toast.makeText(kyc_form.this, "Successfully Updated", Toast.LENGTH_LONG).show();
                                Log.e("mayank", "asynctask successful");

                            }
                        }
                    });

                }
            }
        });

    }

}