package com.example.cleveradsapp;

import android.app.Activity;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.applovin.sdk.AppLovinSdk;
import com.example.cleveradsapp.controller.ControllerFactory;
import com.example.cleveradsapp.controller.InterstitialController;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.LinkedHashMap;

public class CleverAdsPlugin implements LifecycleObserver {

    private String LOGTAG = "TestAds_CleverAdsPlugin";
    private InterstitialController interstitialController;
    private ControllerFactory controllerFactory;

    public CleverAdsPlugin(LinkedHashMap<String, String> poolTags, int adType, Activity activity){

        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);

        initializeSDKs(activity);

        controllerFactory = new ControllerFactory();
        interstitialController = controllerFactory.createController(poolTags, adType, activity);
    }

    public void initializeSDKs(Activity activity){
        // Initialize ADMOB SDK
        MobileAds.initialize(activity, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        // Initialize APPLOVIN SDK
        AppLovinSdk.initializeSdk(activity);
/*        // Declare a new UNITY listener and Initialize the UNITY SDK:
        unityAdNet.initializeUnitySDK(activity);*/
    }

    public void showInsterstitialAd(){
        Log.d(LOGTAG, "Trying to show ad ...");
        interstitialController.showAd();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onEnterForeground() {
        Log.d(LOGTAG, "App in FOREGROUND");
        interstitialController.resume();
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onEnterBackground() {
        Log.d(LOGTAG, "App in BACKGROUND");
        interstitialController.pause();
    }
}
