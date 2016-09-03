package com.example.chiragshenoy.myapplication.REST;

import android.util.Log;

import com.example.chiragshenoy.myapplication.Events.LoadChaptersEvent;
import com.example.chiragshenoy.myapplication.Models.Chapter;
import com.example.chiragshenoy.myapplication.REST.ApiClient;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
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
        Log.e("Entered", "Yea");
        mApiClient.getNetworkService()
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
}