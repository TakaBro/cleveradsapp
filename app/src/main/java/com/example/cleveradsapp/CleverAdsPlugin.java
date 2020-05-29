package com.example.cleveradsapp;

import android.app.Activity;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.applovin.sdk.AppLovinSdk;
import com.example.cleveradsapp.controller.ControllerFactory;
import com.example.cleveradsapp.controller.interstitial.InterstitialController;
import com.example.cleveradsapp.controller.standard.StandardController;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.LinkedHashMap;

public class CleverAdsPlugin implements LifecycleObserver {

    private String LOGTAG = "TestAds_CleverAdsPlugin";
    private Boolean firstExecution = true;
    public StandardController standardController;
    private InterstitialController interstitialController;
    private ControllerFactory controllerFactory;

    public CleverAdsPlugin(LinkedHashMap<String, String> standardPoolTags,
                           LinkedHashMap<String, String> interstitialPoolTags, int adType,
                           long adWaitTimeLimit, Activity activity) {
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        ActivityHolder.getInstance().setCurrentActivity(activity);
        ActivityHolder.getInstance().setCleverAdsPlugin(this);
        initializeSDKs(activity);
        controllerFactory = new ControllerFactory();
        standardController = controllerFactory.createStandardController(standardPoolTags, adWaitTimeLimit);
        //interstitialController = controllerFactory.createInterstitialController(interstitialPoolTags);
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

    public void createController(LinkedHashMap<String, String> poolTags, int adType, long adWaitTimeLimit) {
        controllerFactory.createController(poolTags, adType, adWaitTimeLimit);
    }

    public void enableStandardAd(LinearLayout adContainer) {
        Log.d(LOGTAG, "Enable standard ad");
        standardController.enableStandardAd();
    }

    public void disableStandardAd() {
        Log.d(LOGTAG, "Disable standard ad");
        standardController.disableStandardAd();
    }

    public void showInsterstitialAd() {
        Log.d(LOGTAG, "Trying to show interstitial ad ...");
        //interstitialController.showAd();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onEnterForeground() {
        Log.d(LOGTAG, "App in FOREGROUND");
        if (firstExecution) {
            firstExecution = false;
        }else {
            //interstitialController.resume();
            //standardController.resume();
        }
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onEnterBackground() {
        Log.d(LOGTAG, "App in BACKGROUND");
        //interstitialController.pause();
        //standardController.pause();
    }

    public void onActivityResume() {
        Log.d(LOGTAG, "Activity on RESUME");
        if (ActivityHolder.getInstance().getCurrentActivity().findViewById(R.id.banner_container) != null) {
            Log.d(LOGTAG, "This Activity contains banner_container!");
            standardController.onContainerAppeared((LinearLayout)ActivityHolder.getInstance().getCurrentActivity().findViewById(R.id.banner_container));
        } else if (ActivityHolder.getInstance().getCurrentActivity().findViewById(R.id.another_banner_container) != null){
            Log.d(LOGTAG, "This Activity contains another_banner_container!");
            standardController.onContainerAppeared((LinearLayout)ActivityHolder.getInstance().getCurrentActivity().findViewById(R.id.another_banner_container));
        }
    }

    public void onActivityPause() {
        Log.d(LOGTAG, "Activity on PAUSE");
        if (ActivityHolder.getInstance().getCurrentActivity().findViewById(R.id.banner_container) != null
                || ActivityHolder.getInstance().getCurrentActivity().findViewById(R.id.another_banner_container) != null) {
            standardController.onContainerDisappeared();
        }
    }
}
