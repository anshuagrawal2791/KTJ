package com.example.anshu.ktj;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.parse.ParseUser;

public class Wallet_Activity extends AppCompatActivity {
    private AccountHeader headerResult = null;
    private Drawer result = null;
    private boolean opened = false;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    int kyc;
    ParseUser user;
    LinearLayout wallet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user=ParseUser.getCurrentUser();
        kyc=user.getInt("kyc");

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.splash)


                .withSavedInstance(savedInstanceState)
                .build();
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHasStableIds(true)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Home").withDescription("").withIdentifier(1).withSelectable(false),
                        new PrimaryDrawerItem().withName("Live Market").withDescription("").withIdentifier(2).withSelectable(false),
                        new PrimaryDrawerItem().withName("Wallet").withDescription("").withIdentifier(3).withSelectable(false)
                        //      new PrimaryDrawerItem().withName("Log Out").withDescription("").withIdentifier(6).withSelectable(false)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        //check if the drawerItem is set.
                        //there are different reasons for the drawerItem to be null
                        //--> click on the header
                        //--> click on the footer
                        //those items don't contain a drawerItem

                        if (drawerItem != null) {
                            Intent intent = null;
                            if (drawerItem.getIdentifier() == 1) {
                                //   intent = new Intent(Home_Activity.this, CompactHeaderDrawerActivity.class);
                            } else if (drawerItem.getIdentifier() == 2) {
                                intent = new Intent(Wallet_Activity.this, LiveMarket_Activity.class);
                            } else if (drawerItem.getIdentifier() == 3) {
                                intent = new Intent(Wallet_Activity.this, Wallet_Activity.class);
                            }
                            if (intent != null) {
                                Wallet_Activity.this.startActivity(intent);
                            }
                        }
                        return false;
                    }



                }).withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(true)
                .build();

        wallet=(LinearLayout)findViewById(R.id.walletlayout);

        if(kyc!=0){
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);}
        else
        {
            //Snackbar.make(wallet,"Fill KYC form",Snackbar.LENGTH_INDEFINITE).show();
            Snackbar snackbar = Snackbar
                    .make(wallet, "First Fill KYC form", Snackbar.LENGTH_INDEFINITE)
                    .setAction("FORM", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(Wallet_Activity.this,kyc_form.class));
                        }
                    });

            snackbar.show();
        }


    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Wallettab(), "Wallet");
        adapter.addFragment(new Withdrawaltab(), "Withdrawal");
        adapter.addFragment(new Deposittab(), "Deposit");
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        kyc=user.getInt("kyc");
        if(kyc!=0){
            viewPager = (ViewPager) findViewById(R.id.viewpager);
            setupViewPager(viewPager);

            tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);}
        else
        {
            //Snackbar.make(wallet,"Fill KYC form",Snackbar.LENGTH_INDEFINITE).show();
            Snackbar snackbar = Snackbar
                    .make(wallet, "First Fill KYC form", Snackbar.LENGTH_INDEFINITE)
                    .setAction("FORM", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(Wallet_Activity.this,kyc_form.class));
                        }
                    });

            snackbar.show();
        }

    }
}
