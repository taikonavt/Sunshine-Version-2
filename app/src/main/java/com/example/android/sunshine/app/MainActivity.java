package com.example.android.sunshine.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    String MY_LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }

        Log.d(MY_LOG_TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(MY_LOG_TAG, "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(MY_LOG_TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(MY_LOG_TAG, "onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(MY_LOG_TAG, "onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(MY_LOG_TAG, "onDestroy");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            //noinspection SimplifiableIfStatement
            case (R.id.action_settings): {
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            }
            case (R.id.show_location): {
                SharedPreferences preferences = PreferenceManager
                        .getDefaultSharedPreferences(this);
                String location = preferences.getString(getString(R.string.pref_location_key),
                        getString(R.string.pref_location_default));

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:0,0?q=" + location));

                if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                } else {
                    Log.d(MY_LOG_TAG, "Couldn't call " + location + ", no receiving apps installed!");
                }
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
