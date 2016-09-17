package com.example.chiragshenoy.myapplication.Events;

import android.content.Context;
import android.util.Log;

import com.example.chiragshenoy.myapplication.Bus.BusProvider;
import com.example.chiragshenoy.myapplication.NetworkUtil;
import com.example.chiragshenoy.myapplication.ProgressDialogHandler;

/**
 * Created by chiragshenoy on 03/09/16.
 */


public class BaseNetworkEvent {

    public static final String UNHANDLED_MSG = "UNHANDLED_MSG";
    public static final int UNHANDLED_CODE = -1;

    protected static class OnStart<Rq> {
        private Rq mRequest;
        private Context mContext;

        public OnStart(Rq request, Context context, String loadingMessage) {

//            if (!NetworkUtil.getConnectivityStatusString(context)) {
//                Log.e("No interet", "1");
//                BusProvider.bus().post(new NoInternetEvent(true));
//                return;
//            }

            mRequest = request;
            mContext = context;
            ProgressDialogHandler.initProgressDialog(mContext, loadingMessage);
        }

        public Rq getRequest() {
            return mRequest;
        }

        public Context getContext() {
            return mContext;
        }
    }

    protected static class OnDone<Rs> {

        private Rs mResponse;

        public OnDone(Rs response) {
            mResponse = response;
            ProgressDialogHandler.dismissProgressDialog();
        }

        public Rs getResponse() {
            return mResponse;
        }
    }

    protected static class OnFailed {

        private String mErrorMessage;
        private int mCode;

        public OnFailed(String errorMessage, int code) {
            mErrorMessage = errorMessage;
            mCode = code;
            ProgressDialogHandler.dismissProgressDialog();
        }

        public String getErrorMessage() {
            return mErrorMessage;
        }

        public int getCode() {
            return mCode;
        }

    }
}
