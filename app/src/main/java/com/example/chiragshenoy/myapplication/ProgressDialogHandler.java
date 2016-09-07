package com.example.chiragshenoy.myapplication;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by chiragshenoy on 07/09/16.
 */

public class ProgressDialogHandler {

    private static ProgressDialog pd;

    public static void initProgressDialog(Context context) {

        if (pd == null) {
            pd = new ProgressDialog(context);
            pd.setCancelable(false);
            if (!pd.isShowing()) {
                pd.show();
            }
        }
    }

    public static void dismissProgressDialog() {
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }
    }

}
