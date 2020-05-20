package com.example.cleveradsapp.networkAd.standard.admob;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.cleveradsapp.ActivityHolder;
import com.example.cleveradsapp.networkAd.NetworkAd;
import com.example.cleveradsapp.networkAd.NetworkAdLoadListener;
import com.example.cleveradsapp.networkAd.NetworkAdPresenterListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class AdMobStandardNetworkAd extends AdListener implements NetworkAd {

    private String LOGTAG = "TestAds_AdmobAds";
    private String tag, net;
    private NetworkAdLoadListener loaderListener;
    private NetworkAdPresenterListener presenterListener;
    private AdView AdView;
    private AdListener adListener;
    private LinearLayout adContainer;
    private ActivityHolder activityHolder;

    public AdMobStandardNetworkAd(String tag, String net, NetworkAdLoadListener l_listener,
                                  NetworkAdPresenterListener p_Listener) {
        this.tag = tag;
        this.net = net;
        this.loaderListener = l_listener;
        this.presenterListener = p_Listener;
        this.adListener = AdMobStandardNetworkAd.this;
        activityHolder = ActivityHolder.getInstance();
        AdView = new AdView(activityHolder.getCurrentActivity());
        AdView.setAdSize(AdSize.BANNER);
        AdView.setAdUnitId(tag);
    }

    @Override
    public void request() {
        AdView.loadAd(new AdRequest.Builder().build());
        listenerAdMobStandard();
    }

    @Override
    public AdView getView() {
        return AdView;
    }

    @Override
    public void show() {
        Log.d(LOGTAG, "AdMob show()");
    }

    @Override
    public void onAdLoaded() {
        Log.d(LOGTAG, "AdMob Standard LOADED"); // Code to be executed when an ad finishes loading.
        loaderListener.adLoaded(AdMobStandardNetworkAd.this);
    }

    @Override
    public void onAdFailedToLoad(int errorCode) {
        Log.d(LOGTAG, "AdMob Standard FAILED TO LOAD"); // Code to be executed when an ad request fails.

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
        presenterListener.adClosed(); // Code to be executed when the standard ad is closed.
    }

    public String getTag() { return this.tag; }

    public String getNet() { return this.net; }

    public void listenerAdMobStandard() {
        AdView.setAdListener(adListener);
    }
}
