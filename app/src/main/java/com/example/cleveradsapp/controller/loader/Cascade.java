package com.example.cleveradsapp.controller.loader;

import android.app.Activity;
import com.example.cleveradsapp.networkAd.NetworkAd;
//import com.example.cleveradsapp.networkAd.NetworkLoader.NetworkLoader;

import java.util.ArrayList;

public interface Cascade {

    ArrayList<NetworkAd> networkAdsList = new ArrayList<NetworkAd>();

    void loadAd(Activity act);

    void reset();

    void addListener(CascadeListener cascadeListener);
}
