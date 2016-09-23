package com.example.chiragshenoy.myapplication.UI;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chiragshenoy.myapplication.Bus.BusProvider;
import com.example.chiragshenoy.myapplication.Events.LoadPromoCodeEvent;
import com.example.chiragshenoy.myapplication.Models.RequestModel;
import com.example.chiragshenoy.myapplication.R;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by chiragshenoy on 23/09/16.
 */

public class TestFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("In fragment", "test fragment");
        BusProvider.bus().register(this);

        String studentId = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6InN0dWRlbnQ3MjY3NjE5MjgiLCJ1c2VyX2lkIjoyOTgzLCJyb2xlIjowLCJleHAiOjE1NTk3Mjg1ODAsIm9yaWdfaWF0IjoxNDczMzI4NTgwLCJ0eXBlIjoic3R1ZGVudCIsImVtYWlsIjoia2F1c3RodWJAaGFzaGxlYXJuLmNvbSJ9.5SHaSRkTx_K5T1-wX2E1kgyhe1ZgdJuK0hcbEZWiwa8";
        String promocode = "hxxj8890";

        RequestModel<String> requestModel = new RequestModel<>();
        requestModel.addParam("userid", studentId);
        requestModel.addParam("code", promocode);

        BusProvider.bus().post(new LoadPromoCodeEvent.OnLoadingStart(requestModel, getActivity()));

        return inflater.inflate(R.layout.layout_fragment, container, false);
    }

    @Subscribe
    public void onPromoCodeLoaded(LoadPromoCodeEvent.OnLoaded onLoaded) {
        Log.e("FRAGMENT ", onLoaded.getResponse().getStatus());
    }
}
