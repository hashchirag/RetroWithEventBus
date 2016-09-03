package com.example.chiragshenoy.myapplication.Bus;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by chiragshenoy on 03/09/16.
 */

public class BusProvider {

    private static volatile EventBus instance;

    private BusProvider() {
    }

    public static EventBus bus() {
        EventBus localInstance = instance;
        if (localInstance == null) {
            synchronized (EventBus.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new EventBus();
                }
            }
        }
        return localInstance;
    }
}
