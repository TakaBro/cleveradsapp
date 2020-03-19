package com.example.javatestadsapp.Cascade.CrazyCascade;

import android.app.Activity;

import com.example.javatestadsapp.Cascade.Cascade;
import com.example.javatestadsapp.Networks.Adapters.AdMob.Interstitial.AdMobAdNetwork;
import com.example.javatestadsapp.Networks.NetworkLoader.NetworkLoader;

import java.util.LinkedHashMap;

public class CrazyCascade implements Cascade {

    private NetworkLoader netLoader;

    /*@Override
    public void initialize(LinkedHashMap poolTags) {

    }*/

    public CrazyCascade(){
        netLoader = new NetworkLoader();
    }

    @Override
    public void execute(LinkedHashMap tags,  Activity act) {
        netLoader.loadAd(poolTags, act);
    }
}
