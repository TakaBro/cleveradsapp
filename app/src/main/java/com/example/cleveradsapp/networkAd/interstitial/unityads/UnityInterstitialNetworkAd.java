package com.example.cleveradsapp.networkAd.interstitial.unityads;

import android.util.Log;
import android.view.View;

import com.example.cleveradsapp.ActivityHolder;
import com.example.cleveradsapp.networkAd.NetworkAd;
import com.example.cleveradsapp.networkAd.NetworkAdLoadListener;
import com.example.cleveradsapp.networkAd.NetworkAdPresenterListener;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;

public class UnityInterstitialNetworkAd implements NetworkAd, IUnityAdsListener{

    private String TAG = "TestAds_UnityAds";
    private String tag, net;
    private NetworkAdLoadListener loaderListener;
    private NetworkAdPresenterListener presenterListener;
    private ActivityHolder activityHolder;

    private String unityGameID = "1675872";
    private boolean testMode = true;
    public String placementId = "video";
    //private String placementId = "rewarded_teste";
    //private String TAG = "UnityAds";

    public UnityInterstitialNetworkAd(String tag, String net, NetworkAdLoadListener l_listener,
                                      NetworkAdPresenterListener p_Listener) {
        Log.d(TAG, "UnityInterstitialNetworkAd instance created");
        this.tag = tag;
        this.net = net;
        this.loaderListener = l_listener;
        this.presenterListener = p_Listener;
        activityHolder = ActivityHolder.getInstance();
    }

    @Override
    public void request() {
        Log.d(TAG, "UNITY AD REQUEST");
        // Declare a new UNITY listener:
        final IUnityAdsListener myAdsListener = UnityInterstitialNetworkAd.this;
        // Initialize the UNITY SDK:
        UnityAds.initialize(activityHolder.getCurrentActivity(), unityGameID, myAdsListener, testMode);
    }

    @Override
    public void show() {
        if (UnityAds.isReady (placementId)) {
            UnityAds.show (activityHolder.getCurrentActivity(), placementId);
            Log.d(TAG, "UNITY AD SHOW");
        } else {
            Log.d(TAG, "UNITY AD CAN`T SHOW");
        }
    }

    @Override
    public String getTag() {
        return this.tag;
    }

    @Override
    public String getNet() {
        return this.net;
    }

    @Override
    public View getView() {
        return null;
    }

    // Implement the IUnityAdsListener interface methods:
    @Override
    public void onUnityAdsReady(String s) {
        if (s.equals(placementId)){
            Log.d(TAG, "UNITY AD READY TO SHOW " + s); // Implement functionality for an ad being ready to show.
            loaderListener.adLoaded(UnityInterstitialNetworkAd.this);
        }
    }

    @Override
    public void onUnityAdsStart(String s) {
        presenterListener.adOpened(); // Implement functionality for a user starting to watch an ad.
    }

    @Override
    public void onUnityAdsFinish(String s, UnityAds.FinishState finishState) {
        presenterListener.adClosed(); // Implement functionality for a user finishing an ad.
    }

    @Override
    public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String s) {
        Log.d(TAG, "UNITY AD ERROR"); // Implement functionality for a Unity Ads service error occurring.
        loaderListener.adFailedToLoad();
    }
}
