package com.example.anshu.ktj;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

import fr.ganfra.materialspinner.MaterialSpinner;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Withdrawaltab.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Withdrawaltab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Withdrawaltab extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Withdrawaltab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Withdrawaltab.
     */
    // TODO: Rename and change types and number of parameters
    public static Withdrawaltab newInstance(String param1, String param2) {
        Withdrawaltab fragment = new Withdrawaltab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public MaterialSpinner spinner;
    TextView balance;
    EditText amount;
    EditText accno;
    Button submit;
    ParseUser user=ParseUser.getCurrentUser();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_withdrawaltab, container, false);


        String[] ITEMS = {"BTC", "INR"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        balance=(TextView)v.findViewById(R.id.balance);
        amount=(EditText)v.findViewById(R.id.withdrawalamount);
        accno=(EditText)v.findViewById(R.id.accno);
        submit=(Button)v.findViewById(R.id.submit);

        balance.setVisibility(View.GONE);
        accno.setVisibility(View.GONE);
        amount.setVisibility(View.GONE);
        submit.setVisibility(View.GONE);

        spinner = (MaterialSpinner)v.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    balance.setVisibility(View.VISIBLE);
                    accno.setVisibility(View.VISIBLE);
                    accno.setHint("BTC Wallet Address");
                    amount.setHint("Withdrawal BTC");
                    amount.setVisibility(View.VISIBLE);
                    submit.setVisibility(View.VISIBLE);
                    balance.setText("BTC Balance: " + user.get("bitcoin"));



                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final int amt=Integer.parseInt(amount.getText().toString());
                            if(amt>Integer.parseInt(user.get("bitcoin").toString()))
                            {
                                amount.setError("Insufficient Balance");
                            }
                            else {
                                Toast.makeText(getContext(), "done!", Toast.LENGTH_LONG).show();

                                balance.setText("BTC Balance: "+(user.getInt("bitcoin")-amt));

                                user.put("bitcoin", user.getInt("bitcoin") - amt);

                                // balance.setText(user.getInt("bitcoin") - amt);
                                user.saveInBackground();
                            }
                        }
                    });


                }
if(position==1)
                {
                    balance.setVisibility(View.VISIBLE);
                    accno.setVisibility(View.VISIBLE);
                    accno.setHint("Bank ACC/No");
                    amount.setVisibility(View.VISIBLE);
                    amount.setHint("Withdrawal INR");
                    submit.setVisibility(View.VISIBLE);
                    balance.setText("INR Balance: " + user.get("rupee"));
                    accno.setInputType(InputType.TYPE_CLASS_NUMBER);


                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final int amt=Integer.parseInt(amount.getText().toString());
                            if(amt>Integer.parseInt(user.get("rupee").toString()))
                            {
                                amount.setError("Insufficient Balance");
                            }
                            else {
                                Toast.makeText(getContext(), "done!", Toast.LENGTH_LONG).show();


                                balance.setText("INR Balance: " + (user.getInt("rupee") - amt));
                                user.put("rupee", user.getInt("rupee") - amt);
                                user.saveInBackground();
                            }
                        }
                    });


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
