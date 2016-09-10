package com.example.chiragshenoy.myapplication.REST;

import android.util.Log;

import com.example.chiragshenoy.myapplication.Events.LoadPromoCodeEvent;
import com.example.chiragshenoy.myapplication.Events.LoadTutorStatusEvent;
import com.example.chiragshenoy.myapplication.Models.BaseModel;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chiragshenoy on 10/09/16.
 */

public class RetrofitUtil {


    public static void makeCall(final EventBus mBus, ApiClient apiClient) {

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(onLoadingStart.getRequest().getRequestParams())).toString());

        apiClient.getStudentNetworkService().getGenericPromoRequest(body).enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Call<BaseModel> call, Response<BaseModel> response) {
                if (response.isSuccessful()) {
                    Log.e("PROMO", "success");
                    mBus.post(new LoadPromoCodeEvent.OnLoaded(response.body()));
                } else {
                    int statusCode = response.code();
                    Log.e("Error code", "" + statusCode);
                    ResponseBody errorBody = response.errorBody();
                    try {
                        Log.e("Error body", response.errorBody().string());
                        mBus.post(new LoadPromoCodeEvent.OnLoadingError(errorBody.string(), statusCode));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseModel> call, Throwable error) {
                if (error != null && error.getMessage() != null) {
                    mBus.post(new LoadTutorStatusEvent.OnLoadingError(error.getMessage(), -1));
                } else {
                    mBus.post(LoadTutorStatusEvent.FAILED);
                }
            }
        });

    }

}
