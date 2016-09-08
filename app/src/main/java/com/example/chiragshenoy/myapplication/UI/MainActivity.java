package com.example.chiragshenoy.myapplication.UI;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chiragshenoy.myapplication.Bus.BusProvider;
import com.example.chiragshenoy.myapplication.Events.LoadChaptersEvent;
import com.example.chiragshenoy.myapplication.Events.LoadPromoCodeEvent;
import com.example.chiragshenoy.myapplication.Events.LoadTutorStatusEvent;
import com.example.chiragshenoy.myapplication.Events.NoInternetEvent;
import com.example.chiragshenoy.myapplication.R;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // These calls do not post No Internet Event. Need to Figure out why.
        // It works only inside anonymous functions like below ?
//        BusProvider.bus().post(new LoadChaptersEvent.OnLoadingStart("10", MainActivity.this));
//        BusProvider.bus().post(new LoadTutorStatusEvent.OnLoadingStart("10206842460954809", MainActivity.this));


        String studentId = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6InN0dWRlbnQ3MjY3NjE5MjgiLCJ1c2VyX2lkIjoyOTgzLCJyb2xlIjowLCJleHAiOjE1NTk3Mjg1ODAsIm9yaWdfaWF0IjoxNDczMzI4NTgwLCJ0eXBlIjoic3R1ZGVudCIsImVtYWlsIjoia2F1c3RodWJAaGFzaGxlYXJuLmNvbSJ9.5SHaSRkTx_K5T1-wX2E1kgyhe1ZgdJuK0hcbEZWiwa8";
        String promocode = "hxxj8890";

        HashMap<String, String> h = new HashMap<>();
        h.put("userid", studentId);
        h.put("code", promocode);
        BusProvider.bus().post(new LoadPromoCodeEvent.OnLoadingStart(h, MainActivity.this));

//        TextView tv = (TextView) findViewById(R.id.tv);
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //Do something after 100ms
//                BusProvider.bus().post(new LoadChaptersEvent.OnLoadingStart("10", MainActivity.this));
//            }
//        }, 100);
//
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BusProvider.bus().post(new LoadChaptersEvent.OnLoadingStart("10", MainActivity.this));
//
//            }
//        });
    }

    @Subscribe
    public void onChaptersLoaded(LoadChaptersEvent.OnLoaded onLoaded) {
        Toast.makeText(getApplicationContext(), "Loaded items" + onLoaded.getResponse().get(0).getName(), Toast.LENGTH_LONG).show();
        Log.e("OncollegesLoaded called", onLoaded.getResponse().get(0).getName());
    }

    @Subscribe
    public void onChaptersLoadFailed(LoadChaptersEvent.OnLoadingError onLoadingError) {
        Log.e("Onloading error", "College");
    }

    @Override
    public void noInternetEvent(NoInternetEvent noInternetEvent) {
        Log.e("No net", "YES");
    }

    @Subscribe
    public void onTutorStatusLoaded(LoadTutorStatusEvent.OnLoaded onLoaded) {
        Log.e("OncollegesLoaded called", onLoaded.getResponse().getEmail());

    }

    @Subscribe
    public void onTutorStatusLoadFailed(LoadTutorStatusEvent.OnLoadingError onLoadingError) {
        Log.e("Onloading error", "Tutor");
    }

    @Subscribe
    public void onPromoCodeLoaded(LoadPromoCodeEvent.OnLoaded onLoaded) {
        Log.e("OncollegesLoaded called", onLoaded.getResponse().getStatus());
    }

    @Subscribe
    public void onPromoCodeLoadFailed(LoadPromoCodeEvent.OnLoadingError onLoadingError) {
        Log.e("Onloading error", "Promo");
    }

}
