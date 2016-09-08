package com.example.chiragshenoy.myapplication.Events;

import android.content.Context;

import com.example.chiragshenoy.myapplication.Models.Tutor;

/**
 * Created by chiragshenoy on 08/09/16.
 */

public class LoadTutorStatusEvent extends BaseNetworkEvent {

    public static final LoadChaptersEvent.OnLoadingError FAILED = new LoadChaptersEvent.OnLoadingError(UNHANDLED_MSG, UNHANDLED_CODE);

    public static class OnLoaded extends OnDone<Tutor> {
        public OnLoaded(Tutor tutor) {
            super(tutor);
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
