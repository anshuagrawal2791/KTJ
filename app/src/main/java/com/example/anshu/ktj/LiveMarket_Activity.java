package com.example.anshu.ktj;

import android.app.ProgressDialog;
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
import com.github.mikephil.charting.components.Legend;
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


    private VolleySingleton volleySingleton;

    JSONObject weekly_occupancy = new JSONObject();

     LineChart occupancy_chart;
   //  LineDataSet lineDataSet;
   //  ArrayList<String> labels = new ArrayList<String>();
   // ArrayList<Entry> entries = new ArrayList<>();
    public ArrayList<String> xVals = new ArrayList<String>();
    public ArrayList<Entry> yVals = new ArrayList<Entry>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_market_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




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
                        Log.d("Ram", "Line 88");
                        weekly_occupancy.put(response.getJSONObject(i).getString("time"), response.getJSONObject(i).getString("price"));
                      //  entries.add(new Entry(Integer.parseInt(response.getJSONObject(i).getString("price")), 0));
                      //  labels.add(response.getJSONObject(i).getString("time"));
                        xVals.add(response.getJSONObject(i).getString("time"));
                        yVals.add(new Entry(Integer.parseInt(response.getJSONObject(i).getString("price")), 0));

                    } catch (JSONException e) {
                        Log.d("Ram","Line 90");
                        e.printStackTrace();
                    }
               //     lineDataSet = new LineDataSet(entries,"Value");
                 //   lineDataSet.setColor(Color.rgb(2, 254, 151));
                  //  lineDataSet.setValueTextColor(Color.WHITE);
                   // lineData = new LineData(labels,lineDataSet);
                }


              //  occupancy_chart.setData(lineData);
               // occupancy_chart.setDescription("");

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
                        new PrimaryDrawerItem().withName("Home").withDescription("").withIdentifier(1).withSelectable(true),
                        new PrimaryDrawerItem().withName("Live Market").withDescription("").withIdentifier(2).withSelectable(true),
                        new PrimaryDrawerItem().withName("Wallet").withDescription("").withIdentifier(3).withSelectable(true),
                        new PrimaryDrawerItem().withName("Trade").withDescription("").withIdentifier(4).withSelectable(true),
                        new PrimaryDrawerItem().withName("Transaction").withDescription("").withIdentifier(5).withSelectable(true)
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


        occupancy_chart = (LineChart)findViewById(R.id.graph);
        LineData data = getData(36, 100);
        setupChart(occupancy_chart,data, Color.rgb(137, 230, 81));

    }

    private void setupChart(LineChart chart, LineData data, int color) {

        // no description text
        chart.setDescription("");
        chart.setNoDataTextDescription("You need to provide data for the chart.");

        // mChart.setDrawHorizontalGrid(false);
        //
        // enable / disable grid background
        chart.setDrawGridBackground(false);
//        chart.getRenderer().getGridPaint().setGridColor(Color.WHITE & 0x70FFFFFF);

        // enable touch gestures
        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setBackgroundColor(color);

        // set custom chart offsets (automatic offset calculation is hereby disabled)
        chart.setViewPortOffsets(10, 0, 10, 0);

        // add data
        chart.setData(data);

        // get the legend (only possible after setting data)
        Legend l = chart.getLegend();
        l.setEnabled(false);

        chart.getAxisLeft().setEnabled(false);
        chart.getAxisRight().setEnabled(false);

        chart.getXAxis().setEnabled(false);

        // animate calls invalidate()...
        chart.animateX(2500);
        final ProgressDialog dialog = new ProgressDialog(LiveMarket_Activity.this);
        do {

            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("Fetching Data...");
            dialog.setIndeterminate(true);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
        while (xVals.isEmpty() || yVals.isEmpty());
        dialog.dismiss();
        Log.v("mayank1234", xVals.get(2));
        Log.v("mayank12345", xVals.get(2));
    }


    private LineData getData(int count, float range) {

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, "DataSet 1");
        // set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        set1.setLineWidth(1.75f);
        set1.setCircleSize(3f);
        set1.setColor(Color.WHITE);
        set1.setCircleColor(Color.WHITE);
        set1.setHighLightColor(Color.WHITE);
        set1.setDrawValues(false);
        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        return data;
    }

}