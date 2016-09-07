package com.example.chiragshenoy.myapplication.Events;

/**
 * Created by chiragshenoy on 07/09/16.
 */

public class NoInternetEvent {


    private boolean withRetryPolicy;

    public NoInternetEvent(boolean withRetryPolicy) {
        this.withRetryPolicy = withRetryPolicy;
    }

    public boolean isWithRetryPolicy() {
        return withRetryPolicy;
    }

}
