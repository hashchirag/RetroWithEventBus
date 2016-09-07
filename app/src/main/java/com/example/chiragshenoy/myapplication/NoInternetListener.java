package com.example.chiragshenoy.myapplication;

import com.example.chiragshenoy.myapplication.Events.NoInternetEvent;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by chiragshenoy on 07/09/16.
 */
public interface NoInternetListener {

    @Subscribe
    public void noInternetEvent(NoInternetEvent noInternetEvent);

}
