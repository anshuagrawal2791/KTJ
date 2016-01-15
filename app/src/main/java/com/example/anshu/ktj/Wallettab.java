package com.example.anshu.ktj;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseUser;

import org.w3c.dom.Text;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Wallettab.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Wallettab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Wallettab extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Wallettab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Wallettab.
     */
    // TODO: Rename and change types and number of parameters
    public static Wallettab newInstance(String param1, String param2) {
        Wallettab fragment = new Wallettab();
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

    TextView name;
    TextView phone;
    TextView bankdetails;
    TextView customerid;
    TextView bitcoin;
    TextView rupee;
    ParseUser user;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_wallettab, container, false);

        user=ParseUser.getCurrentUser();
        name=(TextView)v.findViewById(R.id.textView11);
        phone=(TextView)v.findViewById(R.id.textView12);
        bankdetails=(TextView)v.findViewById(R.id.textView13);
        customerid=(TextView)v.findViewById(R.id.textView14);
        bitcoin=(TextView)v.findViewById(R.id.textView16);
        rupee=(TextView)v.findViewById(R.id.textView17);


        name.setText(user.getString("name"));
        phone.setText(user.getString("phone"));
        bankdetails.setText(user.getString("bank")+user.getString("accno"));

        Random rand = new Random();
        int n= (100000 + rand.nextInt(900000));
        customerid.setText("BTCX"+n);
        bitcoin.setText(user.get("bitcoin")+"");
        rupee.setText(user.get("rupee")+"");


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
