package com.example.anshu.ktj;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;

public class Home_Activity extends AppCompatActivity {
    private AccountHeader headerResult = null;
    private Drawer result = null;
    private boolean opened = false;

    VideoView videoView;
    private MediaController mediaControls;
    private int position = 0;

    private VolleySingleton volleySingleton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);
        if (mediaControls == null) {

            mediaControls = new MediaController(Home_Activity.this);

        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        videoView=(VideoView)findViewById(R.id.videoView2);
        try {

            //set the media controller in the VideoView

            videoView.setMediaController(mediaControls);



            //set the uri of the video to be played

            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bitcoinvideo));



        } catch (Exception e) {

            Log.e("Error", e.getMessage());

            e.printStackTrace();

        }
        //videoView.requestFocus();

        //we also set an setOnPreparedListener in order to know when the video file is ready for playback

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {


            public void onPrepared(MediaPlayer mediaPlayer) {

                // close the progress bar and play the video

                //if we have a position on savedInstanceState, the video playback should start from here

                videoView.seekTo(position);

                if (position == 0) {

                    videoView.start();

                } else {

                    //if we come from a resumed activity, video playback will be paused

                    videoView.pause();

                }

            }


        });

        ParseUser user = ParseUser.getCurrentUser();
        String name = user.getString("name");
        String email = user.getString("email");
        final IProfile profile = new ProfileDrawerItem().withName(name).withEmail(email).withIdentifier(100);


        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(profile)

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
                      new PrimaryDrawerItem().withName("Log Out").withDescription("").withIdentifier(4).withSelectable(false)
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
                                intent = new Intent(Home_Activity.this, LiveMarket_Activity.class);
                            } else if (drawerItem.getIdentifier() == 3) {
                                intent = new Intent(Home_Activity.this, Wallet_Activity.class);
                            }
                            else if (drawerItem.getIdentifier() == 4) {
                                intent = new Intent(Home_Activity.this, LoginActivity.class);
                            }
                            if (intent != null) {
                                Home_Activity.this.startActivity(intent);
                            }
                        }
                        return false;
                    }



    }).withSavedInstance(savedInstanceState)
                    .withShowDrawerOnFirstLaunch(true)
                    .build();



        volleySingleton = VolleySingleton.getInstance();

        String url = "http://api.btcxindia.com/trades";
        //occupancy_chart = (LineChart)findViewById(R.id.graph);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                Log.d("Ram", "Line 88");

                try {
                    //Toast.makeText(Home_Activity.this, (response.getJSONObject(1)).toString(), Toast.LENGTH_LONG).show();
                    TextView last_tr = (TextView) findViewById(R.id.last_tr);
                    last_tr.setText("Last Traded Price: "+response.getJSONObject(0).getString("price"));
                } catch (JSONException e) {
                    Log.d("Ram","Line 90");
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override

            public void onErrorResponse(VolleyError error) {
                Log.d("Ram","Line 122");
            }
        });

        volleySingleton.getmRequestQueue().add(jsonArrayRequest);
    }



    @Override
    public void onSaveInstanceState(Bundle savedInstanceState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(savedInstanceState, outPersistentState);
        savedInstanceState.putInt("Position", videoView.getCurrentPosition());

        videoView.pause();

    }

    @Override

    public void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        //we use onRestoreInstanceState in order to play the video playback from the stored position

        position = savedInstanceState.getInt("Position");

        videoView.seekTo(position);

    }

    @Override
    protected void onResume() {
        super.onResume();
        ParseUser user = ParseUser.getCurrentUser();
        String name = user.getString("name");
        String email = user.getString("email");
        final IProfile profile = new ProfileDrawerItem().withName(name).withEmail(email).withIdentifier(100);


        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(profile)

              //  .withSavedInstance(savedInstanceState)
                .build();
        result = new DrawerBuilder()
                .withActivity(this)
               // .withToolbar(toolbar)
                .withHasStableIds(true)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Home").withDescription("").withIdentifier(1).withSelectable(false),
                        new PrimaryDrawerItem().withName("Live Market").withDescription("").withIdentifier(2).withSelectable(false),
                        new PrimaryDrawerItem().withName("Wallet").withDescription("").withIdentifier(3).withSelectable(false),
                        new PrimaryDrawerItem().withName("Log Out").withDescription("").withIdentifier(4).withSelectable(false)
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
                                intent = new Intent(Home_Activity.this, LiveMarket_Activity.class);
                            } else if (drawerItem.getIdentifier() == 3) {
                                intent = new Intent(Home_Activity.this, Wallet_Activity.class);
                            }
                            else if (drawerItem.getIdentifier() == 4) {
                                intent = new Intent(Home_Activity.this, LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            }
                            if (intent != null) {
                                Home_Activity.this.startActivity(intent);
                            }
                        }
                        return false;
                    }



                })
                //.withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(true)
                .build();


    }
}
