package com.example.anshu.ktj;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LiveMarket_Activity extends AppCompatActivity {
    private AccountHeader headerResult = null;
    private Drawer result = null;
    private boolean opened = false;
    static LineData lineData;

    private VolleySingleton volleySingleton;

    static JSONObject weekly_occupancy = new JSONObject();

    static LineChart occupancy_chart;
    static LineDataSet lineDataSet;
    static ArrayList<String> labels = new ArrayList<String>();
    ArrayList<Entry> entries = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_market_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        occupancy_chart = (LineChart)findViewById(R.id.graph);


        volleySingleton = VolleySingleton.getInstance();

        String url = "http://api.btcxindia.com/trades";
        //occupancy_chart = (LineChart)findViewById(R.id.graph);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.d("Ram", "Line 88");

                try {
                    Toast.makeText(LiveMarket_Activity.this, (response.getJSONObject(1)).toString(), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    Log.d("Ram","Line 90");
                    e.printStackTrace();
                }

                for (int i=0;i<response.length();i++){
                    try {
                        Log.d("Ram","Line 88");
                        weekly_occupancy.put(response.getJSONObject(i).getString("time"),response.getJSONObject(i).getString("price"));
                        entries.add(new Entry(Integer.parseInt(response.getJSONObject(i).getString("price")), 0));
                        labels.add(response.getJSONObject(i).getString("time"));

                    } catch (JSONException e) {
                        Log.d("Ram","Line 90");
                        e.printStackTrace();
                    }
                    lineDataSet = new LineDataSet(entries,"Value");
                    lineDataSet.setColor(Color.rgb(2, 254, 151));
                    lineDataSet.setValueTextColor(Color.WHITE);
                    lineData = new LineData(labels,lineDataSet);
                }

                occupancy_chart.setData(lineData);
                occupancy_chart.setDescription("");

            }
        }, new Response.ErrorListener() {
            @Override

            public void onErrorResponse(VolleyError error) {
                Log.d("Ram","Line 122");
            }
        });

        volleySingleton.getmRequestQueue().add(jsonArrayRequest);




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