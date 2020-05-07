package com.example.cleveradsapp.loader.simple;

import android.util.Log;

import com.example.cleveradsapp.loader.Cascade;
import com.example.cleveradsapp.loader.CascadeListener;

public class SimpleCascade implements Cascade {
    /*@Override
    public void initialize(LinkedHashMap poolTags) {

    }*/

    public SimpleCascade() {
        Log.d("TestAds_SimpleCascade", "SimpleCascade instance created");
    }

    @Override
    public void loadAd() {

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
