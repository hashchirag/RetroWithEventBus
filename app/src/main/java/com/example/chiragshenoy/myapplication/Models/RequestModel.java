package com.example.chiragshenoy.myapplication.Models;

import android.util.ArrayMap;

import java.util.Map;

/**
 * Created by chiragshenoy on 08/09/16.
 */

public class RequestModel<T> {

    private Map<String, T> requestParams = new ArrayMap<>();

    public Map<String, T> getRequestParams() {
        return requestParams;
    }

    public void addParam(String key, T value) {
        requestParams.put(key, value);
    }



}
