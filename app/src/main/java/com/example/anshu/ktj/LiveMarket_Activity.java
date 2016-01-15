package com.example.anshu.ktj;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
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

     LineChart chart;
   //  LineDataSet lineDataSet;
   //  ArrayList<String> labels = new ArrayList<String>();
   // ArrayList<Entry> entries = new ArrayList<>();
    public ArrayList<String> xVals = new ArrayList<String>();
    public ArrayList<Entry> yVals = new ArrayList<Entry>();
    // ProgressDialog dialog = new ProgressDialog(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_market_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    /*    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Fetching Data...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        */

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
                   //     weekly_occupancy.put(response.getJSONObject(i).getString("time"), response.getJSONObject(i).getString("price"));
                      //  entries.add(new Entry(Integer.parseInt(response.getJSONObject(i).getString("price")), 0));
                      //  labels.add(response.getJSONObject(i).getString("time"));
                       // if(i==0 || i==7 || i==14 || i==21 || i==28 || i==35) {
                     //       xVals.add(response.getJSONObject(i).getString("time"));
                       //     yVals.add(new Entry(Integer.parseInt(response.getJSONObject(i).getString("price")), 0));
                       // }
                       if(i==0){
                           TextView time = (TextView) findViewById(R.id.time1);

                           time.setText(response.getJSONObject(i).getString("time"));
                           TextView price = (TextView) findViewById(R.id.price1);
                           price.setText(response.getJSONObject(i).getString("price"));
                       }
                        if(i==1){
                            TextView time = (TextView) findViewById(R.id.time2);
                            time.setText(response.getJSONObject(i).getString("time"));
                            TextView price = (TextView) findViewById(R.id.price2);
                            price.setText(response.getJSONObject(i).getString("price"));
                        }
                        if(i==2){
                            TextView time = (TextView) findViewById(R.id.time3);
                            time.setText(response.getJSONObject(i).getString("time"));
                            TextView price = (TextView) findViewById(R.id.price3);
                            price.setText(response.getJSONObject(i).getString("price"));
                        }
                        if(i==3){
                            TextView time = (TextView) findViewById(R.id.time4);
                            time.setText(response.getJSONObject(i).getString("time"));
                            TextView price = (TextView) findViewById(R.id.price4);
                            price.setText(response.getJSONObject(i).getString("price"));
                        }
                        if(i==4){
                            TextView time = (TextView) findViewById(R.id.time5);
                            time.setText(response.getJSONObject(i).getString("time"));
                            TextView price = (TextView) findViewById(R.id.price5);
                            price.setText(response.getJSONObject(i).getString("price"));
                        }

                    } catch (JSONException e) {
                        Log.d("Ram","Line 90");
                        e.printStackTrace();
                    }

               //     lineDataSet = new LineDataSet(entries,"Value");
                 //   lineDataSet.setColor(Color.rgb(2, 254, 151));
                  //  lineDataSet.setValueTextColor(Color.WHITE);
                   // lineData = new LineData(labels,lineDataSet);
                }

        //       chart = (LineChart)findViewById(R.id.graph);
          /*      chart.setDescription("");
                chart.setNoDataTextDescription("You need to provide data for the chart.");

                // mChart.setDrawHorizontalGrid(false);
                //
                // enable / disable grid background
                chart.setDrawGridBackground(false);
//        chart.getRenderer().getGridPaint().setGridColor(Color.WHITE & 0x70FFFFFF);

                // enable touch gestures
             //   chart.setTouchEnabled(true);

                // enable scaling and dragging
                chart.setDragEnabled(true);
                chart.setScaleEnabled(true);

                // if disabled, scaling can be done on x- and y-axis separately
                chart.setPinchZoom(true);
            //    Typeface tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

             //   chart.setBackgroundColor(color);

                // set custom chart offsets (automatic offset calculation is hereby disabled)
              ///  chart.setViewPortOffsets(10, 0, 10, 0);
                LineDataSet set1 = new LineDataSet(yVals, "");
                //\ set1.setFillAlpha(110);
                // set1.setFillColor(Color.RED);

               set1.setLineWidth(1f);
                set1.setDrawCubic(true);
            //    set1.setCircleSize();
             //   set1.setColor(Color.WHITE);
              //  set1.setCircleColor(Color.WHITE);
              //  set1.setHighLightColor(Color.WHITE);
              //  set1.setDrawValues(false);
                ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
                dataSets.add(set1); // add the datasets

                // create a data object with the datasets
                LineData data = new LineData(xVals, dataSets);

                //     LineData data = getData(36, 100);
                // add data
                chart.setData(data);

                // get the legend (only possible after setting data)
            //    Legend l = chart.getLegend();
              //  l.setEnabled(false);

               // chart.getAxisLeft().setEnabled(false);
               // chart.getAxisRight().setEnabled(false);

            //    chart.getXAxis().setEnabled(false);

                // animate calls invalidate()...
               // chart.animateX(2500);  */
            //    Typeface tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

      /*          chart.setDragEnabled(true);
                chart.setScaleEnabled(true);
                chart.setDrawGridBackground(false);
                chart.setHighlightPerDragEnabled(true);
                chart.setPinchZoom(true);

                // set an alternative background color
                chart.setBackgroundColor(Color.LTGRAY);

                // add data
            //    setData(20, 30);

             //   chart.animateX(2500);
                XAxis xAxis = chart.getXAxis();
              //  xAxis.setTypeface(tf);
                xAxis.setTextSize(12f);
                xAxis.setTextColor(Color.WHITE);
                xAxis.setDrawGridLines(false);
                xAxis.setDrawAxisLine(true);
                xAxis.setSpaceBetweenLabels(1);
                YAxis leftAxis = chart.getAxisLeft();
                //leftAxis.setTypeface(tf);
                leftAxis.setTextColor(ColorTemplate.getHoloBlue());
              //  leftAxis.setAxisMaxValue(200f);
                leftAxis.setDrawGridLines(true);

          /*      YAxis rightAxis = chart.getAxisRight();
               // rightAxis.setTypeface(tf);
                rightAxis.setTextColor(Color.RED);
               // rightAxis.setAxisMaxValue(900);
                rightAxis.setStartAtZero(false);
               // rightAxis.setAxisMinValue(-200);
                rightAxis.setDrawGridLines(false);
                */
          /*      LineDataSet set1 = new LineDataSet(yVals, "DataSet 1");
                set1.setAxisDependency(YAxis.AxisDependency.LEFT);
                set1.setColor(ColorTemplate.getHoloBlue());
                set1.setCircleColor(Color.WHITE);
                set1.setLineWidth(2f);
                set1.setCircleSize(3f);
                set1.setFillAlpha(65);
                set1.setFillColor(ColorTemplate.getHoloBlue());
                set1.setHighLightColor(Color.rgb(244, 117, 117));
                set1.setDrawCircleHole(false);
                LineDataSet dataSet = new LineDataSet(yVals,"");
                LineData data = new LineData(xVals, dataSet);
                data.setValueTextSize(9f);

                // set data
                chart.setData(data);
                chart.postInvalidate();        */
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
                                intent = new Intent(LiveMarket_Activity.this, LiveMarket_Activity.class);
                            } else if (drawerItem.getIdentifier() == 3) {
                                intent = new Intent(LiveMarket_Activity.this, Wallet_Activity.class);
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



   //     LineData data = getData(36, 100);
      //  setupChart(occupancy_chart,data, Color.rgb(137, 230, 81));

    }

 //   private void setupChart(LineChart chart, LineData data, int color) {

        // no description text

  //  }




}