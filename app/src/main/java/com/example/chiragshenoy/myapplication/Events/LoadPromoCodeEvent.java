package com.example.chiragshenoy.myapplication.Events;

import android.content.Context;

import com.example.chiragshenoy.myapplication.Models.BaseModel;
import com.example.chiragshenoy.myapplication.Models.RequestModel;

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

    public static class OnLoadingStart extends OnStart<RequestModel> {
        public OnLoadingStart(RequestModel requestModel, Context c) {
            super(requestModel, c);
        }
    }

    public static class OnLoadingError extends OnFailed {
        public OnLoadingError(String errorMessage, int code) {
            super(errorMessage, code);
        }
    }

}
