package com.example.cleveradsapp.controller.loader.simple;

import android.app.Activity;
import android.util.Log;

import com.example.cleveradsapp.controller.loader.Cascade;
import com.example.cleveradsapp.controller.loader.CascadeListener;

import java.util.LinkedHashMap;

public class SimpleCascade implements Cascade {
    /*@Override
    public void initialize(LinkedHashMap poolTags) {

    }*/

    public SimpleCascade(LinkedHashMap<String, String> tags, int adType, Activity act) {
        Log.d("TestAds_SimpleCascade", "SimpleCascade instance created");
    }

    @Override
    public void loadAd(Activity act) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void reset() {

    }

    @Override
    public void addListener(CascadeListener cascadeListener) {

    }
}
