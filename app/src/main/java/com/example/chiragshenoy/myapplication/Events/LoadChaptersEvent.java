package com.example.chiragshenoy.myapplication.Events;

import android.content.Context;
import android.util.Log;

import com.example.chiragshenoy.myapplication.Bus.BusProvider;
import com.example.chiragshenoy.myapplication.Models.Chapter;

import java.util.List;

/**
 * Created by chiragshenoy on 03/09/16.
 */

public class LoadChaptersEvent extends BaseNetworkEvent {

    public static final OnLoadingError FAILED = new OnLoadingError(UNHANDLED_MSG, UNHANDLED_CODE);

    public static class OnLoaded extends OnDone<List<Chapter>> {
        public OnLoaded(List<Chapter> list) {
            super(list);
        }
    }

    public static class OnLoadingStart extends OnStart<String> {
        public OnLoadingStart(String s, Context c) {
            super(s, c);
        }

    }

    public static class OnLoadingError extends OnFailed {
        public OnLoadingError(String errorMessage, int code) {
            super(errorMessage, code);
        }
    }
}
