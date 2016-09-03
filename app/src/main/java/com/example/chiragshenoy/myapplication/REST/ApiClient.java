package com.example.chiragshenoy.myapplication.REST;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chiragshenoy on 03/09/16.
 */

public class ApiClient {

    public static final String API_ROOT = "http://apisvc.tutor.hashlearn.com/";

    private static volatile ApiClient instance;

    private NetworkService mNetworkService;

    private ApiClient() {
        init();
    }

    public static ApiClient getInstance() {
        ApiClient localInstance = instance;
        if (localInstance == null) {
            synchronized (ApiClient.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ApiClient();
                }
            }
        }
        return localInstance;
    }

    public void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_ROOT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();
        mNetworkService = retrofit.create(NetworkService.class);
    }

    public NetworkService getNetworkService() {

        return mNetworkService;

    }

}
