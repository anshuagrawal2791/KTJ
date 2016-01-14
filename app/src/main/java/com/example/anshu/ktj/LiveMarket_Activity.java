package com.example.anshu.ktj;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class LiveMarket_Activity extends AppCompatActivity {
    private AccountHeader headerResult = null;
    private Drawer result = null;
    private boolean opened = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_market_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                        new PrimaryDrawerItem().withName("Wallet").withDescription("").withIdentifier(3).withSelectable(false),
                        new PrimaryDrawerItem().withName("Trade").withDescription("").withIdentifier(4).withSelectable(false),
                        new PrimaryDrawerItem().withName("Transaction").withDescription("").withIdentifier(5).withSelectable(false)
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
                                   intent = new Intent(LiveMarket_Activity.this, Home_Activity.class);
                            } else if (drawerItem.getIdentifier() == 2) {
                                intent = new Intent(LiveMarket_Activity.this, LiveMarket_Activity.class);
                            } else if (drawerItem.getIdentifier() == 3) {
                                intent = new Intent(LiveMarket_Activity.this, Wallet_Activity.class);
                            } else if (drawerItem.getIdentifier() == 4) {
                                intent = new Intent(LiveMarket_Activity.this, Trade_Activity.class);
                            } else if (drawerItem.getIdentifier() == 5) {
                                intent = new Intent(LiveMarket_Activity.this, Trans_Activity.class);
                            }
                            if (intent != null) {
                                LiveMarket_Activity.this.startActivity(intent);
                            }
                        }
                        return false;
                    }



                }).withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(true)
                .build();
    }

}
