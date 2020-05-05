package com.example.cleveradsapp.networkAd.interstitial.admob;

import android.app.Activity;
import android.util.Log;

import com.example.cleveradsapp.CleverAdsPlugin;
import com.example.cleveradsapp.networkAd.NetworkAd;
import com.example.cleveradsapp.networkAd.NetworkAdLoadListener;
import com.example.cleveradsapp.networkAd.NetworkAdPresenterListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class AdMobInterstitialNetworkAd extends AdListener implements NetworkAd {

    private String LOGTAG = "TestAds_AdmobAds";
    private String tag, net;
    private Activity activity;
    private NetworkAdLoadListener loaderListener;
    private NetworkAdPresenterListener presenterListener;
    private InterstitialAd mInterstitialAd;
    private AdListener adListener;

    public AdMobInterstitialNetworkAd(String tag, String net, NetworkAdLoadListener l_listener,
                                      NetworkAdPresenterListener p_Listener, Activity activity) {
        this.tag = tag;
        this.net = net;
        this.activity = activity;
        this.loaderListener = l_listener;
        this.presenterListener = p_Listener;
        this.adListener = AdMobInterstitialNetworkAd.this;
        mInterstitialAd = new InterstitialAd(CleverAdsPlugin.getCurrentActivity());
        mInterstitialAd.setAdUnitId(tag);
    }

    @Override
    public void request() {
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        listenerAdMobInterstitial();
    }

    @Override
    public void show() {
        Log.d(LOGTAG, "AdMob show()");
        mInterstitialAd.show();
    }

    @Override
    public void onAdLoaded() {
        Log.d(LOGTAG, "AdMob Interstitial LOADED"); // Code to be executed when an ad finishes loading.
        loaderListener.adLoaded(AdMobInterstitialNetworkAd.this);
    }

    @Override
    public void onAdFailedToLoad(int errorCode) {
        Log.d(LOGTAG, "AdMob Interstitial FAILED TO LOAD"); // Code to be executed when an ad request fails.

        switch (errorCode) {
            case 0:
                Log.d(LOGTAG, "ERROR_CODE_INTERNAL_ERROR");
                break;
            case 1:
                Log.d(LOGTAG, "ERROR_CODE_INVALID_REQUEST");
                break;
            case 2:
                Log.d(LOGTAG, "ERROR_CODE_NETWORK_ERROR");
                break;
            case 3:
                Log.d(LOGTAG, "ERROR_CODE_NO_FILL");
                break;
            default:
                Log.d(LOGTAG, "--------------------------------------");
        }
        loaderListener.adFailedToLoad();
    }

    @Override
    public void onAdOpened() {
        presenterListener.adOpened(); // Code to be executed when the ad is displayed.
    }

    @Override
    public void onAdClicked() {
        // Code to be executed when the user clicks on an ad.
    }

    @Override
    public void onAdLeftApplication() {
        // Code to be executed when the user has left the app.
    }

    @Override
    public void onAdClosed() {
        presenterListener.adClosed(); // Code to be executed when the interstitial ad is closed.
    }

    public String getTag() { return this.tag; }

    public String getNet() { return this.net; }

    public void listenerAdMobInterstitial() {
        mInterstitialAd.setAdListener(adListener);
    }
}
