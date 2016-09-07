package com.example.chiragshenoy.myapplication;

import android.app.Application;

import com.example.chiragshenoy.myapplication.Bus.BusProvider;
import com.example.chiragshenoy.myapplication.REST.ApiRequestHandler;
import com.example.chiragshenoy.myapplication.UI.BaseActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by chiragshenoy on 03/09/16.
 */

public class App extends Application {


    public static ApiRequestHandler mApiRequestHandler;
    private EventBus mBus = BusProvider.bus();


    @Override
    public void onCreate() {
        super.onCreate();
        //Bind the bus and ApiRequest Handler
        mApiRequestHandler = new ApiRequestHandler(mBus);
        mBus.register(mApiRequestHandler);
    }

}
