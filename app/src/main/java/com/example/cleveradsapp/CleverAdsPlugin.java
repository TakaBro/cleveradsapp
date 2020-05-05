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
import com.example.cleveradsapp.controller.ControllerFactory;
import com.example.cleveradsapp.controller.InterstitialController;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;

public class CleverAdsPlugin implements Application.ActivityLifecycleCallbacks, LifecycleObserver {

    private String LOGTAG = "TestAds_CleverAdsPlugin";
    //private static Activity currentActivity;
    private static WeakReference<Activity> currentActivity;
    private Boolean firstExecution = true;
    private InterstitialController interstitialController;
    private ControllerFactory controllerFactory;

    public CleverAdsPlugin(LinkedHashMap<String, String> poolTags, int adType, Activity activity) {
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        //currentActivity = activity;
        currentActivity = new WeakReference<>(activity);
        initializeSDKs(activity);
        controllerFactory = new ControllerFactory();
        interstitialController = controllerFactory.createController(poolTags, adType, activity);
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
        interstitialController.showAd();
    }

    public static Activity getCurrentActivity() {
        return currentActivity.get();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onEnterForeground() {
        Log.d(LOGTAG, "App in FOREGROUND");
        if (firstExecution) {
            firstExecution = false;
        }else {
            interstitialController.resume();
        }
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onEnterBackground() {
        Log.d(LOGTAG, "App in BACKGROUND");
        interstitialController.pause();
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        //currentActivity = activity;
        currentActivity = new WeakReference<>(activity);
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }
}
