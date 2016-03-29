package com.example.douglas.clockin;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import adapters.DashboardDrawerAdapter;


public class MainActivity extends AppCompatActivity {

    SharedPreferences mPreferences;
    Boolean mIsFirstClockIn; //holds the users clock in location

    public DrawerLayout mDrawer;
    public ListView mDrawerList;
    public DashboardDrawerAdapter mDashboardAdapter;
    public LinearLayout mClockInEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout_dashboard);
        mDrawerList = (ListView) findViewById(R.id.drawer_list_items);
        mDashboardAdapter = new DashboardDrawerAdapter(this,getResources().getStringArray(R.array.navigation_drawer_items));
        mClockInEntries = (LinearLayout) findViewById(R.id.history_item_container);

        //Get preferences
        mPreferences = getSharedPreferences("com.example.douglas.clockin", MODE_PRIVATE);
        mIsFirstClockIn = mPreferences.getBoolean(getString(R.string.IsFirstInstall), false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mIsFirstClockIn) {
            showTutorial();
        } else {
            showClockInHistory();
        }
    }

    public void showTutorial(){

    }

    public void showClockInHistory(){
        String[] tLocations = new String[]{"San Diego CA", "Palmdale CA"};
        String[] tHours = new String[]{"8","8"};
        String[] tTimes = new String[]{"10:00 p.m.","12:00 p.m."};

        for(int i = 0; i < tLocations.length; i++){
            View insertView = getLayoutInflater().inflate(R.layout.clock_in_history_item,null);
            TextView location = (TextView) insertView.findViewById(R.id.clock_in_history_item_location);
            TextView hours = (TextView) insertView.findViewById(R.id.clock_in_history_item_hours);
            TextView time = (TextView) insertView.findViewById(R.id.clock_in_history_item_time);
            location.setText(tLocations[i]);
            hours.setText(tHours[i]);
            time.setText(tTimes[i]);
            mClockInEntries.addView(insertView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
