package com.example.chiragshenoy.myapplication.Events;

import android.content.Context;

import com.example.chiragshenoy.myapplication.Models.BaseModel;

import java.util.HashMap;

/**
 * Created by chiragshenoy on 08/09/16.
 */

public class LoadPromoCodeEvent extends BaseNetworkEvent {

    public static final LoadPromoCodeEvent.OnLoadingError FAILED = new LoadPromoCodeEvent.OnLoadingError(UNHANDLED_MSG, UNHANDLED_CODE);

    public static class OnLoaded extends OnDone<BaseModel> {
        public OnLoaded(BaseModel baseModel) {
            super(baseModel);
        }
    }

    public static class OnLoadingStart extends OnStart<HashMap> {
        public OnLoadingStart(HashMap s, Context c) {
            super(s, c);
        }
    }

    public static class OnLoadingError extends OnFailed {
        public OnLoadingError(String errorMessage, int code) {
            super(errorMessage, code);
        }
    }

}
