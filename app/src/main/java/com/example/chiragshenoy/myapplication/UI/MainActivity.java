package com.example.chiragshenoy.myapplication.UI;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.chiragshenoy.myapplication.Bus.BusProvider;
import com.example.chiragshenoy.myapplication.Events.LoadChaptersEvent;
import com.example.chiragshenoy.myapplication.R;

import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BusProvider.bus().post(new LoadChaptersEvent.OnLoadingStart("10"));
    }

    @Subscribe
    public void onCollegesLoaded(LoadChaptersEvent.OnLoaded onLoaded) {
        Toast.makeText(getApplicationContext(), "Loaded items" + onLoaded.getResponse().get(0).getName(), Toast.LENGTH_LONG).show();
        Log.e("OncollegesLoaded called", onLoaded.getResponse().get(0).getName());
    }

    @Subscribe
    public void onCollegeLoadFailed(LoadChaptersEvent.OnLoadingError onLoadingError) {
        Log.e("Onloading eroor", "YES");
    }

}
