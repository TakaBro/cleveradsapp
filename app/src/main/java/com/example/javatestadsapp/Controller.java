package com.example.javatestadsapp;

import android.app.Activity;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.applovin.sdk.AppLovinSdk;
import com.example.javatestadsapp.Cascade.CascadeLoader.CascadeLoader;
import com.example.javatestadsapp.Networks.Adapters.AdMob.Interstitial.AdMobAdNetwork;
import com.example.javatestadsapp.Networks.Adapters.AppLovin.Interstitial.AppLovinAdNetwork;
import com.example.javatestadsapp.Networks.Adapters.UnityAds.UnityAdNetwork;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.LinkedHashMap;

public class Controller extends AppCompatActivity {
    private String TAG = "TestAds_Controller";

    CascadeLoader cascadeLoader;
    UnityAdNetwork unityAdNet = new UnityAdNetwork();
    AppLovinAdNetwork appLovinAdNet = new AppLovinAdNetwork();
    AdMobAdNetwork adMobAdNetwork = new AdMobAdNetwork();

    private int indexPoolTags = 4;

    public void setup(int cascateType){
        cascadeLoader = new CascadeLoader(cascateType);
    }

    public void initializeSDKs(Activity act){
        // Initialize ADMOB SDK
        MobileAds.initialize(act, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        // Initialize APPLOVIN SDK
        AppLovinSdk.initializeSdk(act);
        // Declare a new UNITY listener and Initialize the UNITY SDK:
        unityAdNet.initializeUnitySDK(act);
    }

    public void startCascate(LinkedHashMap<String, String> poolTags, Activity act){
        cascadeLoader.loadAd(poolTags, act);

        // Add keys and values (tag, net)
        /*poolTags.put("ca-app-pub-5073389895475742/6911676682", "AdMob");            //no fill
        poolTags.put("ca-app-pub-5073389895475742/1333192516", "AdMob");            //no fill
        poolTags.put("ca-app-pub-5073389895475742/8102108072", "AdMob-AppLovin");   //mediation
        poolTags.put("1675872-video", "UnityAds");                                  //unityads
        poolTags.put("ca-app-pub-3940256099942544/1033173712", "AdMob");            //test*/

        /*for (String i : poolTags.keySet()) {
                System.out.println("tag: " + i + "network: " + poolTags.get(i));
        }*/
    }

    public void runCascate(LinkedHashMap tags, Activity act){
        adMobAdNetwork.createAndLoadAdMobInterstitialAd(tags.keySet().toArray()[indexPoolTags].toString(), act);
        //appLovinAdNet.createAndLoadAppLovinInterstitialAd(this);
        Log.d(TAG, "loading Ad ...");
    }
}
