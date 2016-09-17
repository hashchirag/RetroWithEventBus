package com.example.chiragshenoy.myapplication.UI;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.chiragshenoy.myapplication.Bus.BusProvider;
import com.example.chiragshenoy.myapplication.Events.NoInternetEvent;
import com.example.chiragshenoy.myapplication.Models.RequestModel;
import com.example.chiragshenoy.myapplication.NetworkUtil;
import com.example.chiragshenoy.myapplication.NoInternetListener;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by chiragshenoy on 03/09/16.
 */

public class BaseActivity extends AppCompatActivity {

    public final String TYPE_STRING = "STRING";

    @Override
    protected void onStart() {
        super.onStart();
        BusProvider.bus().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        BusProvider.bus().unregister(this);
    }

    public boolean isThereInternet() {
        return NetworkUtil.getConnectivityStatusString(getApplicationContext());
    }
}