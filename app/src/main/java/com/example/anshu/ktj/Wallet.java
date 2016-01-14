package com.example.anshu.ktj;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;



public class Wallet extends Fragment {
    View rootview;
    ViewPager viewPager;
    TabLayout tabLayout;
    ImageView im;
    ImageView im1;
    ImageView im2;
    FragmentTabHost tabHost;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_wallet, container, false);


        viewPager = (ViewPager)rootview.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
//
//        im=(ImageView)rootview.findViewById(R.id.im);
//        im1=(ImageView)rootview.findViewById(R.id.im1);
//        im2=(ImageView)rootview.findViewById(R.id.im2);

        tabHost=(FragmentTabHost)rootview.findViewById(R.id.tabhost);


        Log.e("df","create");

        Volley.newRequestQueue(getContext());

//        im.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewPager.setCurrentItem(2);
//            }
//        });
//        im1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewPager.setCurrentItem(1);
//            }
//        });
//        im2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewPager.setCurrentItem(0);
//            }
//        });

        return rootview;
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new Wallettab(), "Wallet");
        adapter.addFragment(new Withdrawaltab(), "Withdrwawal");
        adapter.addFragment(new Deposittab(), "Deposits");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("df","[ause");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("df","start");
    }
}