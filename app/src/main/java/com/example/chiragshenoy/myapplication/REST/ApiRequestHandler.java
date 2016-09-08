package com.example.chiragshenoy.myapplication.REST;

import android.util.Log;

import com.example.chiragshenoy.myapplication.Events.LoadChaptersEvent;
import com.example.chiragshenoy.myapplication.Events.LoadPromoCodeEvent;
import com.example.chiragshenoy.myapplication.Events.LoadTutorStatusEvent;
import com.example.chiragshenoy.myapplication.Events.NoInternetEvent;
import com.example.chiragshenoy.myapplication.Models.BaseModel;
import com.example.chiragshenoy.myapplication.Models.Chapter;
import com.example.chiragshenoy.myapplication.Models.Tutor;
import com.example.chiragshenoy.myapplication.NetworkUtil;
import com.example.chiragshenoy.myapplication.REST.ApiClient;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chiragshenoy on 03/09/16.
 */


public class ApiRequestHandler {

    private EventBus mBus;
    private ApiClient mApiClient;


    public ApiRequestHandler(EventBus bus) {
        mBus = bus;
        mApiClient = ApiClient.getInstance();
    }

    @Subscribe
    public void onLoadingStart(LoadChaptersEvent.OnLoadingStart onLoadingStart) {
        Log.e("On Loading Start ", "of LoadChapter");

        mApiClient.getStudentNetworkService()
                .listChapters(onLoadingStart.getRequest())
                .enqueue(new Callback<List<Chapter>>() {

                    @Override
                    public void onResponse(Call<List<Chapter>> call, Response<List<Chapter>> response) {
                        if (response.isSuccessful()) {
                            mBus.post(new LoadChaptersEvent.OnLoaded(response.body()));

                        } else {
                            int statusCode = response.code();
                            ResponseBody errorBody = response.errorBody();
                            try {
                                mBus.post(new LoadChaptersEvent.OnLoadingError(errorBody.string(), statusCode));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Chapter>> call, Throwable error) {
                        if (error != null && error.getMessage() != null) {
                            mBus.post(new LoadChaptersEvent.OnLoadingError(error.getMessage(), -1));
                        } else {
                            mBus.post(LoadChaptersEvent.FAILED);
                        }
                    }
                });
    }


    @Subscribe
    public void onLoadingStartOfGetTutorStatus(LoadTutorStatusEvent.OnLoadingStart onLoadingStart) {
        Log.e("On Loading Start ", "of Load Status");
        mApiClient.getStudentNetworkService().getTutorStatus(onLoadingStart.getRequest()).enqueue(new Callback<Tutor>() {
            @Override
            public void onResponse(Call<Tutor> call, Response<Tutor> response) {
                if (response.isSuccessful()) {
                    mBus.post(new LoadTutorStatusEvent.OnLoaded(response.body()));

                } else {
                    int statusCode = response.code();
                    ResponseBody errorBody = response.errorBody();
                    try {
                        mBus.post(new LoadTutorStatusEvent.OnLoadingError(errorBody.string(), statusCode));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Tutor> call, Throwable error) {
                if (error != null && error.getMessage() != null) {
                    mBus.post(new LoadTutorStatusEvent.OnLoadingError(error.getMessage(), -1));
                } else {
                    mBus.post(LoadTutorStatusEvent.FAILED);
                }
            }
        });

    }


    @Subscribe
    public void onLoadingStartOfPromoCodeEvent(LoadPromoCodeEvent.OnLoadingStart onLoadingStart) {
        Log.e("On Loading Start ", "of PromoCode");
        String s = (String) onLoadingStart.getRequest().get("userid");
        String c = (String) onLoadingStart.getRequest().get("code");

        Log.e("On Loading start", s);
        Log.e("On Loading start", c);

        mApiClient.getStudentNetworkService().getPromoResponse(s, c).enqueue(new Callback<BaseModel>() {
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