package com.example.cleveradsapp;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.applovin.sdk.AppLovinSdk;
import com.example.cleveradsapp.controller.Controller;
import com.example.cleveradsapp.controller.ControllerFactory;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.LinkedHashMap;

public class CleverAdsPlugin implements LifecycleObserver {

    private String LOGTAG = "TestAds_CleverAdsPlugin";
    private Boolean firstExecution = true;
    private Controller controller;
    private ControllerFactory controllerFactory;
    private ActivityHolder activityHolder;

    public CleverAdsPlugin(LinkedHashMap<String, String> poolTags, int adType, Activity activity) {
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        activityHolder = ActivityHolder.getInstance();
        activityHolder.setCurrentActivity(activity);
        initializeSDKs(activity);
        controllerFactory = new ControllerFactory();
        controller = controllerFactory.createController(poolTags, adType);
    }

    public void initializeSDKs(Activity activity) {
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

    public void showInsterstitialAd() {
        Log.d(LOGTAG, "Trying to show ad ...");
        controller.showAd();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onEnterForeground() {
        Log.d(LOGTAG, "App in FOREGROUND");
        if (firstExecution) {
            firstExecution = false;
        }else {
            controller.resume();
        }
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onEnterBackground() {
        Log.d(LOGTAG, "App in BACKGROUND");
        controller.pause();
    }
}
