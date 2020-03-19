package com.example.javatestadsapp.Networks.NetworkLoader;

import android.app.Activity;

import com.example.javatestadsapp.Networks.Adapters.AdMob.Interstitial.AdMobAdNetwork;
import com.example.javatestadsapp.Networks.Adapters.AppLovin.Interstitial.AppLovinAdNetwork;
import com.example.javatestadsapp.Networks.Adapters.UnityAds.UnityAdNetwork;

import java.util.LinkedHashMap;

public class NetworkLoader {

    UnityAdNetwork unityAdNet;
    AppLovinAdNetwork appLovinAdNet;
    AdMobAdNetwork adMobAdNetwork;

    public NetworkLoader(){
        unityAdNet = new UnityAdNetwork();
        appLovinAdNet = new AppLovinAdNetwork();
        adMobAdNetwork = new AdMobAdNetwork();
    }

    public void loadAd(LinkedHashMap poolTags,  Activity act){
        loadAdMobInterstitial(poolTags, act);
    }

    public void loadAdMobInterstitial(LinkedHashMap tags, Activity act){
        adMobAdNetwork.createAndLoadAdMobInterstitialAd(tags.keySet().toArray()[0].toString(), act);
    }
}
