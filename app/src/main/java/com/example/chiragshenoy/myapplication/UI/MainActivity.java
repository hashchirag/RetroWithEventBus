package com.example.chiragshenoy.myapplication.UI;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chiragshenoy.myapplication.Bus.BusProvider;
import com.example.chiragshenoy.myapplication.Events.LoadChaptersEvent;
import com.example.chiragshenoy.myapplication.Events.NoInternetEvent;
import com.example.chiragshenoy.myapplication.R;

import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView) findViewById(R.id.tv);

        // This call does not post No Internet Event. Need to Figure out why.
        // It works only inside anonymous functions like below ?
        BusProvider.bus().post(new LoadChaptersEvent.OnLoadingStart("10", MainActivity.this));

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                BusProvider.bus().post(new LoadChaptersEvent.OnLoadingStart("10", MainActivity.this));
            }
        }, 100);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusProvider.bus().post(new LoadChaptersEvent.OnLoadingStart("10", MainActivity.this));

            }
        });
    }

    @Subscribe
    public void onCollegesLoaded(LoadChaptersEvent.OnLoaded onLoaded) {
        Toast.makeText(getApplicationContext(), "Loaded items" + onLoaded.getResponse().get(0).getName(), Toast.LENGTH_LONG).show();
        Log.e("OncollegesLoaded called", onLoaded.getResponse().get(0).getName());
    }

    @Subscribe
    public void onCollegeLoadFailed(LoadChaptersEvent.OnLoadingError onLoadingError) {
        Log.e("Onloading error", "YES");
    }

    @Override
    public void noInternetEvent(NoInternetEvent noInternetEvent) {
        Log.e("No net", "YES");
    }
}
