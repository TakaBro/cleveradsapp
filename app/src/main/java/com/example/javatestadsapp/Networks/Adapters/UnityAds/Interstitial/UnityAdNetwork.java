package com.example.javatestadsapp.Networks.Adapters.UnityAds;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.applovin.sdk.AppLovinSdk;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;

public class UnityAdNetwork {

    private String TAG = "TestAds_UnityAds";
    private String unityGameID = "1675872";
    private boolean testMode = true;
    public String placementId = "video";
    //private String placementId = "rewarded_teste";
    //private String TAG = "UnityAds";

    public UnityAdNetwork() {
        Log.d(TAG, "UnityAdNetwork instance created");
    }

    public void initializeUnitySDK(Activity act){
        // Declare a new UNITY listener:
        final UnityAdsListener myAdsListener = new UnityAdsListener();
        // Initialize the UNITY SDK:
        UnityAds.initialize (act, unityGameID, myAdsListener, testMode);
    }

    public void showUnityAdInterstitial(Activity act){
        if (UnityAds.isReady (placementId)) {
            UnityAds.show (act, placementId);
            Log.d(TAG, "UNITY AD SHOW");
        }else{
            Log.d(TAG, "UNITY AD NOT SHOW");
        }
    }

    // Implement the IUnityAdsListener interface methods:
    private class UnityAdsListener implements IUnityAdsListener {

        @Override
        public void onUnityAdsReady (String placementId) {
            Log.d(TAG, "UNITY AD READY TO SHOW " + placementId); // Implement functionality for an ad being ready to show.
        }

        @Override
        public void onUnityAdsStart (String placementId) {
            // Implement functionality for a user starting to watch an ad.
        }

        @Override
        public void onUnityAdsFinish (String placementId, UnityAds.FinishState finishState) {
            // Implement functionality for a user finishing an ad.
        }

        @Override
        public void onUnityAdsError (UnityAds.UnityAdsError error, String message) {
            Log.d(TAG, "UNITY AD ERROR"); // Implement functionality for a Unity Ads service error occurring.
        }
    }
}
