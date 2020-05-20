package com.example.cleveradsapp.networkAd.interstitial.admob;

import android.util.Log;
import android.view.View;

import com.example.cleveradsapp.ActivityHolder;
import com.example.cleveradsapp.networkAd.NetworkAd;
import com.example.cleveradsapp.networkAd.NetworkAdLoadListener;
import com.example.cleveradsapp.networkAd.NetworkAdPresenterListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class AdMobInterstitialNetworkAd extends AdListener implements NetworkAd {

    private String LOGTAG = "TestAds_AdmobAds";
    private String tag, net;
    private NetworkAdLoadListener adLoadListener;
    private NetworkAdPresenterListener adPresentionListener;
    private InterstitialAd mInterstitialAd;
    private AdListener adListener;
    private ActivityHolder activityHolder;

    public AdMobInterstitialNetworkAd(String tag, String net, NetworkAdLoadListener l_listener,
                                      NetworkAdPresenterListener p_Listener) {
        this.tag = tag;
        this.net = net;
        this.adLoadListener = l_listener;
        this.adPresentionListener = p_Listener;
        this.adListener = AdMobInterstitialNetworkAd.this;
        activityHolder = ActivityHolder.getInstance();
        mInterstitialAd = new InterstitialAd(activityHolder.getCurrentActivity());
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
        adLoadListener.adLoaded(AdMobInterstitialNetworkAd.this);
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
        adLoadListener.adFailedToLoad();
    }

    @Override
    public void onAdOpened() {
        adPresentionListener.adOpened(); // Code to be executed when the ad is displayed.
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
        adPresentionListener.adClosed(); // Code to be executed when the interstitial ad is closed.
    }

    public String getTag() { return this.tag; }

    public String getNet() { return this.net; }

    @Override
    public View getView() {
        return null;
    }

    public void listenerAdMobInterstitial() {
        mInterstitialAd.setAdListener(adListener);
    }
}
